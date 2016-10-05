package tk.cpusoft.common.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface ServletService {
    
   
    /**
     * @desc：得到真实的ip
     * @date：2015年2月24日-下午6:57:54
     * @param session
     * @param hKey
     * @param hValue
     * @param expiredSeconds void
     */
    public String getRealIp(HttpServletRequest request);
    
   
}
