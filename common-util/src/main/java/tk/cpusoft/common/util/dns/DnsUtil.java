/**
 * @desc  
 * @date 2016年8月19日
 */
package tk.cpusoft.common.util.dns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tk.cpusoft.common.util.ip.IpUtil;

/**
 * @desc 
 * @date 2016年8月19日-下午2:33:08
 */
public class DnsUtil {
    /**
     * logback
     */
    private static Logger logger = LoggerFactory.getLogger(DnsUtil.class);
    
    
    public static DnsType geDnsType(String rdata){
        if(StringUtils.isBlank(rdata)){
            return DnsType.OTHERS;
        }
        if(rdata.contains(":")){
            return DnsType.AAAA;
        }else{
           // String regex="^[0-9.]+$";
            String regex = "^\\d+\\.\\d+\\.\\d+\\.\\d+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher match=pattern.matcher(rdata);
            boolean isipv4 = match.matches();
            if(isipv4){
                return DnsType.A;
            }
            
            regex="^[A-Za-z0-9.-]+$";
            pattern = Pattern.compile(regex);
            match=pattern.matcher(rdata);
            boolean isipv6 = match.matches();
            if(isipv6){
                return DnsType.CNAME;
            }
            
            return DnsType.OTHERS;
            
        }
    }
    
    /**
     * @desc rdatas
     * @date 2016年8月19日-下午2:59:14
     * @param rdatas
     * @return String 
     * @throws Exception 
     */
    public static String getDnsTypes(String[] rdatas) throws Exception{
        StringBuffer buf = new StringBuffer();
        for(String rdata: rdatas){
            DnsType dnsType = geDnsType(rdata);
            if(dnsType==DnsType.OTHERS){
                throw new Exception(rdata+"非合法的A、AAAA或CNAME值");
            }
            buf.append(dnsType.name()+";");
        }
        if(buf.length()>0){
            return buf.substring(0,buf.length()-1);
        }
        throw new Exception("非合法的A、AAAA或CNAME值");
    }
}
