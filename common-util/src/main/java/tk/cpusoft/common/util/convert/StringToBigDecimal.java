/**
 * @desc  
 * @date 2015年10月21日
 */
package tk.cpusoft.common.util.convert;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

/**
 * @desc 
 * @date 2015年10月21日-下午6:10:36
 */
public class StringToBigDecimal {
    /**
     * @desc 字符串转为 BigDecimal类型，字符串有可能为null，或者不是数据类型
     * @date 2015年10月21日-下午6:11:48
     * @param str
     * @return BigDecimal 可能返回null
     */
    public static BigDecimal stringToBigDecimal(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        try{
            BigDecimal big = new BigDecimal(str);
            return big;
        }catch(Exception e){
            return null;
        }
    }
}
