package tk.cpusoft.common.util.encode;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class HexUtil {
    /**
     * 
     * Hex编码.
     *
     * @param input
     * @return

     */
    public static String hexEncode(byte[] input) {
        return Hex.encodeHexString(input);
    }
    
    /**
     * 
     * Hex解码.
     *
     * @param input
     * @return

     */
    public static byte[] hexDecode(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new IllegalStateException("Hex Decoder exception", e);
        }
    }
    
}
