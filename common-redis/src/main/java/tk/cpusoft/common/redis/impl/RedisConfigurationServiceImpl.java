/**
 * @desc  
 * @date 2015年8月14日
 */
package tk.cpusoft.common.redis.impl;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import tk.cpusoft.common.redis.RedisClusterService;
import tk.cpusoft.common.redis.RedisConfigurationService;

import com.google.gson.reflect.TypeToken;

import tk.cpusoft.common.util.json.JsonUtil;


/**
 * @desc 
 * @date 2015年8月14日-下午4:22:24
 */
@Service(value="redisConfigurationService")
public class RedisConfigurationServiceImpl  implements RedisConfigurationService {

    /**
     * @desc 默认的redis中的map的 
     * @date 2015年8月14日-下午5:07:22
     */
    private static final String REDIS_CONFIG_MAP_KEY="REDIS_CONFIG_MAP_KEY"; 
    
    
    @Resource
    private RedisClusterService redisClusterService;

   

    /**
     * @desc 
     * @date 2015年8月14日-下午4:46:59
     * @param key
     * @param value 
     * @see tk.cpusoft.common.redis.RedisConfigurationService#addProperty(java.lang.String, java.lang.Object)
     */
    @Override
    public void addProperty(String key, Object value) {
        // TODO Auto-generated method stub
        redisClusterService.addMap(REDIS_CONFIG_MAP_KEY, key, JsonUtil.Object2JsonString(value), -1);
    }
    /**
     * @desc 
     * @date 2015年8月14日-下午5:18:01
     * @param map 
     * @see tk.cpusoft.common.redis.RedisConfigurationService#addAllProperty(java.util.Map)
    */
    @Override
    public void addAllProperty(Map<String,String> map) {
        // TODO Auto-generated method stub
        redisClusterService.addMaps(REDIS_CONFIG_MAP_KEY,map,-1);
    }

    /**
     * @desc 
     * @date 2015年8月17日-下午1:25:25 
     * @see tk.cpusoft.common.redis.RedisConfigurationService#clearAllProperty()
    */
    @Override
    public void clearAllProperty() {
        // TODO Auto-generated method stub
        redisClusterService.del(REDIS_CONFIG_MAP_KEY);
    }

    /**
     * @desc 
     * @date 2015年8月14日-下午4:46:59
     * @param key
     * @param defaultValue
     * @return 
     * @see tk.cpusoft.common.redis.RedisConfigurationService#getBoolean(java.lang.String, java.lang.Boolean)
     */
    @Override
    public Boolean getBoolean(String key, Boolean defaultValue) {
        // TODO Auto-generated method stub
        return this.get(key,defaultValue,Boolean.class);
    }

    /**
     * @desc 
     * @date 2015年8月14日-下午4:46:59
     * @param key
     * @param defaultValue
     * @return 
     * @see tk.cpusoft.common.redis.RedisConfigurationService#getDouble(java.lang.String, java.lang.Double)
     */
    @Override
    public Double getDouble(String key, Double defaultValue) {
        // TODO Auto-generated method stub
        return this.get(key,defaultValue,Double.class);
    }

    
    

    /**
     * @desc 
     * @date 2015年8月14日-下午4:46:59
     * @param key
     * @param defaultValue
     * @return 
     * @see tk.cpusoft.common.redis.RedisConfigurationService#getLong(java.lang.String, java.lang.Long)
     */
    @Override
    public Long getLong(String key, Long defaultValue) {
        // TODO Auto-generated method stub
        return this.get(key,defaultValue,Long.class);
    }

    /**
     * @desc 
     * @date 2015年8月14日-下午4:46:59
     * @param key
     * @param defaultValue
     * @return 
     * @see tk.cpusoft.common.redis.RedisConfigurationService#getBigDecimal(java.lang.String, java.math.BigDecimal)
     */
    @Override
    public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        // TODO Auto-generated method stub
        return this.get(key,defaultValue,BigDecimal.class);
    }

    /**
     * @desc 
     * @date 2015年8月14日-下午4:46:59
     * @param key
     * @param defaultValue
     * @return 
     * @see tk.cpusoft.common.redis.RedisConfigurationService#getString(java.lang.String, java.lang.String)
     */
    @Override
    public String getString(String key, String defaultValue) {
        // TODO Auto-generated method stub
        return this.get(key,defaultValue,String.class);
    }
    /**
     * @desc 
     * @date 2015年8月17日-下午1:28:18
     * @param key
     * @param defaultVale
     * @return 
     * @see tk.cpusoft.common.redis.RedisConfigurationService#getDate(java.lang.String, java.util.Date)
    */
    @Override
    public Date getDate(String key, Date defaultValue) {
        // TODO Auto-generated method stub
     // TODO Auto-generated method stub
        return this.get(key,defaultValue,Date.class);
    }
  

    /**
     * @param <T>
     * @desc 
     * @date 2015年8月14日-下午4:46:59
     * @param key
     * @param defaultValue
     * @return 
     * @see tk.cpusoft.common.redis.RedisConfigurationService#getList(java.lang.String, java.util.List)
     */
    @Override
    public  List<String> getList(String key, List<String> defaultValue) {
        if( StringUtils.isBlank(key)  ){
            return null;
        }
        String listStr = redisClusterService.getMapValue(REDIS_CONFIG_MAP_KEY, key);
        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
        Object o = JsonUtil.JsonString2ComplexObject(listStr, listType);
        if(o!=null){
            return (List<String>)o;
        }else{
            return defaultValue;
        }
    }

 

    /**
     * @desc 
     * @date 2015年8月14日-下午4:50:43
     * @param key
     * @param defaultValue
     * @param className
     * @return 
     * @see tk.cpusoft.common.redis.RedisConfigurationService#get(java.lang.String, java.lang.Object, java.lang.Class)
     */
    @Override
    public <T> T get(String key, T defaultValue, Class className) {
        if( StringUtils.isBlank(key) || className == null ){
            return null;
        }

        Object o = JsonUtil.JsonString2SimpleObject(redisClusterService.getMapValue(REDIS_CONFIG_MAP_KEY, key),className);
        if(o!=null){
            return (T)o;
        }else{
            return defaultValue;
        }
    }
    

   
   
 
    
  
   
}
