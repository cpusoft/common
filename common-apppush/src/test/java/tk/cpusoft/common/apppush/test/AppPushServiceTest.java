/**
 * @desc  
 * @date 2016年8月23日
 */
package tk.cpusoft.common.apppush.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import tk.cpusoft.common.apppush.AppPushService;
import cn.jiguang.common.resp.DefaultResult;
import cn.jpush.api.device.TagAliasResult;

/**
 * @desc 
 * @date 2016年8月23日-下午4:43:12
 */
public class AppPushServiceTest extends AbstractSpringTestNGTest{

    @Resource 
    private AppPushService appPushService;
    
    @Test
    public void push(){
        boolean r = appPushService.push(null, null, "全推送-2016-09-19");
        System.out.println(r);
        
        r = appPushService.push("first", null, "按标签发送:first-2");
        System.out.println(r);
        
        List<String> list = new ArrayList<String>();
        list.add("tag1");
        r = appPushService.push(null, list, "按tag发送tag1-2");
        System.out.println(r);
    }
    
    @Test
    public void getTagAlias(){
        TagAliasResult  ta = appPushService.getTagAlias("1517bfd3f7cf5c13c2a");
        System.out.println(ta);
    }
    
    @Test
    public void updateDeviceTagAlias(){
        String jpushId = "1517bfd3f7cf5c13c2a";
        String alias = "first";
        Set<String> tagsToAdd = new HashSet<String>();
        tagsToAdd.add("tag1");
        tagsToAdd.add("tag2");
        DefaultResult d = appPushService.updateDeviceTagAlias(jpushId,alias, tagsToAdd);
        System.out.println(d);
    }
}
