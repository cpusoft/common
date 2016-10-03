/**
 * @desc  
 * @date 2015年6月4日
 */
package tk.cpusoft.common.util.rate.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import tk.cpusoft.common.base.BaseModel;

/**
 * @desc 
 * @date 2015年6月4日-上午11:41:43
 */
public class RateModel  extends BaseModel{
    
    /**
     * @desc 
     * @date 2015年6月4日-上午11:50:06
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * @desc 总本金 
     * @date 2015年6月4日-上午11:48:40
     */
    private BigDecimal capital;
    /**
     * @desc 贷款年利率
     * @date 2015年6月4日-上午11:48:48
     */
    private BigDecimal yearRate;
    /**
     * @desc 贷款月利率=贷款年限/12 
     * @date 2015年6月4日-上午11:48:54
     */
    private BigDecimal monthRate;

    /**
     * @desc 贷款月份
     * @date 2015年6月4日-上午11:49:34
     */
    private Long month;
    
    
    
    public BigDecimal getCapital() {
        return capital;
    }
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }
    public BigDecimal getYearRate() {
        return yearRate;
    }
    public void setYearRate(BigDecimal yearRate) {
        this.yearRate = yearRate;
        if(this.yearRate!=null){
            this.monthRate = yearRate.divide(new BigDecimal(12L),4,RoundingMode.HALF_UP).setScale(4);
        }else{
            this.monthRate = null;
        }
    }
    public BigDecimal getMonthRate() {
        return monthRate;
    }
    public void setMonthRate(BigDecimal monthRate) {
        this.monthRate = monthRate;
        if(this.monthRate!=null){
            this.yearRate = monthRate.multiply(new BigDecimal(12L));
        }else{
            this.yearRate=null;
        }
    }

    public Long getMonth() {
        return month;
    }
    public void setMonth(Long month) {
        this.month = month;
    }
    
    
    
    
}
