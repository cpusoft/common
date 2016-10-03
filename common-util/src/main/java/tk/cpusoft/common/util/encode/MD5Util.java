package tk.cpusoft.common.util.encode;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    
    /**
     * 
     * Md5加密.
     *
     * @param data  将按UTF-8处理
     * @return

     */
    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(data).toLowerCase();
    }
    
    /**
     * 
     * Md5加密.
     *
     * @param data
     * @return

     */
    public static String md5Hex(byte[] data) {
        return DigestUtils.md5Hex(data).toLowerCase();
    }
    
}
