/**
 * @desc  
 * @date 2016年6月12日
 */
package tk.cpusoft.common.util.test;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.encode.PasswordUtil;

/**
 * @desc 
 * @date 2016年6月12日-上午9:22:56
 */
public class PasswordUtilTest {

    @Test
    public  void test(){
        String rr = PasswordUtil.encrypt("123456");
        System.out.println(rr);
    }
}
