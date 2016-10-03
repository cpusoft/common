/**
 * @desc  
 * @date 2015年6月4日
 */
package tk.cpusoft.common.util.rate.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import tk.cpusoft.common.base.BaseModel;

/**
 * @desc 
 * @date 2015年6月4日-上午11:41:52
 */
public class RateResult extends BaseModel{

    /**
     * @desc 
     * @date 2015年6月5日-下午3:21:38
     */
    private static final long serialVersionUID = 1L;
    /**
     * @desc 累计本金 
     * @date 2015年6月5日-下午3:15:43
     */
    private BigDecimal capitalSum;
    /**
     * @desc 累计利息 
     * @date 2015年6月5日-下午3:15:50
     */
    private BigDecimal interestSum;
    
    /**
     * @desc 每月的还款额=本金+利息 （等额本息才有) 
     * @date 2015年6月5日-下午3:16:45
     */
    private BigDecimal capitalAndInterestMonth;
    
    /**
     * @desc 每月的还款明细
     * @date 2015年6月5日-下午3:21:57
     */
    private List<RateMonthResult> rateMonthResultList;

    public BigDecimal getCapitalSum() {
        return capitalSum;
    }

    public void setCapitalSum(BigDecimal capitalSum) {
        this.capitalSum = capitalSum;
    }

    public BigDecimal getInterestSum() {
        return interestSum;
    }

    public void setInterestSum(BigDecimal interestSum) {
        this.interestSum = interestSum;
    }

    public BigDecimal getCapitalAndInterestMonth() {
        return capitalAndInterestMonth;
    }

    public void setCapitalAndInterestMonth(BigDecimal capitalAndInterestMonth) {
        this.capitalAndInterestMonth = capitalAndInterestMonth;
    }

    public List<RateMonthResult> getRateMonthResultList() {
        return rateMonthResultList;
    }

    public void setRateMonthResultList(List<RateMonthResult> rateMonthResultList) {
        this.rateMonthResultList = rateMonthResultList;
    }

    public void addReateMonthResult(RateMonthResult rateMontResult){
        if(this.rateMonthResultList==null){
            rateMonthResultList = new ArrayList<RateMonthResult>();
        }
        rateMonthResultList.add(rateMontResult);
    }
    
}
