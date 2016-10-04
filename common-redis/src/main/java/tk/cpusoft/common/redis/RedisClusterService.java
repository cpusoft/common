package tk.cpusoft.common.redis;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface RedisClusterService {
    
    /**
     * @desc：
     * @date：2015年2月24日-下午12:55:08
     * @param key
     * @param value
     * @param expiredSeconds if expiredSeconds <=0, then no timeout
     */
    public void set(String key, String value, int expiredSeconds);
 
    /**
     * @desc： 事务性的增加或减少某个值
     * @date：2015年2月26日-下午6:46:22
     * @param key
     * @param value >0为增加，<0为减少
     * @param expiredSeconds
     * @return Long
     */
    public Long incrOrDecrAtomic(final String key,final  long value, final int expiredSeconds);
    /**
     * @desc：
     * @date：2015年2月24日-下午12:55:25
     * @param key
     * @return V
     */
    public String get(String key);
 
    /**
     * @desc：
     * @date：2015年2月24日-下午12:55:28
     * @param key void
     */
    public void del(String key);
    
    
    /**
     * @desc：给key的List<V>加个值value
     * @date：2015年2月24日-下午1:04:18
     * @param key
     * @param value
     * @param expiredSeconds void
     */
    public void addList(String key, String value, int expiredSeconds);

    
    /**
     * @desc：
     * @date：2015年2月24日-下午1:05:10
     * @param key
     * @param value
     * @param expiredSeconds void
     */
    public void addSet(String key, String value, int expiredSeconds);
    
    /**
     * @desc：
     * @date：2015年2月24日-下午1:13:22
     * @param key
     * @param value
     * @return boolean
     */
    public boolean isSetContain(String key, String value );
    
    
    /**
     * @desc：
     * @date：2015年2月24日-下午1:29:49
     * @param key
     * @param hKey
     * @param hValue void
     */
    public void addMap(String key, String hKey, String hValue, int expiredSeconds);
    
    /**
     * @desc 一次加入所有的map
     * @date 2015年8月14日-下午5:19:20
     * @param key
     * @param map
     * @param expiredSeconds void 
     */
    public void addMaps(String key, Map<String,String> map, int expiredSeconds);
    
    /**
     * @desc：
     * @date：2015年2月24日-下午1:30:18
     * @param key
     * @param hKey
     * @param hValue
     * @return boolean
     */
    public boolean isMapContain(String key, String hKey);
    
    /**
     * @desc：
     * @date：2015年2月24日-下午6:50:11
     * @param key
     * @param hKey
     * @return HV
     */
    public String getMapValue(String key ,String hKey);
    
    
    /**
     * @desc 移除某个hkey的值
     * @date 2015年3月23日-上午11:13:13
     * @param key
     * @param hkey
     * @return String 
     */
    public Long removeMapValue(String key, String hkey);
    
    /**
     * @desc：
     * @date：2015年2月24日-下午6:57:54
     * @param session
     * @param hKey
     * @param hValue
     * @param expiredSeconds void
     */
    public void setSessionAttribute(HttpSession session, String hKey, String hValue, int expiredSeconds);
    
    /**
     * @desc 注销session
     * @date 2015年3月19日-下午2:53:58
     * @param session void 
     */
    public void invalidSession(String sessionId) ;
    /**
     * @desc：
     * @date：2015年2月24日-下午6:57:58
     * @param session
     * @param hKey
     * @return HV
     */
    public String getSessionAttribute(HttpSession session, String hKey) ;
    
    
    /**
     * @desc 删除session上某个值
     * @date 2015年3月23日-上午11:10:24
     * @param session session值
     * @param hKey key
     * @param hValue
     * @return String 
     */
    public Long removeSessionAttribute(HttpSession session, String hKey);

    /**
     * @desc 得到key的生存时间
     * @date 2015年4月9日-下午6:09:42
     * @param key
     * @return Long 
     */
    public Long getTtl(String key);
}
