/**
 * @desc  
 * @date 2015年3月14日
 */
package tk.cpusoft.common.util.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import tk.cpusoft.common.util.validate.SyntaxValidate;

/**
 * @desc 
 * @date 2015年3月14日-上午11:56:40
 */
public class SyntaxValidateTest {
    @Test
    public void checkSyntaxTest() {
        String txt = "abc-aaa";
        String result = SyntaxValidate.checkNickName(txt);
        System.out.println(result);
        
        txt = "测试中午abcABC-123";
        result = SyntaxValidate.checkNickName(txt);
        System.out.println(result);
        
        txt = "[]@#$测试中午a3";
        result = SyntaxValidate.checkNickName(txt);
        System.out.println(result);
        
        
        txt = "aaaabaaaa";
        result = SyntaxValidate.checkPassword(txt);
        System.out.println(result);
        
        txt = "aaaa-l@ba.aaa";
        result = SyntaxValidate.checkEmail(txt);
        System.out.println(result);
        
        txt = "99111111111";
        result = SyntaxValidate.checkMobile(txt);
        System.out.println(result);
        
        
        txt = "991111阿三地方abCC/111";
        result = SyntaxValidate.checkNormalString(txt);
        System.out.println(result);
        
        Student s = new Student();
        s.setName("FFS@##@DF");
        s.setSuperName("发#@()射点");
        Map m = SyntaxValidate.checkNormalStringField(s);
        System.out.println(m);
    }
    
    
    
}
