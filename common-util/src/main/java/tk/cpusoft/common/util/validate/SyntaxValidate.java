/**
 * @desc  
 * @date 2015年3月14日
 */
package tk.cpusoft.common.util.validate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @desc 对输入项进行格式语法校验。
 * @date 2015年3月14日-上午11:24:56
 */
public class SyntaxValidate {
    private static Logger logger = LoggerFactory.getLogger(SyntaxValidate.class);
    /**
     * @desc  对传入值进行交易：最短，最长</br>     *  
     * @date 2015年3月14日-上午11:28:45
     * @param txt  传入的字符串,需要调用者自行对传入的txt是否要trim。 txt为null或者""时返回未通过校验
     * @param minLen >=最小值，当<0时，不判断
     * @param maxLen <=最大值，当<0时，不判断
     * @return String 为null时，校验通过，不为空时，校验失败的原因
     */
    public static String checkTxtLen(String txt, int minLen, int maxLen){
        boolean isOk = true;

        if(StringUtils.isBlank(txt)){
            isOk = false;
        }else{
            if (minLen >= 0 &&  txt.length() < minLen){
                isOk = false;
            }
            if  (maxLen >= 0 && txt.length() > maxLen ){
                isOk = false;
            }
        }
        if(!isOk){
            return "长度为"+minLen+"-"+maxLen+"个字符之间";
        }else{
            return null;
        }
    }

    private static final String checkAllowCnAndDigitalAndAlphabetAndUnderlined = "^[a-zA-Z0-9_\u4e00-\u9fa5]+$";
    public static final String CHECK_ALLOW_CN_DIGITAL_ALPHABET = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
    public static final String CHECK_ALLOW_DIGITAL_ALPHABET = "^[a-zA-Z0-9]+$";

    /**
     * @desc 检查长度和是否有非法字符  
     * @date 2015年7月2日-下午3:16:17
     * @param str
     * @param minLen
     * @param maxLen
     * @param regex
     * @return String 
     */
    public static String checkLengthAndRegex(String str,int minLen,int maxLen,String regex){
        StringBuffer buf = new StringBuffer();
        String lenCheck = checkTxtLen(str,minLen,maxLen);
        if(lenCheck!=null){
            buf.append("长度不符合要求;");
        }
        if(StringUtils.isNotBlank(str)){
            Pattern p = Pattern.compile(regex);
            if(! p.matcher(str).find()){
                buf.append("有非法字符;");
            }
        }
        if(buf.length()>0){
            return buf.toString();
        }else{
            return null;
        }
    }

    /** 
     * @desc 检查昵称，只能为中文、数字、字母、下划线 .(?还有问题待查）
     * @date 2015年3月14日-上午11:50:06
     * @param nickName
     * @return String 
     */
    public static String checkNickName(String nickName){
        String lenCheck = checkTxtLen(nickName,4,16);
        if(lenCheck!=null){
            return lenCheck;
        }
        Pattern p = Pattern.compile(checkAllowCnAndDigitalAndAlphabetAndUnderlined);
        if(! p.matcher(nickName).find()){
            return "只能为中文、数字、字母、下划线";
        }
        return null;

    }

    private static final String checkAllowDigitalAndAlphabetAndUnderlined = "^[a-zA-Z0-9]+$";
    private static final String checkAllowDigital = "^[0-9]+$";
    private static final String checkSameAlphabet = "([a-z])\\1{1,}";

    /**
     * @desc 密码检查，只能包含字母数字下划线(?还有问题待查）
     * @date 2015年3月14日-下午12:10:49
     * @param password
     * @return String 
     */
    public static String checkPassword(String password){
        //当已经sha1时，就不检查长度了; sha1长度为40
        if(StringUtils.isNotBlank(password) && password.length()==40){

        }else{
            String lenCheck = checkTxtLen(password,8,16);
            if(lenCheck!=null){
                return lenCheck;
            }
        }

        Pattern p = Pattern.compile(checkAllowDigitalAndAlphabetAndUnderlined);
        if(! p.matcher(password).find()){
            return "只能包含字母数字下划线";
        }

        p = Pattern.compile(checkAllowDigital);
        if( p.matcher(password).find()){
            return "密码不能全为数字";
        }
        p = Pattern.compile(checkSameAlphabet);
        if( p.matcher(password).find()){
            //  return "密码不能为同一字母"; //shaodebug
        }
        return null;
    }

