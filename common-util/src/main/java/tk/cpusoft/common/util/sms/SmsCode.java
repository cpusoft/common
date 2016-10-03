/**
 * @desc  
 * @date 2015年3月23日
 */
package tk.cpusoft.common.util.sms;

import org.apache.commons.lang3.RandomStringUtils;



/**
 * @desc 生成短信验证码
 * @date 2015年3月23日-上午11:58:49
 */
public class SmsCode {

    /**
     * @desc 返回6位纯数字，作为短信验证码 
     * @date 2015年3月23日-上午11:59:32
     * @return String 
     */
    public static String getSmsCode(){
        return RandomStringUtils.random(6, false, true);
    }
}
