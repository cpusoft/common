package tk.cpusoft.common.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface LogInfoService {

    /**
     * @desc 日志信息记录
     * @date 2015年8月3日-下午2:14:48
     * @param name 系统登录用户名
     * @param model model的参数信息,没有时值为null
     * @param rt JSON返回值结果信息
     * @param isRecord 是否记录日志。true：记录，false：不记录
     * @param state 执行成功或失败的标识。success:成功；fail:失败
     * @param request
     * @param response
     * @param Exception isRecord为true时，Exception为null;为false时，日志内容为e
     * @param e void
     */
    public void logInfo(String usrName, Model model, String result, String functionDesc, boolean recordResultDetail,
            HttpServletRequest request, HttpServletResponse response, Exception e);

}
