/**
 * @desc  
 * @date 2016年8月5日
 */
package tk.cpusoft.common.util.response.AppManage;

import tk.cpusoft.common.base.BaseModel;

/**
 * @desc 
 * @date 2016年8月5日-上午10:04:25
 */
public class Opt extends BaseModel{

    /**
     * @desc 
     * @date 2016年8月5日-上午10:04:38
     */
    private static final long serialVersionUID = 1L;
    
    private Long code;
    private String msg;
   
    public static final Long CODE_SUCCESS = 0L; 
    public static final Long CODE_SERVICE = 100L;
    public static final Long CODE_NOT_FOUND = 102L;
    public static final Long CODE_NOT_LOGIN = 101L;
    public static final Long CODE_NOT_AUTH = 103L;
    public static final Long CODE_NOT_ACCESS = 1000L; //越权查询信息
    public static final Long CODE_VALIDATE_ERROR = 105L; //系统参数验证错误
    public static final Long CODE_SYSTEM = -100L;
    //上传错误
    public static final Long CODE_UPLOAD_ERROR = 203L;
    
    
    public Opt(){
        
    }
    public Opt(Long code,String msg){
        this.setCode(code);
        this.setMsg(msg);
    }
    
    
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Long getCode() {
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
    }
    
    

}
