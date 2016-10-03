/**
 * @desc  
 * @date 2015年6月5日
 */
package tk.cpusoft.common.util.test;

import java.math.BigDecimal;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.rate.RateCalcUtil;
import tk.cpusoft.common.util.rate.model.RateModel;
import tk.cpusoft.common.util.rate.model.RateResult;

/**
 * @desc 
 * @date 2015年6月5日-下午1:19:52
 */
public class RateCalcTest {

    @Test
    public void averageCapitalAndInterestTest(){
        RateModel rm = new RateModel();
        rm.setCapital(new BigDecimal(10000L));
        rm.setMonth(12L);
        rm.setYearRate(new BigDecimal("0.12"));
        RateResult rr = RateCalcUtil.averageCapitalAndInterest(rm);
        System.out.println(rr);
    }
    
    
    @Test
    public void averageCapitalTest(){
        RateModel rm = new RateModel();
        rm.setCapital(new BigDecimal(10000L));
        rm.setMonth(12L);
        rm.setYearRate(new BigDecimal("0.12"));
        RateResult rr = RateCalcUtil.averageCapital(rm);
        System.out.println(rr);
    }
}
