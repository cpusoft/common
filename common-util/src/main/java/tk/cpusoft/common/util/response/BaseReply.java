/**
 * @desc
 * @date 2015年2月9日
 */
package tk.cpusoft.common.util.response;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import tk.cpusoft.common.base.BaseModel;

/**
 * @desc 比较简单的返回消息：
 * @date 2015年2月9日-上午9:33:02
 */
public class BaseReply  extends BaseModel{



    /**
     * @desc 
     * @date 2016年3月16日-下午4:52:15
     */
    private static final long serialVersionUID = 1L;
    


    /**
     * 
     * @desc 大的：处理成功或失败:ResponseResult.ok,ResponseResult.fail
     * @date 2015年2月10日-上午8:57:37
     */
    protected ReplyResult result;

    /**
     * @desc 比replyResult更细化些：固定的一些错误编码 
     * @date 2016年3月16日-下午4:54:28
     */
    protected ReplyCode code;
    
    /**
     * @desc result为ok fail时的，比replyCode更加详细的细节，自定义使用
     * @date 2015年3月29日-下午2:56:50
     */
    protected String message;

    /**
     * @desc 处理时间 
     * @date 2016年3月16日-下午4:55:45
     */
    protected Date time;
    
    /**
     * @desc uuid：都小写，带短横线 
     * @date 2016年3月16日-下午4:55:52
     */
    protected String uuid;
    
    public BaseReply(){
        
    }
    
  
    public BaseReply(ReplyResult replyResult,ReplyCode replyCode,String message,String uuid){
        this.result=replyResult;
        this.code=replyCode;
        this.message=StringUtils.isNotBlank(message)?message.trim():null;
        this.uuid=StringUtils.isNotBlank(uuid)?uuid.trim():null;
        this.time=new Date();
    }
    


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public ReplyResult getResult() {
        return result;
    }


    public void setResult(ReplyResult result) {
        this.result = result;
    }


    public ReplyCode getCode() {
        return code;
    }


    public void setCode(ReplyCode code) {
        this.code = code;
    }






}
