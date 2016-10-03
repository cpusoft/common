/**
 * @desc  
 * @date 2016年8月30日
 */
package tk.cpusoft.common.util.test;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.encode.MD5Util;

/**
 * @desc 
 * @date 2016年8月30日-下午4:23:44
 */
public class MD5UtilTest {

    @Test
    public void test(){
        String r = MD5Util.md5Hex("abc123");
        System.out.println(r);
        
        r = MD5Util.md5Hex(r);
        System.out.println(r);
    }
}
