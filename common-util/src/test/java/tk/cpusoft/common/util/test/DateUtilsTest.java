/**
 * @desc  
 * @date 2015年7月24日
 */
package tk.cpusoft.common.util.test;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.date.DateUtils;
import tk.cpusoft.common.util.date.StartEndTime;

/**
 * @desc 时间测试
 * @date 2015年7月24日-下午2:17:08
 */
public class DateUtilsTest {

    /**
     * @desc 测试天数 
     * @date 2015年7月24日-下午2:23:09 void 
     */
    @Test
    public void changeCalendarToStartEndTest(){
        String cal = "近7天";
        StartEndTime set = DateUtils.changeCalendarToStartEnd(cal);
        System.out.println(set);
        
        cal = "近365天";
        set = DateUtils.changeCalendarToStartEnd(cal);
        System.out.println(set);
        
        cal = "2014-7-25 至 2015-7-16";
        set = DateUtils.changeCalendarToStartEnd(cal);
        System.out.println(set);
        
        cal = "2014-7-25";
        set = DateUtils.changeCalendarToStartEnd(cal);
        System.out.println(set);
    }
    
    @Test
    public void test(){
        String r = DateUtils.getWeekOfDate("2016-08-26");
        System.out.println(r);
    }
    
    @Test
    public void calcDiffDays(){
        int r = DateUtils.calcDiffDays(1);
        System.out.println(r);
        
        r = DateUtils.calcDiffDays(24);
        System.out.println(r);
        
        r = DateUtils.calcDiffDays(25);
        System.out.println(r);
        
        r = DateUtils.calcDiffDays(28);
        System.out.println(r);
    }
}
