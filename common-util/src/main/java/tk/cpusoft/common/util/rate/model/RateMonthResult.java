/**
 * @desc  
 * @date 2015年6月5日
 */
package tk.cpusoft.common.util.rate.model;

import java.math.BigDecimal;

import tk.cpusoft.common.base.BaseModel;

/**
 * @desc 每月的还款
 * @date 2015年6月5日-下午3:17:38
 */
public class RateMonthResult extends BaseModel{

    /**
     * @desc 
     * @date 2015年6月5日-下午3:21:31
     */
    private static final long serialVersionUID = 1L;

    /**
     * @desc 从1开始 
     * @date 2015年6月5日-下午3:18:53
     */
    private Long index;
    
    /**
     * @desc 每月利息 
     * @date 2015年6月5日-下午3:19:18
     */
    private BigDecimal interest;
    
    /**
     * @desc 每月本金 
     * @date 2015年6月5日-下午3:19:28
     */
    private BigDecimal capital;
    
    /**
     * @desc 每月还款额=利息+本金 
     * @date 2015年6月5日-下午3:20:00
     */
    private BigDecimal capitalAndInterest;
    
    /**
     * @desc 每月剩余本金
     * @date 2015年6月5日-下午3:20:40
     */
    private BigDecimal remainCapital;

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getCapitalAndInterest() {
        return capitalAndInterest;
    }

    public void setCapitalAndInterest(BigDecimal capitalAndInterest) {
        this.capitalAndInterest = capitalAndInterest;
    }

    public BigDecimal getRemainCapital() {
        return remainCapital;
    }

    public void setRemainCapital(BigDecimal remainCapital) {
        this.remainCapital = remainCapital;
    }

  
    
    
    
    
}
