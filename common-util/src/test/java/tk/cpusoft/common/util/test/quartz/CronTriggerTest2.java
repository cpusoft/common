/**
 * @desc  
 * @date 2016年8月15日
 */
package tk.cpusoft.common.util.test.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @desc 
 * @date 2016年8月15日-上午10:46:22
 */
public class CronTriggerTest2 {

    /**
     * @desc 
     * @date 2016年8月15日-上午10:46:22
     * @param args void 
     * @throws SchedulerException 
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        CronTriggerTest2 att = new CronTriggerTest2();
        att.run();
    }
    public void run() throws SchedulerException, ParseException{


        String dataStr = "23:31:52";//
        String express = dataStr;
        String type ="PERIOD_MONTH";//"PERIOD_WEEK";// "PERIOD_DAY";//"FIXED";
        if("FIXED".equalsIgnoreCase(type)){
            dataStr = "2016-8-15 13:43:53";
            SimpleDateFormat fixedFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = fixedFormat.parse(dataStr);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int   year   =   c.get(Calendar.YEAR);  
            int   month   =   c.get(Calendar.MONTH)+1;  
            int   day   =   c.get(Calendar.DAY_OF_MONTH);  
            
            Calendar nowC = Calendar.getInstance();
            fixedFormat = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
            express = fixedFormat.format(d);
            System.out.println(express); 
        }else if("PERIOD_DAY".equalsIgnoreCase(type)){
            dataStr = "13:49:53";
            SimpleDateFormat fixedFormat = new SimpleDateFormat("HH:mm:ss");
            Date d = fixedFormat.parse(dataStr);
            
            
            fixedFormat = new SimpleDateFormat("ss mm HH * * ?");
            express = fixedFormat.format(d);
            System.out.println(express); 
        }else if("PERIOD_WEEK".equalsIgnoreCase(type)){
            String cycledate = "2,3";
            dataStr = "14:12:53";
            SimpleDateFormat fixedFormat = new SimpleDateFormat("HH:mm:ss");
            Date d = fixedFormat.parse(dataStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            int year       = calendar.get(Calendar.YEAR);
            int month      = calendar.get(Calendar.MONTH); 
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // Jan = 0, not 1
            int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
            int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
            int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);

            int hour       = calendar.get(Calendar.HOUR);        // 12 hour clock
            int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
            int minute     = calendar.get(Calendar.MINUTE);
            int second     = calendar.get(Calendar.SECOND);
            int millisecond= calendar.get(Calendar.MILLISECOND);
            
            fixedFormat = new SimpleDateFormat("ss mm HH * * ");
            express = second +" "+minute+" "+hourOfDay +" ? * " + cycledate ; //星期要+1
            System.out.println(express); 
        }else if("PERIOD_MONTH".equalsIgnoreCase(type)){
            String cycledate = "2,3";
            dataStr = "14:12:53";
            SimpleDateFormat fixedFormat = new SimpleDateFormat("HH:mm:ss");
            Date d = fixedFormat.parse(dataStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            int year       = calendar.get(Calendar.YEAR);
            int month      = calendar.get(Calendar.MONTH); 
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // Jan = 0, not 1
            int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
            int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
            int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);

            int hour       = calendar.get(Calendar.HOUR);        // 12 hour clock
            int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
            int minute     = calendar.get(Calendar.MINUTE);
            int second     = calendar.get(Calendar.SECOND);
            int millisecond= calendar.get(Calendar.MILLISECOND);
            
            fixedFormat = new SimpleDateFormat("ss mm HH * * ");
            express = second +" "+minute+" "+hourOfDay +" ? * " + cycledate ; //星期要+1
            System.out.println(express); 
        }
        
        
        

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();




        JobDetail job1 = JobBuilder.newJob(HelloJob.class).withIdentity("job1","group1").build();
        CronTrigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger1","group1").withSchedule(CronScheduleBuilder.cronSchedule(express)).build();
        
        
        
        Date ft1 = scheduler.scheduleJob(job1, trigger1);
       
        System.out.println(job1.getKey().getName()+"任务将在执行，其中包含的Cron表达式"+trigger1.getCronExpression());


        scheduler.start();//启动调度器
        try {
            Thread.sleep(9999*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        scheduler.shutdown(true);
        //在休眠的一定时间内，显示执行的任务类个数【通过调度器获取到SchedulerMetaDate对象】
        System.out.println("~~~~~~~~~~~~~~~~关闭调度~~~~~~~~~~~~~~");
        SchedulerMetaData smd = scheduler.getMetaData();
        System.out.println("总共执行了"+smd.getNumberOfJobsExecuted()+"个任务");
         
    }

}
