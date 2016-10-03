/**
 * @desc  
 * @date 2015年5月11日
 */
package tk.cpusoft.common.http.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;











import tk.cpusoft.common.http.JsoupService;

/**
 * @desc 
 * @date 2015年5月11日-下午2:57:52
 */
@Deprecated
@Service(value="jsoupService")
public class JsoupServiceImpl implements JsoupService {
    private static Logger logger = LoggerFactory.getLogger(JsoupServiceImpl.class);

    /**
     * 配置文件
     */
    @Autowired
    protected CompositeConfiguration config;

    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36";
    private static final int DEFAULT_TIMEOUT=30000;
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
    @Override
    public Document connect(String url,String referrer,String postDataCharset,boolean isPost,Map dataMap,String dataString){
        long start = System.currentTimeMillis();
        try {
            logger.debug("connect():url:"+url+"  referrer:"+referrer+
                    "   postDataCharset:"+postDataCharset+ "  isPost:"+isPost+
                    "  dataMap:"+dataMap);
            return connectReuse(null,url,referrer,postDataCharset,isPost,dataMap,dataString,null,-1).execute().parse();
        } catch (IOException e) {
            long end = System.currentTimeMillis();
            logger.error("connect():exception:url:"+url+"  referrer:"+referrer+
                    "   postDataCharset:"+postDataCharset+ "  isPost:"+isPost+
                    "  dataMap:"+dataMap+"  time:"+(end-start)+"ms   e:"+e.getMessage(),e);
            return null;
        }

    }
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
    @Override
    public String simpleConnect(String url,String referrer,String postDataCharset,boolean isPost,Map dataMap,String dataString){
        long start = System.currentTimeMillis();
        try {
            logger.debug("simpleConnect():url:"+url+"  referrer:"+referrer+
                    "   postDataCharset:"+postDataCharset+ "  isPost:"+isPost+
                    "  dataMap:"+dataMap);
            return connectReuse(null,url,referrer,postDataCharset,isPost,dataMap,dataString,null,-1).execute().body();
        } catch (Exception e) {
            long end = System.currentTimeMillis();
            logger.error("simpleConnect():exception:url:"+url+"  referrer:"+referrer+
                    "   postDataCharset:"+postDataCharset+ "  isPost:"+isPost+
                    "  dataMap:"+dataMap+"  time:"+(end-start)+"ms   e:"+e.getMessage(),e);
            return null;
        }
    }

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
    @Override
    public Document connectByProxy(String url,String referrer,String postDataCharset,boolean isPost,Map dataMap,String dataString,
            String proxyIp,int proxyPort){
        long start = System.currentTimeMillis();
        try {
            logger.debug("connectByProxy():url:"+url+"  referrer:"+referrer+
                    "   postDataCharset:"+postDataCharset+ "  isPost:"+isPost+
                    "  dataMap:"+dataMap+"   proxyIp:"+proxyIp+"  proxyPort:"+proxyPort);
            return connectReuse(null,url,referrer,postDataCharset,isPost,dataMap,dataString,proxyIp,proxyPort).execute().parse();
        } catch (IOException e) {
            long end = System.currentTimeMillis();
            logger.error("connectByProxy():exception:url:"+url+"  referrer:"+referrer+
                    "   postDataCharset:"+postDataCharset+ "  isPost:"+isPost+
                    "  dataMap:"+dataMap+"   proxyIp:"+proxyIp+"  proxyPort:"+proxyPort+"  time:"+(end-start)+"ms   e:"+e.getMessage(),e);
            return null;
        }

    }
    /**
     * @desc 
     * @date 2015年8月17日-下午4:28:58
     * @param url
     * @param referrer
     * @param postDataCharset
     * @param isPost
     * @param dataMap
     * @return 
     * @see tk.cpusoft.common.http.JsoupService#imgConnect(java.lang.String, java.lang.String, java.lang.String, boolean, java.util.Map)
     */
    @Override
    public byte[] imgConnectByProxy(String url, String referrer, String postDataCharset, boolean isPost, Map dataMap,String dataString, 
                String proxyIp,int proxyPort) {
        try {
            logger.debug("imgConnect():url:"+url+"  referrer:"+referrer+
                    "   postDataCharset:"+postDataCharset+ "  isPost:"+isPost+
                    "  dataMap:"+dataMap);
            return connectReuse(null,url,referrer,postDataCharset,isPost,dataMap,dataString,proxyIp,proxyPort).execute().bodyAsBytes();
        } catch (Exception e) {
            logger.error("imgConnect():exception:url:"+url+"  referrer:"+referrer+
                    "   postDataCharset:"+postDataCharset+ "  isPost:"+isPost+
                    "  dataMap:"+dataMap+"  e:"+e.getMessage(),e);
            return null;
        }
    }

    /**
     * @desc 进行实际的链接 
     * @date 2015年8月17日-下午4:54:23
     * @param url
     * @param referrer
     * @param postDataCharset
     * @param isPost
     * @param dataMap
     * @param proxyIp
     * @param proxyPort
     * @return Response 
     */
    @Override
    public Connection connectReuse(Connection lastCon,String url,String referrer,String postDataCharset,boolean isPost,Map dataMap,String dataString,
            String proxyIp,int proxyPort){
        try{
            
            Method method= null;
            if(isPost){
                method = Method.POST;
            }else{
                method =  Method.GET;
            }
            Connection connect = null;
            if(lastCon==null){
                connect = Jsoup.connect(url);
            }else{
                connect = lastCon.url(url);
            }
            connect = connect
                    .userAgent(config.getString("common.http.user.agent",DEFAULT_USER_AGENT))
                    .timeout(config.getInt("common.http.timeout.ms",DEFAULT_TIMEOUT))
                    .followRedirects(true)
                    .referrer(referrer)
                    .postDataCharset(postDataCharset)
                    .validateTLSCertificates(false)
                    .ignoreContentType(true)
                    .method(method);
            if(dataMap!=null && dataMap.size()>0){
                connect = connect.data(dataMap);
            }
            if(StringUtils.isNotBlank(dataString)){
                connect = connect.requestBody(dataString);
            }
                
            if(StringUtils.isNotBlank(proxyIp) && proxyPort > 0) {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, proxyPort));
                connect = connect.proxy(proxy);
            }
            
            return connect;
        } catch (Exception e) {
            logger.error("connectImpl():exception:url:"+url+"  referrer:"+referrer+
                    "   postDataCharset:"+postDataCharset+ "  isPost:"+isPost+
                    "  dataMap:"+dataMap+"   e:"+e.getMessage(),e);
            return null;
        }
    }
   



}
