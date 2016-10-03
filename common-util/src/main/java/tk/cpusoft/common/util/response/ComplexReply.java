/**
 * @desc 
 * @date 2015年2月9日
 */
package tk.cpusoft.common.util.response;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @desc 复杂消息，增加了Map，可以封装复杂对象
 * @param <T>
 * @date 2015年2月9日-上午9:33:02
 */
public class ComplexReply<T>  extends BaseReply{
    

    
    
    /**
     * @desc 
     * @date 2016年3月16日-下午4:52:24
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * @desc 返回更加复杂的信息，自定义key和value
     * @date 2015年3月14日-下午3:21:48
     */
    protected Map<String,T> map;   
    
    public ComplexReply(){
        
    }
    
    /**
     * @desc 
     * @date 2016年3月16日-下午5:20:27
     */
    public ComplexReply(ReplyResult replyResult, ReplyCode replyCode, String message, String uuid,Map map) {
        this.result=replyResult;
        this.code=replyCode;
        this.message=StringUtils.isNotBlank(message)?message.trim():null;
        this.uuid=StringUtils.isNotBlank(uuid)?uuid.trim():null;
        this.time=new Date();
        this.map = map;
    }
    
   

    public void addMap(String key, T value){
        if(map==null){
            map = new HashMap<String,T>();
        }
        map.put(key, value);
    }

    
    
    
}
