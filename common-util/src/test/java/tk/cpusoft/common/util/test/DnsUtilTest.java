/**
 * @desc  
 * @date 2016年8月19日
 */
package tk.cpusoft.common.util.test;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.dns.DnsUtil;

/**
 * @desc 
 * @date 2016年8月19日-下午3:23:22
 */
public class DnsUtilTest {

    @Test
    public void test() throws Exception{
        String r = "1.1.1.1;2401:8d00:3:0::2;www.sina.com.cn;0::01;3.4.55.6";
        String rr = DnsUtil.getDnsTypes(r.split(";"));
        System.out.println(rr);
    }
}
