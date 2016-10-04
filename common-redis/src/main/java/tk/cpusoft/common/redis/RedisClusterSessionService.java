/**
 * @desc  
 * @date 2015年3月31日
 */
package tk.cpusoft.common.redis;

import javax.servlet.http.HttpSession;

/**
 * @desc 存入redis集群：
 * 等jedis提供了集群的二进制缓存，则会将一些接口放入fin-common-redis中是
 * @date 2015年3月31日-上午10:18:59
 */
public interface RedisClusterSessionService {

    /**
     * @desc 保存到session中， 
     * @date 2015年3月31日-上午10:58:18
     * @param session
     * @param hKey
     * @param t 简单对象
     */
    public <T> void  save(HttpSession session,String hKey,T t, int expiredMins);
    
   
    /**
     * @desc 保存 
     * @date 2015年4月2日-下午6:09:04
     * @param key
     * @param t 简单对象 
     */
    public <T> void save(String key,T t, int expiredMins);
    
      
    /**
     * @desc 得到session中存的值 
     * @date 2015年3月31日-上午10:58:41
     * @param session
     * @param hKey
     * @param className
     * @return T 返回值可能为null
     */
    public <T> T get(HttpSession session,String hKey, Class className);
    
    /**
     * @desc 得到t值 
     * @date 2015年4月2日-下午6:08:41
     * @param key
     * @param className
     * @return T 
     */
    public <T> T get(String key,Class className);
    /**
     * @desc 从session的值删除某个key
     * @date 2015年3月31日-上午10:59:34
     * @param session
     * @param hKey void 
     */
    public void remove(HttpSession session,String hKey);
    
    /**
     * @desc 删除key值 
     * @date 2015年4月2日-下午6:08:13
     * @param key void 
     */
    public void remove(String key);
    

    /**
     * @desc session无效，登出 
     * @date 2015年4月9日-下午2:15:00
     * @param session void 
     */
    void invalidSession(HttpSession session);
    
}
