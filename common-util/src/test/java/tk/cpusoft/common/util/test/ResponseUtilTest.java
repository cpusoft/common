/**
 * @desc  
 * @date 2016年3月16日
 */
package tk.cpusoft.common.util.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;






import tk.cpusoft.common.util.response.BaseReply;
import tk.cpusoft.common.util.response.ReplyCode;
import tk.cpusoft.common.util.response.ReplyResult;
import tk.cpusoft.common.util.response.ReplyUtil;
import tk.cpusoft.common.util.uuid.UuidUtil;

/**
 * @desc 
 * @date 2016年3月16日-下午4:40:46
 */
public class ResponseUtilTest {
    @Test
    public void test(){
        String jsonStr = ReplyUtil.geneBaseReply(ReplyResult.ok, ReplyCode.ERR_ALARM_OTHER, "测试通过", UuidUtil.geneUuid(true));
        System.out.println(jsonStr);

        BaseReply b = (BaseReply)ReplyUtil.jsonString2BaseReply(jsonStr);
        System.out.println(b);


        jsonStr = ReplyUtil.geneBaseReply(ReplyResult.fail, ReplyCode.ERR_NOALARM_BUSINESS, "申请失败，此身份证已经申请***银行", UuidUtil.geneUuid(true));
        System.out.println(jsonStr);

        b = (BaseReply)ReplyUtil.jsonString2BaseReply(jsonStr);
        System.out.println(b);




    }

}
