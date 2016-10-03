/**
 * @desc  
 * @date 2016年8月2日
 */
package tk.cpusoft.common.util.idcard;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import tk.cpusoft.common.util.date.DateUtils;

/**
 * @desc 
 * @date 2016年8月2日-上午9:31:17
 */
public class IdCardUtil {
    
    public static Date getBirthday(String idCard){
        if(StringUtils.isBlank(idCard) || idCard.length()!=18){
            return null;
        }
        String year = idCard.substring(6,10);          //出生的年份
        String month = idCard.substring(10,12);       //出生的月份
        String day = idCard.substring(12,14);         //出生的日期
        Date date = DateUtils.getDate(year+"-"+month+"-"+day);
        return date;
    }
    public static int getAge(String idCard){
        if(StringUtils.isBlank(idCard) || idCard.length()!=18){
            return 0;
        }
        Date date = getBirthday(idCard);
        Date now = new Date();
        return now.getYear()-date.getYear();
    }
}
