/**
 * @desc  
 * @date 2016年8月22日
 */
package tk.cpusoft.common.apppush.impl;


import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.resource.spi.ConfigProperty;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


































import tk.cpusoft.common.apppush.AppPushService;
import cn.jiguang.commom.ClientConfig;
import cn.jiguang.common.resp.DefaultResult;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.TagAliasResult;
import cn.jpush.api.device.TagListResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @desc 
 * @date 2016年8月22日-下午1:09:29
 */
@Service(value="appPushService")
public class AppPushServiceImpl implements AppPushService{
    private static Logger logger = LoggerFactory.getLogger(AppPushServiceImpl.class);

    @Resource
    protected CompositeConfiguration config;
    @Value("${appKey}")
    private String appKey;
    
    @Value("${masterSecret}")
    private String masterSecret;
    
    @Value("${maxRetryTimes}")
    private int maxRetryTimes;
    private ClientConfig clientConfig;


    private JPushClient getJpushClient(){
        return new JPushClient(masterSecret, appKey,null,clientConfig);
    }

    @PostConstruct
    public void init(){
      //  appKey = config.getString("appKey");
      //  masterSecret = config.getString("masterSecret");
      //  maxRetryTimes = config.getInt("maxRetryTimes");

        clientConfig = ClientConfig.getInstance();
        clientConfig.setMaxRetryTimes(maxRetryTimes);
        clientConfig.setConnectionTimeout(10 * 1000); // 10 seconds
        clientConfig.setTimeToLive(60 * 60 * 24); // one day
    }

    @Override
    public boolean push(String alias, List<String> pushTags, String alert) {
        try{
            logger.info("push():alias:"+alias+"  pushTags:"+pushTags+"  alert:"+alert);

            JPushClient jpushClient = getJpushClient();
            Builder  builder =  PushPayload.newBuilder()
                    .setPlatform(Platform.all());//所有平台
            if(StringUtils.isNotBlank(alias)){        
                builder = builder.setAudience(Audience.alias(alias));//向选定的人推送
            }
            if(pushTags!=null && pushTags.size()>0){
               builder = builder.setAudience(Audience.tag(pushTags));//根据标签推送
            }
            if(StringUtils.isBlank(alias) && (pushTags==null || pushTags.size()==0)){
                builder = builder.setAudience(Audience.all());
            }
          //  PushPayload pushPayload = PushPayload.alertAll(content);
            PushPayload pushPayload = builder.setNotification(Notification.alert( alert))//消息内容
                    .build();
            logger.info("push():pushPayload:"+pushPayload);
            PushResult result = jpushClient.sendPush(pushPayload);
            logger.info("push():result:"+result);
            return result.isResultOK();
        }catch(Exception e){
            logger.error("push():e:"+e.getMessage(),e);
            return false;
        }
    }
    @Override
    public TagAliasResult getTagAlias(String jpushId){
        try{
            logger.info("getTagAlias():jpushId:"+jpushId);
            JPushClient jpushClient = getJpushClient();
            TagAliasResult result = jpushClient.getDeviceTagAlias(jpushId);
            logger.info("getTagAlias():result:"+result);
            return result;
        }catch(Exception e){
            logger.error("push():e:"+e.getMessage(),e);
            return null;
        }
    }

    @Override
    public DefaultResult updateDeviceTagAlias(String jpushId,String alias, Set<String> tagsToAdd){
        try{
            logger.info("updateDeviceTagAlias():jpushId:"+jpushId);
            JPushClient jpushClient = getJpushClient();
            DefaultResult defaultResult = jpushClient.updateDeviceTagAlias(jpushId, true, true);
            logger.info("updateDeviceTagAlias():result:"+defaultResult);
            
            defaultResult = jpushClient.updateDeviceTagAlias(jpushId, alias, tagsToAdd,null);
            logger.info("updateDeviceTagAlias():result:"+defaultResult);
            return defaultResult;
        }catch(Exception e){
            logger.error("updateDeviceTagAlias():e:"+e.getMessage(),e);
            return null;
        }
    }
    
}
