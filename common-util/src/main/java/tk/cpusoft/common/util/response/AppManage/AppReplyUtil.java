/**
 * @desc  
 * @date 2016年8月5日
 */
package tk.cpusoft.common.util.response.AppManage;

import java.util.Map;

import tk.cpusoft.common.util.json.JsonUtil;
import tk.cpusoft.common.util.response.ComplexReply;
import tk.cpusoft.common.util.response.ReplyCode;
import tk.cpusoft.common.util.response.ReplyResult;

/**
 * @desc 
 * @date 2016年8月5日-上午10:12:23
 */
public class AppReplyUtil {
    private AppReplyUtil(){}
    
    public static String geneReply(
            Long code, String msg,
            Map<String,Object> map) {
        AppReply appReply = new AppReply(new Opt(code,msg),map);
        return JsonUtil.Object2JsonString(appReply);
    } 
}
