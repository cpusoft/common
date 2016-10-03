/**
 * @desc  
 * @date 2015年6月2日
 */
package tk.cpusoft.common.util.response;

import java.util.Map;



import tk.cpusoft.common.util.json.JsonUtil;

/**
 * @desc 拼接成功或失败的返回json串
 * @date 2015年6月2日-上午10:10:01
 */
public class ReplyUtil {
    private ReplyUtil(){}
    /**
     * @desc 返回结果，无附加返回值
     * @date 2015年5月26日-上午9:15:01
     * @param key  map中定义的key值 
     * @param myResult 要返回的结果
     * @return String 返回的json
     */
    public static String geneBaseReply(ReplyResult replyResult,ReplyCode replyCode,String message,String uuid) {
        BaseReply  reply = new BaseReply(replyResult,replyCode,message,uuid);
        return JsonUtil.Object2JsonString(reply);
    } 
    
    /**
     * @desc  返回成功的结果，有附加返回值Map的(更加复杂)
     * @date 2015年7月3日-上午11:14:13
     * @param map
     * @return String 
     */
    public static String geneComplexReply(
            ReplyResult replyResult,
            ReplyCode replyCode,String message,String uuid,
            Map<String,Object> map) {
        ComplexReply  reply = new ComplexReply(replyResult,replyCode,message,uuid,map);
        return JsonUtil.Object2JsonString(reply);
    } 
  
    
    
    /**
     * @desc 从 geneBaseReply 生成的json，得到BaseReply
     * @date 2016年1月11日-上午10:52:39
     * @param jsonStr
     * @return BaseReply 
     */
    public static BaseReply jsonString2BaseReply(String jsonStr){
        return (BaseReply)JsonUtil.JsonString2SimpleObject(jsonStr, BaseReply.class);
    }
    
   
    /**
     * @desc 从 geneComplexReply 生成的json字符串中得到ComplexReply
     1 定义扩展类，T改为实际的bean类对象<br/>
        public class InteractComplexReply extends ComplexReply&lt;FinScoreBlackList&gt;{ <br/>
          private static final long serialVersionUID = 1L; <br/>
          public FinScoreBlackList get(String key){ <br/>
              return map.get(key); <br/>
            }<br/>
        } <br/>
     2   然后直接调用，解析json串 <br/>
        InteractComplexReply i = (InteractComplexReply)JsonUtil.JsonString2SimpleObject(jsonStr, InteractComplexReply.class); <br/>
        FinScoreBlackList b = i.get("blackList");<br/>
        System.out.println(i);<br/>
<br/>
     */
    public static Object jsonString2ComplexReply(String jsonStr, Class cs){
        return (Object)JsonUtil.JsonString2SimpleObject(jsonStr, cs);
    }
   
}
