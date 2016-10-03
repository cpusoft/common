package tk.cpusoft.common.util.log;

import tk.cpusoft.common.base.BaseModel;

/**
 * 日志信息Bean
 * 
 * @desc
 * @date 2015-6-10-下午5:33:25
 */
public class BaseLog extends BaseModel{

    /**
     * @desc
     * @date 2015-6-10-下午5:33:21
     */
    private static final long serialVersionUID = 1L;

    protected String date;// 访问时间
    protected String clientIp;//访问者IP
    protected String operatingSystem;// 用户操作系统
    protected String browser;// IE类型及版本
    protected String area;// 地区
    protected String serverIp;//服务器IP

    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
    
    

}
