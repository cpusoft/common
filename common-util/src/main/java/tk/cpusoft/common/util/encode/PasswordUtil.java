/**
 * @desc  
 * @date 2015年3月23日
 */
package tk.cpusoft.common.util.encode;

import org.apache.commons.lang3.StringUtils;

/**
 * @desc 密码做sha1
 * @date 2015年3月23日-下午2:15:17
 */
public class PasswordUtil {
    /**
     * @desc 加盐处理 
     * @date 2015年3月22日-下午12:22:52
     */
    private static final String PASSWORD_SALT_PREFIX = "2cxf02Eq97fueSLYTau367Wabf3Hc348";
    
    
    /** 统一的加密sha1处理
     * @desc 
     * @date 2015年3月22日-下午12:18:17
     * @param password
     * @return String 
     */
    public static String encrypt(String password){
        if(StringUtils.isBlank(password)){
            return null;
        }
        return SHA1Util.sha1Hex(PASSWORD_SALT_PREFIX+password);
    }
}
