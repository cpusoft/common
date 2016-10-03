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



/**
 * @desc 
 * @date 2015年5月11日-下午2:57:52
 */
@Deprecated
public interface JsoupService {


    /**
     * @desc 链接网页，得到页面格式
     * @date 2015年6月17日-下午4:10:42
     * @param url url地址
     * @param referrer  referer来源
     * @param postDataCharset 提交编码
     * @param isPost  是否是post，否则就是get
     * @param dataMap 提交的参数
     * @return Document  得到的页面格式，为空时为访问失败
     */
    public Document connect(String url,String referrer,String postDataCharset,boolean isPost,Map dataMap,String dataString);
    
    /**
     * @desc 通过代理链接
     * @date 2015年6月19日-上午10:58:36
     * @param url 访问url地址
     * @param referrer  referer
     * @param postDataCharset 编码
     * @param isPost  是否是POST，还是GET
     * @param dataMap  参数
     * @param proxyIp 代理ip
     * @param proxyPort  代理端口
     * @return Document   返回的网页结构体，为空时为失败
     */
    public Document connectByProxy(String url,String referrer,String postDataCharset,boolean isPost,Map dataMap,String dataString,
            String proxyIp,int proxyPort);

    /**
     * @desc 
     * @date 2015年6月17日-下午4:10:38
     * @param url url地址
     * @param referrer  referer来源
     * @param postDataCharset 提交编码
     * @param isPost  是否是post，否则就是get
     * @param dataMap 提交的参数
     * @return string  得到的页面格式，为空时为访问失败
     */
    public String simpleConnect(String url,String referrer,String postDataCharset,boolean isPost,Map dataMap,String dataString);
    
        
    /**
     * @desc 得到图片的二进制流
     * @date 2015年8月17日-下午4:56:31
     * @param url
     * @param referrer
     * @param postDataCharset
     * @param isPost
     * @param dataMap
     * @param proxyIp  null为不用代理
     * @param proxyPort  -1为不用代理
     * @return byte[] 
     */
    public byte[] imgConnectByProxy(String url,String referrer,String postDataCharset,boolean isPost,Map dataMap, String dataString,
            String proxyIp,int proxyPort);
    
    
    
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
    public Connection connectReuse(Connection lastCon,String newUrl,String referrer,String postDataCharset,boolean isPost,Map dataMap, String dataString,String proxyIp,int proxyPort);
    
    
}
