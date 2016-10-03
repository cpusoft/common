/**
 * @desc  
 * @date 2016年4月19日
 */
package tk.cpusoft.common.util.test;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.compress.ZipUtil;

/**
 * @desc 
 * @date 2016年4月19日-上午8:46:34
 */
public class ZipUtilTest {

    @Test
    public void test() throws Exception{
        ZipUtil.zip("G:\\Download\\https企业包.zip", "D:\\1.zip");
    }
}
