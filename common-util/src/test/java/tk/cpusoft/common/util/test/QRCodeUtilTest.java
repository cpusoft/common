/**
 * @desc  
 * @date 2015年10月10日
 */
package tk.cpusoft.common.util.test;

import java.io.File;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.qr.QRCodeUtil;

/**
 * @desc 
 * @date 2015年10月10日-下午4:04:02
 */
public class QRCodeUtilTest {
    @Test
    public void encode2File(){
        try {
            QRCodeUtil.encodeToFile("http://www.baidu.com/", "../logs/", "qr", "UTF-8", "png", 114, 114);
            
            String str = QRCodeUtil.decode(new File("../logs/qr.png"), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
