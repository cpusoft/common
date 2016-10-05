/**
 * @desc： 
 * @date： 2015年2月9日
 */
package tk.cpusoft.common.web.service.impl;

import java.util.HashMap;
import java.util.Map;









import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tk.cpusoft.common.web.service.ServletService;

/**
 * @desc：
 * @date：2015年2月9日-上午10:17:57
 */
@Service(value="servletService")
public class ServletServiceImpl implements ServletService{
    private Logger logger = LoggerFactory.getLogger(ServletServiceImpl.class);


    /**
     * @desc 得到真实的ip
     * @date 2015年7月14日-下午4:45:29
     * @param request 
     * @see tk.cpusoft.common.web.service.ServletService#getAgent(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for"); 
        if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(StringUtils.isBlank(ip)  || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
        if(StringUtils.isBlank(ip)){
            return null;
        }
        String[] split = ip.split(",");
        if(split!=null && split.length>0){
            return split[0].toLowerCase().trim();
        }
        return ip.toLowerCase().trim(); 
    }















}
