/**
 * @desc  
 * @date 2015年3月19日
 */
package tk.cpusoft.common.web.interceptor;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;




/**
 * @desc 
 * @date 2015年3月19日-上午10:16:49
 */
@Repository  
public class UsrAgentInterceptor  extends HandlerInterceptorAdapter {

    /**
     * logback
     */
    private static Logger logger = LoggerFactory.getLogger(UsrAgentInterceptor.class);


    @Resource
    protected CompositeConfiguration config;

    private Set<String> allowBrowserSet = new HashSet<String>();

    @PostConstruct
    public void init(){
        List allowBrowserList = config.getList("user.agent.allow.browser");
        for(Object b : allowBrowserList){
            allowBrowserSet.add((String)b);
        }

    }

    /**
     * @desc 拦截器判断useragent 
     * @date 2015年7月14日-下午6:05:52
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
    */
    @Override
    public boolean preHandle(HttpServletRequest request, 
            HttpServletResponse response, Object handler) throws Exception {

        if(request==null){
            return false;
        }
        String agent = request.getHeader("user-agent");

        if(StringUtils.isBlank(agent) || !contains(agent.toLowerCase())){
            logger.info("preHandle():agent:"+agent+"   is not in allow browser");
            response.sendRedirect(config.getString("user.agent.error"));
            return false;
        }

        return super.preHandle(request, response, handler);
    }
    
    /**
     * @desc 进程查询 
     * @date 2015年7月14日-下午6:05:39
     * @param agent
     * @return boolean 
     */
    private boolean contains(String agent){
        for(String allow:allowBrowserSet){
            if(agent.contains(allow)){
                return true;
            }
        }
        return false;
    }

}
