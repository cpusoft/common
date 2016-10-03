/**
 * @desc  
 * @date 2015年10月21日
 */
package tk.cpusoft.common.util.convert;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

/**
 * @desc 
 * @date 2015年10月21日-下午7:45:08
 */
public class StringToLong {
    /**
     * @desc 字符串转为 Long类型，字符串有可能为null，或者不是数据类型
     * @date 2015年10月21日-下午6:11:48
     * @param str
     * @return Long 可能返回null
     */
    public static Long stringToLong(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        try{
            return Long.valueOf(str);
        }catch(Exception e){
            return null;
        }
    }
}
