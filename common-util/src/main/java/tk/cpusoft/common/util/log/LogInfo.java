package tk.cpusoft.common.util.log;

import tk.cpusoft.common.util.response.ReplyCode;


/**
 * 对外服务日志信息Bean
 * 
 * @desc
 * @date 2015-6-10-下午5:29:58
 */
public class LogInfo extends BaseLog{

    /**
     * @desc
     * @date 2015-6-10-下午5:28:47
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * @desc 日志编码：enum枚举 
     * @date 2016年3月27日-下午1:31:58
     */
    private ReplyCode replyCode;
    
    private String account;// 登陆账号（访客）
    private String loginTime;// 登陆时间
    private String functionDes;// 访问功能描述
    private String functionUrl;// 访问URL
    private String accessReturnState;// 访问返回状态（成功或失败）
    private String stayLongTime;// 停留时间
    private String reference;// 访问连接来源（之前的页面）
    private String downloadNum;// 下载次数
    
    private String param;// 参数:request中取出的原始参数
    private String model;//model对象信息记录：接口传入
    private String result;// 结果集
    private String exceptionMessage;// 错误信息记录
    private String exceptionStackTrace; //错误堆栈信息
    
    
    
    

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getFunctionDes() {
        return functionDes;
    }

    public void setFunctionDes(String functionDes) {
        this.functionDes = functionDes;
    }

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    public String getAccessReturnState() {
        return accessReturnState;
    }

    public void setAccessReturnState(String accessReturnState) {
        this.accessReturnState = accessReturnState;
    }

    public String getStayLongTime() {
        return stayLongTime;
    }

    public void setStayLongTime(String stayLongTime) {
        this.stayLongTime = stayLongTime;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(String downloadNum) {
        this.downloadNum = downloadNum;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }



    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public static void main(String[] args) {
        LogInfo logInfo= new LogInfo();
        System.out.println(logInfo.toString());
    }

    public ReplyCode getLogCode() {
        return replyCode;
    }

    public void setLogCode(ReplyCode replyCode) {
        this.replyCode = replyCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionStackTrace() {
        return exceptionStackTrace;
    }

    public void setExceptionStackTrace(String exceptionStackTrace) {
        this.exceptionStackTrace = exceptionStackTrace;
    }
    
}
