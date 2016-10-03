/**
 * @desc  
 * @date 2015年7月24日
 */
package tk.cpusoft.common.util.date;

import tk.cpusoft.common.base.BaseModel;

/**
 * @desc 根据前台的日历js转换到开始和结束时间
 * @date 2015年7月24日-下午2:05:28
 */
public class StartEndTime extends BaseModel{

    /**
     * @desc 
     * @date 2015年7月24日-下午2:05:42
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * @desc YYYY-MM-dd开始时间 
     * @date 2015年7月24日-下午2:06:21
     */
    private String startTime;
    
    /**
     * @desc YYYY-MM-dd结束时间 
     * @date 2015年7月24日-下午2:07:15
     */
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    

}
