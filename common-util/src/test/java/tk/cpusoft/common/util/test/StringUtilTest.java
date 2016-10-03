/**
 * @desc  
 * @date 2016年4月14日
 */
package tk.cpusoft.common.util.test;

import org.testng.annotations.Test;




import tk.cpusoft.common.util.string.FileSuffixType;
import tk.cpusoft.common.util.string.StringUtil;

/**
 * @desc 
 * @date 2016年4月14日-下午4:07:22
 */
public class StringUtilTest {

    @Test
    public void testFile(){
        String fileName = "测试.jpg";
        FileSuffixType  fst = StringUtil.getSuffix(fileName);
        System.out.println(fst);
    }
    
    @Test
    public void testName(){
        String rr = StringUtil.changeSuffix("aasdsfsdf.bmp","png");
        System.out.println(rr);
    }
}
