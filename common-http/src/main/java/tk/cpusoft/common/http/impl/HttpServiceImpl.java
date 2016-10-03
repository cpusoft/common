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












import tk.cpusoft.common.http.HttpService;
import tk.cpusoft.common.http.JsoupService;
import tk.cpusoft.common.http.model.HttpForm;

/**
 * @desc 
 * @date 2015年5月11日-下午2:57:52
 */
@Service(value="httpService")
public class HttpServiceImpl implements HttpService {
    private static Logger logger = LoggerFactory.getLogger(HttpServiceImpl.class);

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
     * @throws IOException 
     */
    @Override
    public Document connect(HttpForm httpForm) throws IOException{
        long start = System.currentTimeMillis();
        try {
            logger.debug("connect():httpForm:"+httpForm);
            return connectReuse(null,httpForm).execute().parse();
        } catch (IOException e) {
            long end = System.currentTimeMillis();
            logger.error("connect():exception:httpForm:"+ httpForm +"  time:"+(end-start)+"ms   e:"+e.getMessage(),e);
            if(httpForm.isThrowException()){
                throw e;
            }else{
                return null;
            }
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
     * @throws IOException 
     */
    @Override
    public String simpleConnect(HttpForm httpForm) throws IOException{
        long start = System.currentTimeMillis();
        try {
            logger.debug("simpleConnect():httpForm:"+httpForm);
            return connectReuse(null,httpForm).execute().body();
        } catch (IOException e) {
            long end = System.currentTimeMillis();
            logger.error("simpleConnect():exception:httpForm:"+httpForm+"  time:"+(end-start)+"ms   e:"+e.getMessage(),e);
            if(httpForm.isThrowException()){
                throw e;
            }else{
                return null;
            }
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
    public Connection connectReuse(Connection lastCon,HttpForm httpForm) {
        long start = System.currentTimeMillis();

        Connection connect = null;
        if(lastCon==null){
            connect = Jsoup.connect(httpForm.getUrl());
        }else{
            connect = lastCon.url(httpForm.getUrl());
        }
        connect = connect
                .userAgent(config.getString("common.http.user.agent",DEFAULT_USER_AGENT))
                .timeout(config.getInt("common.http.timeout.ms",DEFAULT_TIMEOUT))
                .followRedirects(true)
                .referrer(httpForm.getReferrer())
                .postDataCharset(httpForm.getCharset())
                .validateTLSCertificates(false)
                .ignoreContentType(true)
                .method(httpForm.getMethod());
        if(httpForm.getDataMap()!=null && httpForm.getDataMap().size()>0){
            connect = connect.data(httpForm.getDataMap());
        }
        if(StringUtils.isNotBlank(httpForm.getDataString())){
            connect = connect.requestBody(httpForm.getDataString());
        }

        if(StringUtils.isNotBlank(httpForm.getProxyIp()) && httpForm.getProxyPort() > 0) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(httpForm.getProxyIp(), httpForm.getProxyPort()));
            connect = connect.proxy(proxy);
        }
        long end = System.currentTimeMillis();
        logger.debug("connectReuse():time:"+(end-start)+"ms");
        return connect;

    }




}
