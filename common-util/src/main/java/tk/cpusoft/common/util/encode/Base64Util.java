package tk.cpusoft.common.util.encode;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
    private static final String DEFAULT_URL_ENCODING = "UTF-8";
    /**
     * 
     * Base64编码.
     *
     * @param input
     * @return

     */
    public static String base64Encode(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }
     /**
     * 
     * Base64解码.
     *
     * @param input
     * @return

     */
    public static byte[] base64Decode(String input) {
        return Base64.decodeBase64(input);
    }
    /**
     * 
     * Base64编码, URL安全(将Base64中的URL非法字符如+,/=转为其他字符, 见RFC3548).
     *
     * @param input
     * @return

     */
    public static String base64UrlSafeEncode(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }
    /**
     * URL 解码, Encode默认为UTF-8. 
     */
    public static String base64UrlSafeDecode(String part) {
        try {
            
            return new String(Base64.decodeBase64(part),DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }
}
