/**
 * @desc  
 * @date 2015年5月11日
 */
package tk.cpusoft.common.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import tk.cpusoft.common.http.model.HttpForm;



/**
 * @desc 
 * @date 2015年5月11日-下午2:57:52
 */
public interface HttpService {


    /**
     * @desc 链接网页，得到页面格式
     * @date 2015年6月17日-下午4:10:42
     * @param url url地址
     * @param referrer  referer来源
     * @param postDataCharset 提交编码
     * @param isPost  是否是post，否则就是get
     * @param dataMap 提交的参数
     * @return Document  得到的页面格式，为空时为访问失败
     * @throws IOException 
     */
    public Document connect(HttpForm httpForm) throws IOException;
    
  

    /**
     * @desc 
     * @date 2015年6月17日-下午4:10:38
     * @param url url地址
     * @param referrer  referer来源
     * @param postDataCharset 提交编码
     * @param isPost  是否是post，否则就是get
     * @param dataMap 提交的参数
     * @return string  得到的页面格式，为空时为访问失败
     * @throws IOException 
     */
    public String simpleConnect(HttpForm httpForm) throws IOException;
    
        
    
    
    /**
     * @desc connection复用：用在需要同一个session场合：比如登录，验证码图片等
     * @date 2015年8月17日-下午5:19:19
     * @param lastCon
     * @param newUrl
     * @param referrer
     * @param postDataCharset
     * @param isPost
     * @param dataMap
     * @param proxyIp
     * @param proxyPort
     * @return Connection 
     */
    public Connection connectReuse(Connection lastCon,HttpForm httpForm);
    
    
}
