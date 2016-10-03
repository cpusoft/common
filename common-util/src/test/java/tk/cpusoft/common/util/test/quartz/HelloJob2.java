/**
 * @desc  
 * @date 2016年8月15日
 */
package tk.cpusoft.common.util.test.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @desc 
 * @date 2016年8月15日-上午10:48:58
 */
public class HelloJob2 implements Job{
 
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        // TODO Auto-generated method stub
        //job名
        String jobName = arg0.getJobDetail().getKey().getName();
        //执行时间，
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        String jobRunTime = sf.format(new Date());
        System.out.println("HelloJob2222222222类中的主任务内容如下："+jobName+"在时间："+jobRunTime+"执行");
         
    }

}
