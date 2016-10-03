/**
 * @desc  
 * @date 2016年3月25日
 */
package tk.cpusoft.common.util.response;

/**
 * @desc 返回编码：比ReplyResult次一级的 
 * @date 2016年3月25日-上午10:45:05
 */
public enum ReplyCode {
    /**
     * 编码分几类
     * OK
     * ERR_ALARM_**异常并且需要触发告警的
     * ERR_NOALARM_**异常，但是不需要触发告警的
     */
    OK("OK","成功"),
    ERR_ALARM_PARAM("ERR_PARAM","参数异常"),
    ERR_ALARM_NOLOGIN("ERR_LOGIN","未登录"),
    ERR_ALARM_NULL("ERR_NULL","空指针异常"),
    ERR_ALARM_ORACLE("ERR_ORACLE","oracle异常"),
    ERR_ALARM_DATABSE("ERR_DATABASE","数据库异常"),
    ERR_ALARM_CONNECT("ERR_CONNECT","网络链接异常"),
    ERR_ALARM_BUSINESS("ERR_BUSINESS","业务异常"),
    ERR_ALARM_OTHER("ERR_OTHER","未知异常"),
    
    ERR_NOALARM_BUSINESS("ERR_NOALARM_BUSINESS","业务异常");
    
    private String code;
    private String explain;
    
    private ReplyCode(String code, String explain) {
        this.code = code;
        this.explain = explain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

 

}
