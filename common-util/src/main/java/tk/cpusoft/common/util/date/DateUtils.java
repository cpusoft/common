package tk.cpusoft.common.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期工具类
 * @author <a href="mailto:wangxin@knet.cn">北京王欣</a>
 * @version 1.0, 2012-5-25 下午12:01:26
 * @since 1.0
 */
public class DateUtils {
    /** 日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT = "yyMMdd";
    /** 日期时间格式 */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_TIME_FORMAT2 = "HHmmss";

    /** 短日期格式 **/
    public static final String DEFAULT_DATE_SHORT_FORMAT = "yyyyMMdd";
    public static final String DEFAULT_DATE_SHORT_FORMAT1 = "yyyy/MM/dd";

    public static final String YEAR_FORMAT = "yyyy";
    /** 每天小时数 */
    private static final long HOURS_PER_DAY = 24;
    /** 每小时分钟数 */
    private static final long MINUTES_PER_HOUR = 60;
    /** 每分钟秒数 */
    private static final long SECONDS_PER_MINUTE = 60;
    /** 每秒的毫秒数 */
    private static final long MILLIONSECONDS_PER_SECOND = 1000;
    /** 每分钟毫秒数 */
    private static final long MILLIONSECONDS_PER_MINUTE = MILLIONSECONDS_PER_SECOND * SECONDS_PER_MINUTE;
    /** 每天毫秒数 */
    private static final long MILLIONSECONDS_SECOND_PER_DAY = HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE * MILLIONSECONDS_PER_SECOND;

    public static TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");

    private DateUtils() {
    }

