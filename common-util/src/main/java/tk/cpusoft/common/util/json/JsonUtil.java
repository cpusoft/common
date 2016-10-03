package tk.cpusoft.common.util.json;

import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @desc 使用gson进行json<-->object转换 
 * @date 2015年3月31日-上午9:11:45
 */
public class JsonUtil {
    private JsonUtil() {}

    
    
    /**
     * @desc 根据object转为json对象 
     * @date 2015年3月31日-上午9:10:42
     * @param o
     * @return String 可能为null 
     */
    public static String Object2JsonString(Object o) {
        if(o==null){
            return null;
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        return gson.toJson(o);
    }

    /** 
     * 举例<br/>
     * Student s2 = (Student)JsonUtil.JsonString2SimpleObject(str, Student.class);<br/>
     * Map m2 = (Map)JsonUtil.JsonString2SimpleObject(str, Map.class);
     * @desc：对简单对象，或者Map
     * @date：2015年2月19日-下午12:39:16
     * @param str
     * @param className
     * @return Object  可能为null
     */
    public static Object JsonString2SimpleObject(String str,Class className){
        if(StringUtils.isBlank(str) || className ==null){
            return null;
        }
        Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        return gson.fromJson(str, className);
    }

    /**
     * @desc：对复杂对象（对象属性还有别的对象，或者数组）
     * @date：2015年2月19日-下午12:36:09
     * @param str
     * @param type 数组对象：Type listType = new TypeToken&lt;ArrayList&lt;Bean&gt;&gt;(){}.getType();<br/>
     *             复杂对象：Type cType = new TypeToken&lt;Bean&gt;(){}.getType();
     * @return Object
     */
    public static Object JsonString2ComplexObject(String str,Type type){
        if(StringUtils.isBlank(str) || type ==null){
            return null;
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        return gson.fromJson(str, type);
    }
}
