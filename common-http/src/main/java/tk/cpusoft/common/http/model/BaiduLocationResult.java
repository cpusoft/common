/**
 * @desc  
 * @date 2016年4月13日
 */
package tk.cpusoft.common.http.model;

import tk.cpusoft.common.base.BaseModel;



/**
 * @desc 
 * @date 2016年4月13日-下午2:07:18
 */
public class BaiduLocationResult extends BaseModel {

    /**
     * @desc 
     * @date 2016年4月13日-下午2:07:43
     */
    private static final long serialVersionUID = 1L;
    
    
    private Long status;
    
    private Result result;
    
    
    public class Result extends BaseModel{
        /**
         * @desc 
         * @date 2016年4月13日-下午2:10:16
         */
        private static final long serialVersionUID = 1L;
        private String formatted_address;

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }
        
    }


    public Long getStatus() {
        return status;
    }


    public void setStatus(Long status) {
        this.status = status;
    }


    public Result getResult() {
        return result;
    }


    public void setResult(Result result) {
        this.result = result;
    }
    
}
