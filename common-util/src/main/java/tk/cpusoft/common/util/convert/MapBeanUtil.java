/**
 * @desc  
 * @date 2016年1月27日
 */
package tk.cpusoft.common.util.convert;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @desc 实现Map和bean对象的互转
 * @date 2016年1月27日-下午5:17:00
 */
public class MapBeanUtil {
    
    private MapBeanUtil(){}
    
    /**
     * @desc bean对象转为Map
     * @date 2016年1月27日-下午5:19:05
     * @param obj
     * @return Map<String,Object> 
     */
    public static Map<String, Object> beanToMap(Object obj) {

        if(obj == null){
            return null;
        }        
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return map;

    }
    
    /**
     * @desc Map 转为Bean对象
     * @date 2016年1月27日-下午5:19:17
     * @param map
     * @param obj 需要先new
     */
    public static void mapToBean(Map<String, Object> map, Object obj) {
        if (map == null || obj == null) {
            return;
        }
        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            return;
        }
    }
}

