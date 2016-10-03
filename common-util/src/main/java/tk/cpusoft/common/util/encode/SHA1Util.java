package tk.cpusoft.common.util.encode;

import org.apache.commons.codec.digest.DigestUtils;

public class SHA1Util {

    /**
     * sha1加密
     * @desc：
     * @date：2015年2月10日-下午1:23:06
     * @param data 将按UTF-8处理
     * @return String
     */
    public static String sha1Hex(String data){
        return DigestUtils.shaHex(data).toLowerCase();
    }
    
    
    /**
     * sha1加密
     * @desc：
     * @date：2015年2月10日-下午1:23:15
     * @param data
     * @return String
     */
    public static String sha1Hex(byte[] data){
        return DigestUtils.shaHex(data).toLowerCase();
    }
    
    
    /**
     * @desc sha256加密 
     * @date 2015年7月9日-下午6:35:43
     * @param data
     * @return String 
     */
    public static String sha256Hex(String data){
        return DigestUtils.sha256Hex(data).toLowerCase();
    }
    /**
     * @desc sha256加密 
     * @date 2015年7月9日-下午6:35:52
     * @param data
     * @return String 
     */
    public static String sha256Hex(byte[] data){
        return DigestUtils.sha256Hex(data).toLowerCase();
    }
}
