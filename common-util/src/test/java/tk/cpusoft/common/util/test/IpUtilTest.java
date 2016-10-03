/**
 * @desc  
 * @date 2015年7月15日
 */
package tk.cpusoft.common.util.test;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import tk.cpusoft.common.util.ip.IpUtil;
/**
 * @desc 
 * @date 2015年7月15日-上午9:24:41
 */
public class IpUtilTest {

    @Test
    public void isIpTest(){
        List<String> ips = new ArrayList<String>();
        ips.add("127.0.0.1");
        ips.add("*.*.*.*");
        ips.add("9.4.5555.2");
        ips.add("9.4.c.2");
        ips.add("dfsd");
        for(String ip:ips){
            System.out.println(ip+":"+IpUtil.isIp(ip));
        }
    }
    @Test
    public void allowIpTest(){
        String allowIp = "*.*.*.*";
        List<String> ips = new ArrayList<String>();
        ips.add("127.0.0.1");
        ips.add("*.*.*.*");
        ips.add("7.6.5.4");
        System.out.println("allow ip:"+  allowIp);
        for(String ip:ips){
            System.out.println(ip+":"+IpUtil.allowIp(allowIp,ip));
        }
        
        allowIp = "7.6.*.*";
        System.out.println("allow ip:"+  allowIp);
        for(String ip:ips){
            System.out.println(IpUtil.allowIp(allowIp,ip));
        }
        
        
        allowIp = "7.6.*.4";
        System.out.println("allow ip:"+  allowIp);
        for(String ip:ips){
            System.out.println("ip "+ip+":"+IpUtil.allowIp(allowIp,ip));
        }
    }
}
