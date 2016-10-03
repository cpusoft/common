package tk.cpusoft.common.util.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlEncodeUtil {

    private static final String DEFAULT_URL_ENCODING = "UTF-8";
    /**
     * 
     * URL 编码, Encode默认为UTF-8. 
     *
     * @param input
     * @return

     */
    public static String urlEncode(String input) {
        try {
            return URLEncoder.encode(input, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }
    
    /**
     * 
     * URL 解码, Encode默认为UTF-8.
     *
     * @param input
     * @return

     */
    public static String urlDecode(String input) {
        try {
            return URLDecoder.decode(input, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }
}
