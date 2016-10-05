package tk.cpusoft.common.web.service.impl;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.util.WebUtils;











import tk.cpusoft.common.web.service.LogInfoService;
import tk.cpusoft.common.web.service.ServletService;
import tk.cpusoft.common.util.date.DateUtils;
import tk.cpusoft.common.util.exception.ExceptionUtil;
import tk.cpusoft.common.util.json.JsonUtil;
import tk.cpusoft.common.util.log.LogInfo;
import tk.cpusoft.common.util.response.ReplyCode;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

@Service(value="logInfoService")
public class LogInfoServiceImpl implements LogInfoService {
    private Logger logger = LoggerFactory.getLogger("ES");
    @Resource
    ServletService servletService;

    /**
     * @desc 日志信息记录
     * @date 2015年8月3日-下午2:23:26
     * @param usrName 当前登录用户名
     * @param Model model的参数信息
     * @param result JSON返回值结果信息
     * @param functionDesc 功能描述
     * @param recordResultDetail 是否记录日志。true：记录，false：不记录
     * @param request
     * @param response
     * @param Exception isRecord为true时，Exception为null;为false时，日志内容为e
     * @see tk.cpusoft.common.web.service.LogInfoService#logInfo(java.lang.String,
     *      java.lang.String, java.lang.String, boolean, java.lang.String,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Exception)
     */
    public void logInfo(String usrName, Model model, String result,String functionDesc, boolean recordResultDetail,
            HttpServletRequest request, HttpServletResponse response, Exception e) {
        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, "");
        // 1，访问时间
        String dateTime = DateUtils.formatDate(new Date(), DateUtils.DEFAULT_DATETIME_FORMAT);
        // 2，访问IP
        String clientIp = servletService.getRealIp(request);

        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        // 3,通过浏览器 获取客户的操作系统
        OperatingSystem os = userAgent.getOperatingSystem();
        String clientOS = os.toString();
        Browser browser = userAgent.getBrowser();
        // 4,浏览器信息
        String brower = browser.toString();
        // 5,访问者所在地（可以先为空）
        // 6,访问者姓名（取当前登录人信息;取不到时，默认为空字符串）
        // 见参数
        // 7,访问时间
        // 同一 dateTime
        // 8,访问的导航菜单（先设置成空）
        // 9,访问内容全路径
        String requestUrl = request.getRequestURL().toString();// 全路径
        // 10,访问是否成功()
        // catch外-成功，内失败
        // 11,停留时间（设置成空）
        // 12,来源页面
        String referer = request.getHeader("Referer");
        // 13,下载次数（设置成空）

        // 14，服务器IP
        LogInfo logInfo = new LogInfo();
        logInfo.setDate(dateTime);// 访问时间
        logInfo.setClientIp(clientIp);//
        logInfo.setOperatingSystem(clientOS);// 用户操作系统
        logInfo.setBrowser(brower);// IE类型及版本
        logInfo.setArea("");// 地区
        logInfo.setAccount(usrName);// 登陆账号（访客）
        logInfo.setLoginTime(dateTime);// 登陆时间
        logInfo.setFunctionDes(functionDesc);// 访问功能描述
        logInfo.setFunctionUrl(requestUrl);// 访问URL
        if(e==null){
            logInfo.setAccessReturnState("success");// 访问返回状态（成功或失败）
        }else{
            logInfo.setAccessReturnState("fail");// 访问返回状态（成功或失败）
        }
        logInfo.setStayLongTime("");// 停留时间
        logInfo.setReference(referer);// 访问连接来源（之前的页面）
        logInfo.setDownloadNum("");// 下载次数
        logInfo.setParam(paramMap.toString());
        logInfo.setModel((model!=null)?model.toString():null);
        logInfo.setServerIp(getFirstIPWithoutLocalhost());// 14,服务器IP
        String jsonLog = "";
        StringBuffer logBuffer = new StringBuffer();
        // 是否需要记录结果集
        if (recordResultDetail) {
            logInfo.setResult(result);
        }
        //默认是OK
        ReplyCode lc = ReplyCode.OK;
        if(e!=null){
            logInfo.setExceptionMessage(e.getMessage());
            logInfo.setExceptionStackTrace(ExceptionUtil.printStackTrace(e));
            lc = ReplyCode.ERR_ALARM_OTHER;
            if(e instanceof NullPointerException){
                lc = ReplyCode.ERR_ALARM_NULL;
            }else if( e instanceof SQLException){
                lc = ReplyCode.ERR_ALARM_ORACLE; 
            }else if( e instanceof SocketException){
                lc = ReplyCode.ERR_ALARM_CONNECT; 
            }
        }
        logInfo.setLogCode(lc);
        jsonLog = JsonUtil.Object2JsonString(logInfo);
        logBuffer.append(jsonLog);

        logger.info(logBuffer.toString());

    }
    
    /**
     * 得到所有本机地址
     * @return List<InetAddress> 
     */
    public List<InetAddress> getAllHostAddress() {   
        try {   
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();   
            List<InetAddress> addresses = new ArrayList<InetAddress>();   

            while (networkInterfaces.hasMoreElements()) {   
                NetworkInterface networkInterface = networkInterfaces.nextElement();   
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();   
                while (inetAddresses.hasMoreElements()) {   
                    InetAddress inetAddress = inetAddresses.nextElement();   
                    addresses.add(inetAddress);   
                }   
            }   

            return addresses;   
        } catch (SocketException e) {   
            logger.error("getAllHostAddress():error:" + e.getMessage(), e);
            return null;
        }   
    } 
    private static String firstIPWithoutLocalhost ="";
    /**
     * 得到第一个非127.0.0.1的，实际的ip地址
     * @return String ip地址
     */
    @SuppressWarnings("unused")
    public String getFirstIPWithoutLocalhost(){
        if(StringUtils.isBlank(firstIPWithoutLocalhost)){
            List<InetAddress> colInetAddress =getAllHostAddress();
            for (InetAddress address : colInetAddress) {   
                if (!address.isLoopbackAddress() && address.getAddress().length==4){ 
                    firstIPWithoutLocalhost = address.getHostAddress();
                    break;
                }
            }
        }
        return firstIPWithoutLocalhost;
    }
    
}
