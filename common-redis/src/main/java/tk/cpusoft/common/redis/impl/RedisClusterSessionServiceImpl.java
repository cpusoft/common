/**
 * @desc  
 * @date 2015年3月31日
 */
package tk.cpusoft.common.redis.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;








import tk.cpusoft.common.redis.RedisClusterService;
import tk.cpusoft.common.redis.RedisClusterSessionService;
import tk.cpusoft.common.util.json.JsonUtil;



/**
 * @desc 
 * @date 2015年3月31日-上午10:34:26
 */
@Service(value = "redisClusterSessionService")
public class RedisClusterSessionServiceImpl  implements RedisClusterSessionService {

    /**
     * @desc redis类 
     * @date 2015年3月16日-上午9:56:21
     */
    @Resource
    private RedisClusterService redisClusterService;
    
    @Override
    public <T> void save(HttpSession session, String hKey, T t, int expiredMins) {
        // TODO Auto-generated method stub
     // TODO Auto-generated method stub
        if(session==null || StringUtils.isBlank(hKey) || t == null){
            return ;
        }
        redisClusterService.setSessionAttribute(session, hKey, JsonUtil.Object2JsonString(t), 60*expiredMins);
    }

  

    
  
    /**
     * @desc 
     * @date 2015年3月31日-上午10:35:50
     * @param session
     * @param hKey
     * @return 
    */
    @Override
    public <T> T get(HttpSession session, String hKey, Class className) {
        if(session==null || StringUtils.isBlank(hKey) ){
            return null;
        }
        
        Object o = JsonUtil.JsonString2SimpleObject(redisClusterService.getSessionAttribute(session, hKey),className);
        if(o!=null){
            return (T)o;
        }
        return null;
    }

    /**
     * @desc 
     * @date 2015年3月31日-上午10:35:50
     * @param session
     * @param hKey 
    */
    @Override
    public void remove(HttpSession session, String hKey) {
        // TODO Auto-generated method stub
        if(session==null || StringUtils.isBlank(hKey) ){
            return ;
        }
        redisClusterService.removeSessionAttribute(session, hKey);
    }

    /**
     * @desc session无效，登出 
     * @date 2015年4月9日-下午2:14:42
     * @param session void 
     */
    @Override
    public void invalidSession(HttpSession session){
        if(session!=null){
            redisClusterService.invalidSession(session.getId());
        }
    }
    
    @Override
    public <T> void save(String key, T t, int expiredMins) {
        // TODO Auto-generated method stub
        if(StringUtils.isBlank(key) || t==null){
            return ;
        }
        redisClusterService.set(key, JsonUtil.Object2JsonString(t),60*expiredMins);

    }
    
  

    /**
     * @desc 
     * @date 2015年4月2日-上午10:27:59
     * @param key
     * @return 
    */
    @Override
    public <T> T get(String key,Class className) {
        if(StringUtils.isBlank(key) || className==null){
            return null ;
        }
        Object o = JsonUtil.JsonString2SimpleObject(redisClusterService.get(key), className);
        if(o!=null){
            return (T)o;
        }
        return null;
    }

    /**
     * @desc 
     * @date 2015年4月2日-上午10:27:59
     * @param key 
    */
    @Override
    public void remove(String key) {
        if(StringUtils.isBlank(key) ){
            return ;
        }
        redisClusterService.del(key);
    }




   
}
