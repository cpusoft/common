/**
 * @desc  
 * @date 2016年8月24日
 */
package tk.cpusoft.common.util.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tk.cpusoft.common.util.date.DateUtils;
import tk.cpusoft.common.util.dns.DnsUtil;

/**
 * @desc 
 * @date 2016年8月24日-上午9:52:24
 */
public class QuartzUtil {


    /**
     * logback
     */
    private static Logger logger = LoggerFactory.getLogger(QuartzUtil.class);

    private QuartzUtil(){}

    private final static int DAYS_PER_MONTH=28;
    private final static int DAYS_PER_WEEK=7;
    /**
     * @desc 按一个月28天计算
     * @date 2016年8月24日-上午10:08:38
     * @param periodType FIXED  PERIOD_DAY PERIOD_WEEK  PERIOD_MONTH
     * @param cycledate FIXED时固定格式：YYYY-MM-dd;   
     *                  PERIOD_DAY时忽略;    
     *                  PERIOD_WEEK时星期数,号分隔(2,3)周日是1开始计算;  
     *                  PERIOD_MONTH是按月的每日时间,号分隔(2,3);
     * @param hhMMss  时分秒HH:mm:ss
     * @param adjustDays  调整的日期: null忽略；负数为往前；正数为往后
     * @return String 
     */
    public static String getExpression(String periodType, String cycleDate, String hhMMss, Long adjustDays){
        logger.info("getExpression():periodType:"+periodType+"  cycleDate:"+cycleDate+"  hMMss:"+hhMMss);
        //  格式： [秒] [分] [小时] [日] [月] [周] ([年]) 
        String expression = null;
        try{
            if("FIXED".equalsIgnoreCase(periodType)){
                Date d = DateUtils.getDate(cycleDate+" "+hhMMss, "yyyy-MM-dd HH:mm:ss");
                if(d==null){
                    return null;
                }
                Date newDay = DateUtils.toOtherDate(d,Calendar.DATE,-3);
                logger.info("getExpression():固定日期FIXED:"+newDay);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(newDay);

                Calendar calendarNow = Calendar.getInstance();
                calendarNow.setTime(new Date());
                if(calendar.compareTo(calendarNow)<=0){
                    logger.error("getExpression():固定日期时，不能比当前日期还早: 当前日期calendarNow:"+calendarNow+"   设定日期calendar:"+calendar);
                    return null;
                }
                
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;//月份要+1
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
                int minute     = calendar.get(Calendar.MINUTE);
                int second     = calendar.get(Calendar.SECOND);
                expression = second+" "+minute+" "+hourOfDay+" "+day+" "+month+" ? "+year ;// DateUtils.formatDate(d, "ss mm HH dd MM ? yyyy");
                logger.info("getExpression():FIXED expression:"+expression);
                return expression;
            }else if("PERIOD_DAY".equalsIgnoreCase(periodType)){
                Date d = DateUtils.getDate(hhMMss, "HH:mm:ss");
                if(d==null){
                    return null;
                }
                expression = DateUtils.formatDate(d, "ss mm HH * * ?");
                logger.info("getExpression():PERIOD_DAY expression:"+expression);
                return expression;
            }else if("PERIOD_WEEK".equalsIgnoreCase(periodType)){
                Date d = DateUtils.getDate(hhMMss, "HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(d);

                int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
                int minute     = calendar.get(Calendar.MINUTE);
                int second     = calendar.get(Calendar.SECOND);

                if(adjustDays!=null){
                    String[] weekSplit = cycleDate.split(",");
                    StringBuffer buf = new StringBuffer();
                    for(String weekStr: weekSplit){
                        int week = Integer.valueOf(weekStr);
                        if(week<=0 || week>DAYS_PER_WEEK){
                            logger.info("getExpression():PERIOD_WEEK星期格式错误(1-7):"+week);
                            return null;
                        }
                        int newWeek = week+adjustDays.intValue();
                        if(newWeek<=0){
                            newWeek+=DAYS_PER_WEEK;
                        }else if(newWeek>DAYS_PER_WEEK){
                            newWeek-=DAYS_PER_WEEK;
                        }
                        buf.append(newWeek+",");
                    }
                    cycleDate = buf.substring(0,buf.length()-1);
                }


                expression = second +" "+minute+" "+hourOfDay +" ? * " + cycleDate ; 
                logger.info("getExpression():PERIOD_WEEK expression:"+expression);
                return expression;
            }else if("PERIOD_MONTH".equalsIgnoreCase(periodType)){
                Date d = DateUtils.getDate(hhMMss, "HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(d);

                int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
                int minute     = calendar.get(Calendar.MINUTE);
                int second     = calendar.get(Calendar.SECOND);

                if(adjustDays!=null){
                    String[] daySplit = cycleDate.split(",");
                    StringBuffer buf = new StringBuffer();
                    for(String dayStr: daySplit){
                        int day = Integer.valueOf(dayStr);
                        if(day<=0 || day>DAYS_PER_MONTH){
                            logger.info("getExpression():DAYS_PER_MONTH月份格式错误(1-28):"+day);
                            return null;
                        }
                        int newDay = day+adjustDays.intValue();
                        if(newDay<=0){
                            newDay+=DAYS_PER_MONTH;
                        }else if(newDay>DAYS_PER_MONTH){
                            newDay-=DAYS_PER_MONTH;
                        }
                        buf.append(newDay+",");
                    }
                    cycleDate = buf.substring(0,buf.length()-1);
                }

                expression = second +" "+minute+" "+hourOfDay +" "+cycleDate+" * ? " ; 
                logger.info("getExpression():PERIOD_MONTH expression:"+expression);
                return expression;
            } 
            logger.info("getExpression():类型无法识别:periodType:"+periodType);
            return null;
        }catch(Exception e){
            logger.info("getExpression():error:"+e.getMessage(),e);
            return null;
        }
        
    }
}
