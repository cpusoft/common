/**
 * @desc  
 * @date 2016年8月2日
 */
package tk.cpusoft.common.util.test;

import java.util.Date;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.idcard.IdCardUtil;

/**
 * @desc 
 * @date 2016年8月2日-上午9:37:11
 */
public class IdCardUtilTest {

    @Test
    public void idcardTest(){
        String idCard = "372323199001061825";
        Date d = IdCardUtil.getBirthday(idCard);
        int b = IdCardUtil.getAge(idCard);
        System.out.println(d);
        System.out.println(b);
    }
}
