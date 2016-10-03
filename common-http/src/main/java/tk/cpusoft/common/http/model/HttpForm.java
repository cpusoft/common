/**
 * @desc  
 * @date 2016年5月30日
 */
package tk.cpusoft.common.http.model;

import java.util.Map;

import org.jsoup.Connection.Method;

import tk.cpusoft.common.base.BaseModel;

/**
 * @desc 
 * @date 2016年5月30日-下午4:42:18
 */
public class HttpForm extends BaseModel  {

    /**
     * @desc 
     * @date 2016年5月30日-下午4:42:34
     */
    private static final long serialVersionUID = 1L;
    
    
    
    /**
     * @desc url地址
     * @date 2016年5月30日-下午4:43:37
     */
    private String url;
    /**
     * @desc referer 
     * @date 2016年5月30日-下午4:44:26
     */
    private String referrer;
    /**
     * @desc 编码 
     * @date 2016年5月30日-下午4:44:31
     */
    private String charset = "UTF-8";
    /**
     * @desc 提交方法：POST、GET、PUT 
     * @date 2016年5月30日-下午4:44:41
     */
    private Method method;
    
    /**
     * @desc 代理ip 
     * @date 2016年5月30日-下午4:48:39
     */
    private String proxyIp = null;
    
    /**
     * @desc 代理端口 
     * @date 2016年5月30日-下午4:48:53
     */
    private int proxyPort=-1;
    
    /**
     * @desc 作为参数提交时 
     * @date 2016年5月30日-下午4:44:51
     */
    private Map dataMap;
    /**
     * @desc 直接作字符串提交 
     * @date 2016年5月30日-下午4:45:03
     */
    private String dataString;
    
    
    /**
     * @desc 是否抛出异常：默认false时，失败就返回null，日志中打印错误。如果为true则抛出异常 
     * @date 2016年9月8日-上午9:49:05
     */
    private boolean throwException = false;
    
    
    public static HttpForm gene(){
        return new HttpForm();
    }
    
    public String getUrl() {
        return url;
    }
    public HttpForm setUrl(String url) {
        this.url = url;
        return this;
    }
    public String getReferrer() {
        return referrer;
    }
    public HttpForm setReferrer(String referrer) {
        this.referrer = referrer;
        return this;
    }
    public String getCharset() {
        return charset;
    }
    public HttpForm setCharset(String charset) {
        this.charset = charset;
        return this;
    }
    public Method getMethod() {
        return method;
    }
    public HttpForm setMethod(Method method) {
        this.method = method;
        return this;
    }
    public Map getDataMap() {
        return dataMap;
    }
    public HttpForm setDataMap(Map dataMap) {
        this.dataMap = dataMap;
        return this;
    }
    public String getDataString() {
        return dataString;
    }
    public HttpForm setDataString(String dataString) {
        this.dataString = dataString;
        return this;
    }
    public String getProxyIp() {
        return proxyIp;
    }
    public HttpForm setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
        return this;
    }
    public int getProxyPort() {
        return proxyPort;
    }
    public HttpForm setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
        return this;
    }

    public boolean isThrowException() {
        return throwException;
    }

    public HttpForm setThrowException(boolean throwException) {
        this.throwException = throwException;
        return this;
    }
    

    
}
