/**
 * @desc  利率计算器
 * @date 2015年6月4日
 */
package tk.cpusoft.common.util.rate;

import java.math.BigDecimal;
import java.math.RoundingMode;

import tk.cpusoft.common.util.rate.model.RateModel;
import tk.cpusoft.common.util.rate.model.RateMonthResult;
import tk.cpusoft.common.util.rate.model.RateResult;

/**
 * @desc 利率计算器
 * @date 2015年6月4日-上午11:29:43
 */
public class RateCalcUtil {
    
    /**
     * @desc 等额本息计算 http://www.what21.com/programming/java/java-algorithm/rate2.html  
     * @date 2015年6月5日-下午2:25:42
     * @param rateModel void 
     */
    public static RateResult averageCapitalAndInterest(RateModel rateModel) {
        RateResult rateResult = new RateResult();
        
        BigDecimal one = BigDecimal.ONE;

        // 每月本息金额  = (本金×月利率×(1＋月利率)＾还款月数)÷ ((1＋月利率)＾还款月数-1)
        
        // 每月指数 = (1＋月利率)＾还款月数
        BigDecimal monthPow =  one.add(rateModel.getMonthRate()).pow(rateModel.getMonth().intValue());
        //
        //分子= (本金×月利率×每月指数)
        BigDecimal numerator = rateModel.getCapital() .multiply(rateModel.getMonthRate()).multiply( monthPow) ;
        //分母 =  (每月指数-1)
        BigDecimal denominator = monthPow.subtract(one);
        //每月本息金额  = (本金×月利率×每月指数)÷ (每月指数-1)
        BigDecimal capitalAndInterestMonth = numerator.divide(denominator,2, RoundingMode.HALF_UP);
        rateResult.setCapitalAndInterestMonth(capitalAndInterestMonth);
        
        
        
        
        // 每月本金 = (本金×月利率×(1+月利率)^(还款月序号-1))÷((1+月利率)^还款月数-1)
        BigDecimal remainNoPaidCapital = rateModel.getCapital();
        BigDecimal calcCapital = BigDecimal.ZERO;
        BigDecimal calcInterest = BigDecimal.ZERO;
        BigDecimal monthCapital = BigDecimal.ZERO;
        for(int i=1; i<=rateModel.getMonth(); i++){
            RateMonthResult rateMonthResult = new RateMonthResult();
            rateMonthResult.setIndex(Long.valueOf(i));
            
            //每月利息：
            //先求每月未支付时的剩余的本金= 剩余本金 * 月利率，然后 月利息 =  剩余本金*利率
            remainNoPaidCapital = remainNoPaidCapital .subtract( monthCapital );
            BigDecimal monthInterest = remainNoPaidCapital .multiply( rateModel.getMonthRate());
            rateMonthResult.setInterest(monthInterest.setScale(2, RoundingMode.HALF_UP));
            
            
            //每月本金：
            //(1+月利率)^(还款月序号-1)
            BigDecimal monthPower1 = one.add(rateModel.getMonthRate()).pow(i-1); 
            //((1+月利率)^还款月数-1)
            BigDecimal monthPower2 =  one.add(rateModel.getMonthRate()).pow(rateModel.getMonth().intValue()).subtract(one);
            monthCapital = rateModel.getCapital() .multiply( rateModel.getMonthRate()) .multiply(monthPower1).divide(monthPower2,4, RoundingMode.HALF_UP);
            rateMonthResult.setCapital(monthCapital.setScale(2, RoundingMode.HALF_UP));
            
            //每月支付后，还待支付本金 = 总剩余本金 - 本月本金
            rateMonthResult.setRemainCapital(remainNoPaidCapital.subtract(monthCapital).setScale(2, RoundingMode.HALF_UP));
            
            //每月应付款=每月本金+每月利息
            rateMonthResult.setCapitalAndInterest(monthInterest.add(monthCapital).setScale(2, RoundingMode.HALF_UP));
            rateResult.addReateMonthResult(rateMonthResult);
            
            //循环累计
            calcCapital = calcCapital.add(monthCapital);
            calcInterest = calcInterest.add(monthInterest);
            
        }
        rateResult.setCapitalSum(calcCapital.setScale(2, RoundingMode.HALF_UP));
        rateResult.setInterestSum(calcInterest.setScale(2, RoundingMode.HALF_UP));
        rateResult.setCapitalAndInterestMonth(calcCapital.add(calcInterest).setScale(2, RoundingMode.HALF_UP));
        
        return rateResult;
    }
    
    /**
     * @desc 等额本金 http://www.what21.com/programming/java/java-algorithm/rate3.html 
     * @date 2015年6月5日-下午4:05:07
     * @param rateModel
     * @return RateResult 
     */
    public static RateResult averageCapital(RateModel rateModel) {
        RateResult rateResult = new RateResult();
        
        BigDecimal one = BigDecimal.ONE;
        
        // 每月本息金额  = (贷款本金÷还款月数) + (贷款本金-已归还本金累计额)×月利率
        // 每月本金 = 贷款本金÷还款月数
        // 每月利息 = (贷款本金-已归还本金累计额)×月利率
        BigDecimal calcCapital = BigDecimal.ZERO;
        BigDecimal calcInterest = BigDecimal.ZERO;
        BigDecimal accumulateCapital = BigDecimal.ZERO;
        for(int i=1; i<=rateModel.getMonth(); i++){
            RateMonthResult rateMonthResult = new RateMonthResult();
            rateMonthResult.setIndex(Long.valueOf(i));
            
            //每月本息金额  = (贷款本金÷还款月数) + (贷款本金-已归还本金累计额)×月利率
            //先求没有的还款本金 = (贷款本金÷还款月数)
            BigDecimal monthCapital =  rateModel.getCapital().divide(new BigDecimal(rateModel.getMonth()),4, RoundingMode.HALF_UP);
            rateMonthResult.setCapital(monthCapital.setScale(2, RoundingMode.HALF_UP));
            
            //然后求利息  = (贷款本金-已归还本金累计额)×月利率
            BigDecimal monthInterest = rateModel.getCapital().subtract(accumulateCapital).multiply(rateModel.getMonthRate());
            rateMonthResult.setInterest(monthInterest.setScale(2, RoundingMode.HALF_UP));
            
            //每月的还款额 =  每月本金 + 每月利息
            BigDecimal capitalAndInterestMonth = monthCapital.add(monthInterest );
            rateMonthResult.setCapitalAndInterest(capitalAndInterestMonth.setScale(2, RoundingMode.HALF_UP));
            
            //累计本金
            accumulateCapital = accumulateCapital.add( monthCapital );
            
            //剩余本金 = 总剩余本金 - 本月本金
            BigDecimal remainCapital = rateModel.getCapital().subtract(accumulateCapital);
            rateMonthResult.setRemainCapital(remainCapital.setScale(2, RoundingMode.HALF_UP));
            rateResult.addReateMonthResult(rateMonthResult);
            
            //循环累计
            calcCapital = calcCapital.add(monthCapital);
            calcInterest = calcInterest.add(monthInterest);
        }
        rateResult.setCapitalSum(calcCapital.setScale(2, RoundingMode.HALF_UP));
        rateResult.setInterestSum(calcInterest.setScale(2, RoundingMode.HALF_UP));
        rateResult.setCapitalAndInterestMonth(calcCapital.add(calcInterest).setScale(2, RoundingMode.HALF_UP));         
        return rateResult;
    }
}