    /**
     * 将yyyy-MM-dd格式的字符串转换为日期对象
     * @param date 待转换字符串
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date) {
        return getDate(date, DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为日期对象
     * @param date 待转换字符串
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDateTime(String date) {
        return getDate(date, DEFAULT_DATETIME_FORMAT, null);
    }

    public static Date getDateTimeYYYY(String date) {
        return getDate(date, YEAR_FORMAT, null);
    }
    public static Date getDateTimeYYYYMMDD(String date) {
        return getDate(date, DEFAULT_DATE_SHORT_FORMAT, null);
    }

    /**
     * 将指定格式的字符串转换为日期对象
     * @param date 待转换字符串
     * @param format 日期格式
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date, String format) {
        return getDate(date, format, null);
    }

    /**
     * 将指定格式的字符串转换为日期对象
     * @param date 日期对象
     * @param format 日期格式
     * @param defVal 转换失败时的默认返回值
     * @return 转换后的日期对象
     */
    public static Date getDate(String date, String format, Date defVal) {
        if(StringUtils.isBlank(date) || StringUtils.isBlank(format)){
            return null;
        }
        Date d;
        try {
            d = new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            d = defVal;
        }
        return d;
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd格式的字符串
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT, null);
    }

    public static String YYMMDDDate(Date date) {
        return formatDate(date, DATE_FORMAT, null);
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd格式的字符串
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatShortDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将日期对象格式化成yyyyMMdd格式的字符串
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatYYYYMMDDDate(Date date) {
        return formatDate(date, DEFAULT_DATE_SHORT_FORMAT, null);
    }

    public static String formatYYYYMMDDDate1(Date date) {
        return formatDate(date, DEFAULT_DATE_SHORT_FORMAT1, null);
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd HH:mm:ss格式的字符串
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String forDatetime(Date date) {
        return formatDate(date, DEFAULT_DATETIME_FORMAT, null);
    }

    /**
     * 根据指定格式来展现年
     * @param date
     * @return
     */
    public static String forYearDatetime(Date date) {
        return formatDate(date, YEAR_FORMAT, null);
    }

    /**
     * 将日期对象格式化成HH:mm:ss格式的字符串
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatTime(Date date) {
        return formatDate(date, DEFAULT_TIME_FORMAT, null);
    }

    /**
     * 将日期对象格式化成HHmmss格式的字符串
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatTime2(Date date) {
        return formatDate(date, DEFAULT_TIME_FORMAT2, null);
    }

    /**
     * 将日期对象格式化成指定类型的字符串
     * @param date 待格式化日期对象
     * @param format 格式化格式
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatDate(Date date, String format) {
        return formatDate(date, format, null);
    }

    /**
     * 将传入的Date类型改为"yyyy-MM-dd HH:mm:ss"的String类型
     * @param date 日期
     * @return String "yyyy-MM-dd HH:mm:ss",如果传入的date为null，则返回null
     */
    public static String formatDateYYYYMMMDDHHMMSS(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat s = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return s.format(date);
    }

    /**
     * 带时区的格式化时间
     * @param date
     * @param format
     * @param timeZone
     * @return
     */
    public static String formatDateTimeZone(Date date, String format, TimeZone timeZone) {
        String ret = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setTimeZone(timeZone);
            ret = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 将日期对象格式化成指定类型的字符串
     * @param date 待格式化日期对象
     * @param format 格式化格式
     * @param defVal 格式化失败时的默认返回空
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date, String format, String defVal) {
        if(date==null || StringUtils.isBlank(format)){
            return null;
        }
        String ret;
        try {
            ret = new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            ret = defVal;
        }
        return ret;
    }

    /**
     * 返回指定日期加上days天后的日期
     * @param date
     * @param days
     * @return
     */
    public static Date plusDays(Date date, int days) {
        return changeDays(date, days);
    }

    public static Date plusDaysToday(int days) {
        return plusDays(getToday(), days);
    }

    public static Date minusDaysToday(int days) {
        return minusDays(getToday(), days);
    }

    /**
     * 添加到另外的时间
     * @param date
     * @param type Calendar.MONTH  Calendar.DATE  Calendar.YEAR
     * @param value 要修改的时间值：根据type不同而不同
     * @return
     */
    public static Date toOtherDate(Date date, 
            int type,
            int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(type, value);
        return cal.getTime();		
    }

    /**
     * 返回指定日期减去days天后的日期
     * @param date
     * @param days
     * @return
     */
    public static Date minusDays(Date date, int days) {
        return changeDays(date, -days);
    }

    private static Date changeDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    /**
     * 获取当前日期加时间
     * @return
     */
    public static Date getToday() {
        return new Date();
    }

    public static Date now() {
        return getToday();
    }

    public static long currentTimeMillis() {
        return new Date().getTime();
    }

    /**
     * 获得当前时间sql.date
     */
    public static java.sql.Date getTodaySqlDate() {
        return new java.sql.Date(getToday().getTime());
    }

    /**
     * 获取今天日期, 格式: YYYY-MM-DD
     * @return
     */
    public static String getTodayStr() {
        return formatDate(getToday(), DEFAULT_DATE_FORMAT);
    }

    /**
     * 比较传入日期与当前日期相差的天数
     * @param d
     * @return
     */
    public static int intervalDay(Date d) {
        return intervalDay(getToday(), d);
    }

    /**
     * 获取时间格式为yyyyMMdd
     * @return
     */
    public static String getShortDate() {
        return formatDate(getToday(), DEFAULT_DATE_SHORT_FORMAT);
    }

    /**
     * 获取时间格式为yyyyMMddHHmmss
     * @return
     */
    public static String getShortTime() {
        return formatDate(getToday(), DEFAULT_DATE_SHORT_FORMAT + DEFAULT_TIME_FORMAT2);
    }

    /**
     * 比较两个日期相差的天数
     * @param d1
     * @param d2
     * @return
     */
    public static int intervalDay(Date d1, Date d2) {
        long intervalMillSecond = setToDayStartTime(d1).getTime() - setToDayStartTime(d2).getTime();
        //相差的天数 = 相差的毫秒数 / 每天的毫秒数 (小数位采用去尾制)
        return (int) (intervalMillSecond / MILLIONSECONDS_SECOND_PER_DAY);
    }

    /**
     * 将时间调整到当天0:0:0
     * @param date
     * @return
     */
    private static Date setToDayStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 判断当前时间
     * @return
     */
    public static String getDateStatus() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour < 12) {
            return "morning";
        } else if (hour >= 12 && hour < 18) {
            return "noon";
        } else if (hour >= 18 && hour < 24) {
            return "evning";
        } else {
            return "midnight";
        }
    }

    /**
     * 获得两个日期之间相差的分钟数。（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return 返回两个日期之间相差的分钟数值
     */
    public static int intervalMinutes(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();

        //相差的分钟数 = 相差的毫秒数 / 每分钟的毫秒数 (小数位采用进位制处理，即大于0则加1)
        return (int) (intervalMillSecond / MILLIONSECONDS_PER_MINUTE + (intervalMillSecond % MILLIONSECONDS_PER_MINUTE > 0 ? 1 : 0));
    }

    /**
     * 获得两个日期之间相差的秒数差（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int intervalSeconds(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();

        return (int) (intervalMillSecond / MILLIONSECONDS_PER_SECOND + (intervalMillSecond % MILLIONSECONDS_PER_SECOND > 0 ? 1 : 0));
    }

    public static int getAge(Date birthday) {
        Calendar now = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthday);
        //取得生日年份
        int year = birth.get(Calendar.YEAR);
        //年龄
        int age = now.get(Calendar.YEAR) - year;
        //修正
        now.set(Calendar.YEAR, year);
        age = (now.before(birth)) ? age - 1 : age;
        return age;
    }

    /**
     * d1 和 d2 是同一天
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSameDate(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(d1.getTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(d2.getTime());

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 判断是否d2是d1的后一天
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isContinueDay(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        if (intervalDay(d1, d2) == 1)
            return true;
        return false;
    }

    /**
     * 得到没有时间的日期
     * @param date
     * @return
     */
    public static Date truncDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 
     * 得到旬.
     *
     * @param input
     * @return
     * @author <a href="mailto:wangxin@knet.cn">北京王欣</a>
     */
    public static String getCnDecade(Date input) {
        String day = formatDate(input);
        String decade = day.replaceAll("01日", "上旬").replaceAll("11日", "中旬").replaceAll("21日", "下旬");
        return decade;
    }

    public static Date getTodayZero() {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(new Date());
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date getTheDayBefore(Date date) {
        return new Date(date.getTime() - (long) 24 * (long) 60 * 60 * 1000);
    }

    public static Date[] getTenDayBefore() {//计算之前一旬的起止时间
        Date[] ret = new Date[2];
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(new Date());
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);//0点0分0秒
        int day = c.get(java.util.Calendar.DAY_OF_MONTH);
        if (day < 10) {//今天处在某月的上旬，起始时间是前一个月的21号，终止时间是本月的1号
            c.set(java.util.Calendar.DAY_OF_MONTH, 1);//本月的1号
            ret[1] = new Date(c.getTime().getTime());
            c.setTime(getTheDayBefore(c.getTime()));//往前翻一天，到上一个月
            c.set(java.util.Calendar.DAY_OF_MONTH, 21);
            ret[0] = new Date(c.getTime().getTime());
        } else {//

            if (10 < day && day <= 20) {//今天处在某月的中旬，起始时间是本月的1号，终止时间是本月的11号
                c.set(java.util.Calendar.DAY_OF_MONTH, 1);
                ret[0] = new Date(c.getTime().getTime());
                c.set(java.util.Calendar.DAY_OF_MONTH, 11);
                ret[1] = new Date(c.getTime().getTime());
            } else {//今天处在某月的下旬，起始时间是本月的11号，终止时间是本月的21号
                c.set(java.util.Calendar.DAY_OF_MONTH, 11);
                ret[0] = new Date(c.getTime().getTime());
                c.set(java.util.Calendar.DAY_OF_MONTH, 21);
                ret[1] = new Date(c.getTime().getTime());
            }
        }
        return ret;
    }

    public static Date[] getCurrentTenDay(Date input) {//计算某个输入时间的当前旬起止时间
        Date[] ret = new Date[2];
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(input);
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);//0点0分0秒
        int day = c.get(java.util.Calendar.DAY_OF_MONTH);
        if (day < 10) {//今天处在某月的上旬，起始时间是本月的1号，终止时间是本月的11号
            c.set(java.util.Calendar.DAY_OF_MONTH, 1);//本月的1号
            ret[0] = new Date(c.getTime().getTime());
            c.set(java.util.Calendar.DAY_OF_MONTH, 11);
            ret[1] = new Date(c.getTime().getTime());
        } else {//

            if (10 < day && day <= 20) {//今天处在某月的中旬，起始时间是本月的11号，终止时间是本月的21号
                c.set(java.util.Calendar.DAY_OF_MONTH, 11);
                ret[0] = new Date(c.getTime().getTime());
                c.set(java.util.Calendar.DAY_OF_MONTH, 21);
                ret[1] = new Date(c.getTime().getTime());
            } else {//今天处在某月的下旬，起始时间是本月的21号，终止时间是下个月的1号
                c.set(java.util.Calendar.DAY_OF_MONTH, 21);
                ret[0] = new Date(c.getTime().getTime());
                ret[1] = getNextMonthFirst(c.getTime());
            }
        }
        return ret;
    }

    public static Date getNextMonthFirst(Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);//0点0分0秒
        c.add(java.util.Calendar.MONTH, 1);//加一个月   
        c.set(java.util.Calendar.DATE, 1);//把日期设置为当月第一天
        return c.getTime();
    }

    public static Date[] getTheMonthBefore(Date date) {//计算之前一旬的起止时间
        Date[] ret = new Date[2];
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);//0点0分0秒
        c.set(java.util.Calendar.DAY_OF_MONTH, 1);//本月的1号
        ret[1] = new Date(c.getTime().getTime());
        c.setTime(getTheDayBefore(c.getTime()));//往前翻一天，到上一个月
        c.set(java.util.Calendar.DAY_OF_MONTH, 1);//上月的1号
        ret[0] = new Date(c.getTime().getTime());
        return ret;
    }

    /**
     * 格式化输入的日期，格式为：yyyy.MM.dd | yyyy-MM-dd
     * 
     * @return
     */
    public static String formatInputDate(Date curTime, String formate) {
        String mDateTime1 = "";
        if (null == curTime)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat(formate);
        mDateTime1 = formatter.format(curTime);
        return mDateTime1;
    }

    /**
     * 两个时间之间相差的月数
     * @param date1
     * @param date2
     * @return
     */
    public static int getMonths(Date date1, Date date2) {
        int iMonth = 0;
        int flag = 0;
        try {
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTime(date1);
            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTime(date2);
            if (objCalendarDate2.equals(objCalendarDate1))
                return 0;
            if (objCalendarDate1.after(objCalendarDate2)) {
                Calendar temp = objCalendarDate1;
                objCalendarDate1 = objCalendarDate2;
                objCalendarDate2 = temp;
            }
            if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))
                flag = 1;
            if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
                iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12 + objCalendarDate2.get(Calendar.MONTH) - flag) - objCalendarDate1.get(Calendar.MONTH);
            else
                iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return iMonth;
    }

    /**
     * 判断当前审核的信息是否已经过期
     * @param validEndDate
     * @param dbtime 数据库时间
     * @return
     */
    @SuppressWarnings("static-access")
    public static boolean expiredDate(Date validEndDate, Date dbtime) {
        Calendar today1 = Calendar.getInstance();
        //        Date dbtime = dbTimeDao.getDBTime();// 数据库时间
        today1.setTime(dbtime);
        today1.add(Calendar.DAY_OF_MONTH, 1);// 数据库“今天”后一天的取整天
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.set(today1.get(today1.YEAR), today1.get(today1.MONTH), today1.get(today1.DAY_OF_MONTH), 0, 0, 0);
        Calendar validPeriodEnd = Calendar.getInstance();
        validPeriodEnd.setTime(validEndDate);
        validPeriodEnd.set(Calendar.HOUR, 23); // 避免时分秒的差距
        // 注册系统传来的营业期限截至日期为整天整点，而后面的Calendar比较的是精确时间
        if (validPeriodEnd.before(tomorrow)) {// 审核日期已经过了营业截止日期
            return false;
        }
        return true;
    }


    /**
     * @desc 吧前端日历格式，转为起止时间
     * @date 2015年7月24日-下午2:16:34
     * @param calendar
     * @return StartEndTime 
     */
    public static StartEndTime changeCalendarToStartEnd(String calendar){
        StartEndTime set = new StartEndTime();
        if(StringUtils.isBlank(calendar)){
            return set;
        }
        calendar =  calendar.trim();
        String startTime=null,endTime=null;
        if(calendar.indexOf("至")>=0){
            String[] split = calendar.split("至");
            if(split.length==1){
                startTime = split[0].trim();
                endTime=split[0].trim();
            }else if(split.length==2){
                startTime = split[0].trim();
                endTime=split[1].trim();
            }else{
                return set;
            }
            startTime = DateUtils.formatDate(DateUtils.getDate(startTime));
            endTime = DateUtils.formatDate(DateUtils.getDate(endTime));
        }else if(calendar.indexOf("近")>=0){
            String tmp = calendar.replace("近", "").replace("天", "");
            Integer days = Integer.valueOf(tmp)-1;//要排除起点那天
            Date end = new Date();
            Date start = changeDays(end,-days);
            startTime = DateUtils.formatDate(start);
            endTime = DateUtils.formatDate(end);
        }else{
            //就是今天
            startTime = DateUtils.formatDate(DateUtils.getDate(calendar));
            endTime = DateUtils.formatDate(DateUtils.getDate(calendar));
        }
        set.setStartTime(startTime);
        set.setEndTime(endTime);
        return set;
    }
    
    /**
     * @desc 根据日期返回星期几 
     * @date 2015年10月16日-下午2:35:28
     * @param strDate
     * @return String 
     * add by WSl
     */
    public static String getWeekOfDate(String strDate) {
        Date date = getDate(strDate,DEFAULT_DATE_FORMAT);
        String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" }; 
        //String[] weekDaysCode = { "日", "一", "二", "三", "四", "五", "六" }; 
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date); 
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
        return weekDaysName[intWeek]; 
      } 
    
    /**
     * 计算到月底的时间  (秒)
     * @return
     */
	public static int getCountMills(){
		
		   Calendar calendar=Calendar.getInstance();   
		   calendar.setTime(new Date()); 
		   int countDay =  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		   int day = calendar.get(Calendar.DAY_OF_MONTH);
		   int hour = calendar.get(Calendar.HOUR_OF_DAY)+1;
		   int minute = calendar.get(Calendar.MINUTE)-1;
		   int scendMills = (countDay-day-1)*24*60*60+(24-hour)*60*60+(60-minute)*60;
		   return scendMills;
		   
	}
	
	public static int getAfterOneMonthSeconds(){
		Date date1 = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(Calendar.MONTH, 1);
		Date date2 = calendar.getTime();
		return intervalSeconds(date1, date2); 
	}
    
    /**
     * @desc 距离提醒日期还有多少天，不能有负数（此时需要按下个月提醒日期来计算） 
     * @date 2016年8月25日-上午8:21:08
     * @param reminderDay
     * @return int 
     */
    public static int calcDiffDays(int reminderDay){
        //计算diffDays
        Date date1 = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
        int diff = reminderDay - nowDay;
        if(diff>=0){
            return  diff;
        }else{
            int maxDay = calendar.getActualMaximum(Calendar.DATE);
            diff += maxDay;
            return diff;
        }
    }

}
