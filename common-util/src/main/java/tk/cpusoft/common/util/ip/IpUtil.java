/**
 * @desc  
 * @date 2015年7月15日
 */
package tk.cpusoft.common.util.ip;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tk.cpusoft.common.util.validate.reglogin.RegLoginValidate;

/**
 * @desc 和ip地址相关的一些工具函数
 * @date 2015年7月15日-上午8:57:55
 */
public class IpUtil {

    /**
     * logback
     */
    private static Logger logger = LoggerFactory.getLogger(IpUtil.class);

    /**
     * @desc ipv4的检查格式，支持通配符。可以再找找正则的，先直接比较吧 
     * @date 2015年7月15日-上午9:05:53
     * @param ips
     * @return boolean 
     */
    public static boolean isIp(String ips) {
        try {
            if(StringUtils.isBlank(ips)){
                logger.debug("ips is blank:" + ips);
                return false;
            }
            String[] arr = ips.split("\\.");
            if (arr.length != 4) {
                logger.debug("ips is not allowed ip:" + ips);
                return false;
            }
            for (String s : arr) {
                if(s.equalsIgnoreCase("*")){
                    continue;
                }
                if(! s.matches("[0-9]+")){
                    return false;
                }
                int i = Integer.valueOf(s);
                if (i < 0 || i > 255) {
                    logger.debug("ips is error:" + ips);
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            logger.error("isIp():exception:ips:"+ips+"   e:"+e.getMessage(),e);
            return false;
        }
    }

    /**
     * @desc 判断是否是允许的ip 
     * @date 2015年7月15日-上午9:23:15
     * @param allowIp 允许的ip，支持通配符
     * @param remoteIp 输入的ip（如果是本机127.0.0.1，就默认都允许）
     * @return boolean 
     */
    public static boolean allowIp(String allowIp, String remoteIp){
        try {
            if(!isIp(allowIp) || !isIp(remoteIp)){
                return false;
            }
            if(allowIp.equalsIgnoreCase(remoteIp) || 
                    allowIp.equalsIgnoreCase("*.*.*.*") ||
                    remoteIp.equalsIgnoreCase("127.0.0.1")){
                return true;
            }
            String[] allowIps = allowIp.split("\\.");
            String[] remoteIps = remoteIp.split("\\.");
            for(int i = 0; i < allowIps.length; i++){
                if(!(allowIps[i].equalsIgnoreCase("*") || allowIps[i].equalsIgnoreCase(remoteIps[i]))){
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            logger.error("allowIp():exception:allowIp:"+allowIp+"    remoteIp:"+remoteIp+"   e:"+e.getMessage(),e);
            return false;
        }
    }
    

}
