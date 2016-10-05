/**
 * @desc  
 * @date 2016年8月22日
 */
package tk.cpusoft.common.apppush;

import java.util.List;
import java.util.Set;

import cn.jiguang.common.resp.DefaultResult;
import cn.jpush.api.device.TagAliasResult;

/**
 * @desc 
 * @date 2016年8月22日-下午1:09:09
 */
public interface AppPushService {

    /**
     * @desc 
     * @date 2016年8月23日-下午4:49:41
     * @param alias 别名，可null
     * @param pushTags 标签列表，可null。如果alias和pushTags都是null，则都发送
     * @param title 
     * @param content
     * @return boolean 
     */
    boolean push(String alias, List<String> pushTags, String alert);

    /**
     * @desc 
     * @date 2016年8月23日-下午4:57:19
     * @param usrId
     * @return TagAliasResult 
     */
    TagAliasResult getTagAlias(String jpushId);

    /**
     * @desc 
     * @date 2016年8月31日-下午2:15:03
     * @param userId
     * @param alias
     * @param tagsToAdd
     * @param tagsToRemove
     * @return DefaultResult 
     */
    DefaultResult updateDeviceTagAlias(String jpushId, String alias, Set<String> tagsToAdd );

 

}
