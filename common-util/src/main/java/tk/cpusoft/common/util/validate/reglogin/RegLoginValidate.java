/**
 * @desc  
 * @date 2015年3月14日
 */
package tk.cpusoft.common.util.validate.reglogin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tk.cpusoft.common.util.validate.SyntaxValidate;


/**
 * @desc  语法校验
 * @date 2015年3月14日-下午1:45:26
 */

public class RegLoginValidate  {
    /**
     * logback
     */
    private static Logger logger = LoggerFactory.getLogger(RegLoginValidate.class);

    /**
     * @desc 
     * @date 2015年3月14日-下午1:13:46
     * @param role
     * @param nickName
     * @param password
     * @param mobile
     * @param messageAuth
     * @param messageCache 
     * @return String 
     */
    public static Map<String,String>  registerSyntaxCheck(String role, String nickName, 
            String password,String passwordAgain,
            String mobile) {
        logger.info("registerSyntaxCheck():role:"+role+"  nickName:"+nickName+
                //"  password:"+password+"   passwordAgain:"+passwordAgain+
                "  mobile:"+mobile);
        Map<String,String> map = new HashMap<String,String>();
        
        /**
         * 1 检查角色
         */
        if(StringUtils.isBlank(role) || 
                (!role.equals("LOAN") &&
                        !role.equals("INVEST") )){
            map.put("role", "请选择角色");
        }
        
        /**
         * 2 检查昵称
         */
        String checkNickName = SyntaxValidate.checkNickName(nickName);
        if(StringUtils.isNotBlank(checkNickName)){
            map.put("nickName", checkNickName);
        }
        
        /**
         * 3 检查密码
         */
        if(StringUtils.isBlank(password)){
            map.put("password", "登录密码不能为空");
        }
        if(StringUtils.isBlank(passwordAgain)){
            map.put("passwordAgain", "重复密码不能为空");
        }
        if(StringUtils.isNotBlank(password) && StringUtils.isNotBlank(passwordAgain) && 
                !password.equals(passwordAgain)  ){
            map.put("password", "输入的两个密码不一致");
        }
        
        String checkPassword = SyntaxValidate.checkPassword(password);
        if(StringUtils.isNotBlank(checkPassword)){
            map.put("password", checkPassword);
        }
        
        
        /**
         * 3 检查手机
         */
        String checkMobile = SyntaxValidate.checkMobile(mobile);
        if(StringUtils.isNotBlank(checkMobile)){
            map.put("mobile", checkMobile);
        }
        logger.info("registerSyntaxCheck():map"+map);        
        return map;
    }


    /**
     * @desc 
     * @date 2015年3月19日-上午9:29:51
     * @param loginName
     * @param password
     * @return Map<String,String> 
     */
    public static Map<String, String> loginSyntaxCheck(String loginName, String password) {
        logger.info("loginSyntaxCheck():loginName:"+loginName);
        
        Map<String,String> map = new HashMap<String,String>();
        if(StringUtils.isBlank(loginName)){
            map.put("loginName", "请输入正确的邮箱地址或手机号码");
            return map;
        }
        //有一个校验通过即可
        String checkMobile = SyntaxValidate.checkMobile(loginName);
        String checkEmail = SyntaxValidate.checkEmail(loginName);
        if(StringUtils.isBlank(checkMobile) || StringUtils.isBlank(checkEmail)){
            return null;
        }
        map.put("loginName", "请输入正确的邮箱地址或手机号码");
        logger.info("loginSyntaxCheck():map"+map); 
        return map;
    }

    /**
     * @desc 
     * @date 2015年3月23日-下午5:59:40
     * @param password
     * @param passwordAgain
     * @return Map<String,String> 
     */
    public static Map<String, String> resetPasswordSyntaxCheck(String password, String passwordAgain) {
        // TODO Auto-generated method stub
        Map<String,String> map = new HashMap<String,String>();
        /**
         * 1 检查密码
         */
        if(StringUtils.isBlank(password)){
            map.put("password", "登录密码不能为空");
        }
        if(StringUtils.isBlank(passwordAgain)){
            map.put("passwordAgain", "您输入的密码不一致");
        }
        if(StringUtils.isNotBlank(password) && StringUtils.isNotBlank(passwordAgain) && 
                !password.equals(passwordAgain)  ){
            map.put("password", "输入的两个密码不一致");
        }
        String checkPassword = SyntaxValidate.checkPassword(password);
        if(StringUtils.isNotBlank(checkPassword)){
            map.put("password", checkPassword);
        }
        logger.info("resetPasswordSyntaxCheck():map"+map); 
        return map;
    }
}