    private static final String checkMobile="^[0-9]{11}$";
    /**
     * @desc 手机号码检查 
     * @date 2015年3月14日-下午1:39:07
     * @param mobile
     * @return String 
     */
    public static String checkMobile(String mobile){
        if( StringUtils.isBlank(mobile)){
            return "手机号不能为空";
        }
        Pattern p = Pattern.compile(checkMobile);
        if(! p.matcher(mobile).find()){
            return "手机号格式错误";
        }
        return null;
    }

    /**
     * @desc 邮箱检查 
     * @date 2015年3月14日-下午1:39:07
     * @param email
     * @return String 
     */
    public static String checkEmail(String email){
        if( StringUtils.isBlank(email)){
            return "邮箱不能为空";
        }

        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            if( matcher.matches()){
                return null;
            }else{
                return "邮箱格式错误";
            }
        }catch(Exception e){
            return "邮箱格式错误";
        }
    }

    private static final String checkNormalString="^[\\u4e00-\\u9fa5\\w\\-/]+$";
    /**
     * @desc 必须是字母、数字、汉字、- /  不能特殊字符，不能空格
     * @date 2015年5月4日-下午6:12:48
     * @param txt
     * @return String 
     */
    public static String checkNormalString(String txt){
        if( StringUtils.isBlank(txt)){
            return "不能为空";
        }
        Pattern p = Pattern.compile(checkNormalString);
        if(! p.matcher(txt).find()){
            return "格式错误";
        }
        return null;
    }


    /**
     * @desc 检查格式：所有属性的字符串类型（允许为空）
     * @date 2015年5月4日-下午6:37:58
     * @param object
     * @return Map 
     */
    public static Map checkNormalStringField(Object object){
        Map map = new HashMap();
        if(object==null){
            return map;
        }

        Field[] field = object.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
        try {
            for (int j = 0; j < field.length; j++) { // 遍历所有属性
                String nameOrg = field[j].getName(); // 获取属性的名字
                String name = nameOrg.substring(0, 1).toUpperCase() + nameOrg.substring(1); // 将属性的首字符大写，方便构造get，set方法
                String type = field[j].getGenericType().toString(); // 获取属性的类型
                if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                    Method m = object.getClass().getMethod("get" + name);
                    String value = (String) m.invoke(object); // 调用getter方法获取属性值
                    if (StringUtils.isNotBlank(value)) {
                        String check = SyntaxValidate.checkNormalString(value);
                        if(StringUtils.isNotBlank(check)){
                            map.put(nameOrg, check);
                        }
                    }
                }
            }
            logger.info("checkUserDetail():map:" + map);
            return map;

        } catch (Exception e) {
            logger.error("checkUserDetail():e:" + e.getMessage(), e);
            return null;
        }

    }


    /**
     * @desc 查询Long 
     * @date 2015年7月3日-下午2:46:45
     * @param value 
     * @param min 为null时不限制
     * @param max 为null时不限制
     * @return String 
     */
    public static String checkLong(Long value, Long min,Long max){
        if(value==null){
            return "不能为空";
        }
        if(min!=null && value.longValue() < min.longValue()){
            return "过小";
        }
        if(max!=null && value.longValue() > max.longValue()){
            return "过大";
        }
        return null;
    }

    /**
     * @desc Long比较
     * @date 2015年7月3日-下午2:56:35
     * @param min
     * @param max
     * @return String 
     */
    public static String compareLong(Long min,Long max){
        if(min==null || max==null){
            return "不能为空";
        }
        if(min.longValue()>max.longValue()){
            return "小值不能大于大值";
        }
        return null;
    }
}
