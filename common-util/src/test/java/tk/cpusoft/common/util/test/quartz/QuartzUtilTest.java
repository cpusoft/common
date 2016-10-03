/**
 * @desc  
 * @date 2016年8月24日
 */
package tk.cpusoft.common.util.test.quartz;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.quartz.QuartzUtil;



/**
 * @desc 
 * @date 2016年8月24日-下午2:29:19
 */
public class QuartzUtilTest {

    @Test
    public void test(){
        String periodType ="PERIOD_MONTH";//"PERIOD_WEEK";// "PERIOD_DAY";//"FIXED";
        String cycleDate = "2,3";
        String hhMMss = "23:31:52";//
        
        String rr;
        /*
        rr = QuartzUtil.getExpression(periodType, cycleDate, hhMMss,null);
        System.out.println(rr);
        
        rr = QuartzUtil.getExpression(periodType, cycleDate, hhMMss,-3L);
        System.out.println(rr);
        
        
        periodType ="PERIOD_WEEK";
        rr = QuartzUtil.getExpression(periodType, cycleDate, hhMMss,null);
        
        System.out.println(rr);
        rr = QuartzUtil.getExpression(periodType, cycleDate, hhMMss,-3L);
        System.out.println(rr);
        
        periodType ="PERIOD_DAY";
        cycleDate = "2,3";
        rr = QuartzUtil.getExpression(periodType, cycleDate, hhMMss,null);
        System.out.println(rr);
        */
        periodType ="FIXED";
        cycleDate = "2016-08-2";
        rr = QuartzUtil.getExpression(periodType, cycleDate, hhMMss,null);
        System.out.println(rr);
        rr = QuartzUtil.getExpression(periodType, cycleDate, hhMMss,-4L);
        System.out.println(rr);
        
    }
}
