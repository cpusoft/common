/**
 * @desc  
 * @date 2015年10月21日
 */
package tk.cpusoft.common.util.convert;

/**
 * @desc 
 * @date 2015年10月21日-下午7:09:07
 */
public class IntegerToLong {
    public static Long integerToLong(Integer i){
        if(i==null){
            return null;
        }else{
            return Long.valueOf(i);
        }
    }
}
