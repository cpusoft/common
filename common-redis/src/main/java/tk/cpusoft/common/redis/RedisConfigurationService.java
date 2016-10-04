/**
 * @desc  
 * @date 2015年8月14日
 */
package tk.cpusoft.common.redis;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConversionException;

/**
 * @desc redis读取配置参数
 * @date 2015年8月14日-下午4:22:24
 */
public interface RedisConfigurationService {

    
    /**
     * @desc 添加所有属性 
     * @date 2015年8月17日-下午1:22:53
     * @param map void 
     */
    public  void addAllProperty(Map<String,String> map);
    
    /**
     * @desc 添加某个属性 
     * @date 2015年8月17日-下午1:23:01
     * @param key
     * @param value void 
     */
    public  void addProperty(String key, Object value) ;
    
    /**
     * @desc 清除所有属性 
     * @date 2015年8月17日-下午1:24:56 void 
     */
    public void clearAllProperty();
  
    /**
     * @desc 取boolean类型 
     * @date 2015年8月17日-下午1:23:08
     * @param key
     * @param defaultValue
     * @return Boolean 
     */
    public  Boolean getBoolean(String key, Boolean defaultValue);
    
  
    
   
    /**
     * @desc 取double类型 
     * @date 2015年8月17日-下午1:23:16
     * @param key
     * @param defaultValue
     * @return Double 
     */
    public  Double getDouble(String key, Double defaultValue) ;

    
  
  
    /**
     * @desc 取long类型 
     * @date 2015年8月17日-下午1:23:23
     * @param key
     * @param defaultValue
     * @return Long 
     */
    public  Long getLong(String key, Long defaultValue);

   

    /**
     * @desc 取bigdecimal类型 
     * @date 2015年8月17日-下午1:23:29
     * @param key
     * @param defaultValue
     * @return BigDecimal 
     */
    public  BigDecimal getBigDecimal(String key, BigDecimal defaultValue) ;
    

   
    /**
     * @desc 取String类型
     * @date 2015年8月17日-下午1:23:38
     * @param key
     * @param defaultValue
     * @return String 
     */
    public  String getString(String key, String defaultValue) ;

 
    /**
     * @desc 取Date类型 
     * @date 2015年8月17日-下午1:28:03
     * @param key
     * @param defaultVale
     * @return Date 
     */
    public Date getDate(String key, Date defaultValue);
    
    /**
     * @desc 取数组类型：限定为List<String>
     * @date 2015年8月17日-下午1:23:48
     * @param key
     * @param defaultValue
     * @return List<String> 
     */
    public  List<String> getList(String key, List<String> defaultValue) ;
    
    /**
     * @desc 通用类型取（不能数组等集合）
     * @date 2015年8月17日-下午1:24:05
     * @param key
     * @param defaultValue
     * @param className
     * @return T 
     */
    public  <T> T get(String key, T defaultValue,Class className);
}
