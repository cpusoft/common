package tk.cpusoft.common.http.test;


import java.util.Iterator;
import java.util.concurrent.TimeUnit;



import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;



/**
 * http探测类
 *
 */
@Service
public class ProbeHttpService {
	/**
	 * log4j
	 */
	private static final Log logger=LogFactory.getLog(ProbeHttpService.class);
	
	/**
	 * 关闭http连接 时间，单位秒
	 */
	private static final int TIME = 10;

	/**
	 * http连接超时时间
	 */
	private static final int HTTP_CONN_TIMEOUT = 5*6000;

	/**
	 * httpclient超时时间
	 */
	private static final int SOCKET_TIMEOUT = 5*6000;

	/**
	 * 字符集长度
	 */
	private static final int CHARSHET_LEN = 8;

	
	public static void main(String[] args){
	    ProbeHttpService phs = new ProbeHttpService();
	    ProbeHttpEntity phe = new ProbeHttpEntity();
        phe.setDomainName("https://sp0.baidu.com/9_Q4sjW91Qh3otqbppnN2DJv/pae/channel/data/asyncqury?appid=4001&com=shentong&nu=229485118158");
        phe.setNeedHtmlContent(true);
	    phs.crawlHttp(phe);
	}
	
	/**
	 * 对外接口，查询http探测是否能打开
	 * @param domain 域名
	 * @return
	 */
	public ProbeHttpEntity http(String domain){
		logger.info("http():domain:"+domain);
		
		if(StringUtils.isBlank(domain)){
			return null;
		}
		domain = domain.trim().toLowerCase();
		long start = System.currentTimeMillis();
		ProbeHttpEntity phe = new ProbeHttpEntity();
		phe.setDomainName(domain);
		crawlHttp(phe);
		/**
		 * 如果本身失败，再试下加www的情况
		 */
		if(phe.getHttpResult().equalsIgnoreCase("FAIL")){
			String wwwDomain = domain;
			phe = new ProbeHttpEntity();
			phe.setDomainName(wwwDomain);
			crawlHttp(phe);
		}
		long end = System.currentTimeMillis();
		logger.info("http():domain:"+domain+"    spend time:"+(end-start)+"   result:"+phe);
		
		return phe;
	}
	
	
	
	
	/**
	 * 抓取网页
	 * @param domain 域名<br\>
	 * @param  getHtmlContent 是否返回网页内容：			<br\>
	 *       1 false: 就返回OK、FAIL 表示是否成功打开		<br\>
	 *       2 true：返回整个的网页内容,可能返回""		<br\>
	 * @return 
	 */
	public  void  crawlHttp(ProbeHttpEntity phe){
		logger.info("crawlHtml():domain:"+phe.getDomainName());
	//	phe.setDomainName( RegexUtil.getUrl(phe.getDomainName()) );

		DefaultHttpClient httpclient = null;
		HttpGet httpget = null;
		try {
			long start = System.currentTimeMillis();
			
			/**
			 * 通过httpclient获取网页
			 */
			httpclient = new DefaultHttpClient();
			CookieStore cookieStore = new BasicCookieStore();
			httpclient.setCookieStore(cookieStore);
			CookieSpecFactory csf = new CookieSpecFactory(){
				public CookieSpec newInstance(HttpParams params) {
					return new BrowserCompatSpec(){
						private CookieOrigin origin;
						public void validate(Cookie cookie, CookieOrigin origin)throws MalformedCookieException{
							this.origin = origin;
						}  
					};
				}
			};
			httpclient.getCookieSpecs().register("easy", csf);
			httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY, "easy"); 
			configParams(httpclient);			
			httpget = new HttpGet(phe.getDomainName());
			configHeader(httpget);
			HttpResponse response = httpclient.execute(httpget);

			/**
			 *  判断页面返回状态判断是否进行转向抓取新链接
			 */
			int statusCode = response.getStatusLine().getStatusCode();
			logger.info("crawlHtml():statusCode:"+statusCode);
			
			if ((statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
					|| (statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
					|| (statusCode == HttpStatus.SC_SEE_OTHER)
					|| (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
				String newUri = response.getLastHeader("Location").getValue();
				httpclient = new DefaultHttpClient();
				configParams(httpclient);	
				httpget = new HttpGet(newUri);
				configHeader(httpget);
				response = httpclient.execute(httpget);
				statusCode = response.getStatusLine().getStatusCode();
			}
			phe.setStatusCode(statusCode);
			long end = System.currentTimeMillis();
			logger.info("crawlHtml():domain:"+phe.getDomainName()+" statusCode:"+statusCode+"   spendtime:"+(end-start)+"ms");
			
			/**
			 * 是否还需要页面内容
			 */
			if(!phe.isNeedHtmlContent()){
				//在[200,400)之间就算成功，否则就算失败
				if(statusCode>=HttpStatus.SC_OK && statusCode<HttpStatus.SC_BAD_REQUEST){
					phe.setHttpResult("OK");
				}else{
					phe.setHttpResult( "FAIL");
				}
			}else{
				// Get hold of the response entity
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// 将源码流保存在一个byte数组当中，因为可能需要两次用到该流，
					byte[] bytes = EntityUtils.toByteArray(entity);

					// 如果头部Content-Type中包含了编码信息，那么我们可以直接在此处获取
					String charSet = getHtmlCharset(new String(bytes));
					phe.setHtmlContent( new String(bytes, charSet) );
					phe.setHttpResult("OK");
					System.out.println(phe.getHtmlContent());
				}
				phe.setHttpResult("AGAIN");
			}
			
		} catch (Exception e) {
			logger.error("crawlHtml():e:"+e.getMessage(),e);
			phe.setHttpResult(  "FAIL" );
		} finally {
			if(httpclient!=null){
				httpclient.getConnectionManager().closeIdleConnections(TIME, TimeUnit.SECONDS);
				httpclient.getConnectionManager().closeExpiredConnections();
				httpclient.getConnectionManager().shutdown();
			}
		}

		
	}


	/**
	 * 配置http的param
	 * @param httpclient
	 */
	private void configParams(HttpClient httpclient){
		httpclient.getParams().setIntParameter("http.connection.timeout", HTTP_CONN_TIMEOUT);
		httpclient.getParams().setIntParameter("http.socket.timeout", SOCKET_TIMEOUT);
	}

	/**
	 * 配置http的header
	 * @param httpclient
	 */
	private void configHeader(HttpGet httpget){
		httpget.setHeader("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
		// 用逗号分隔显示可以同时接受多种编码
		httpget.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpget.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
	}

	/**
	 * 自动获取html首页页面的编码格式
	 * 
	 * @param htmlContent
	 *            网页内容
	 * @return
	 */
	private String getHtmlCharset(String htmlContent) {
		String pageCharset = "";
		try {
			org.jsoup.nodes.Document htmlDoc = Jsoup.parse(htmlContent);// Jsoup.parse(html,"GBK");设置编码
			// htmlDoc.select("div[class=post]"); 查询属性=post的div标签
			Elements metaElements = htmlDoc.getElementsByTag("meta");// 获取meta标签所在的Elements节点
			Iterator<Element> metaElmentIter;
			if (metaElements != null) {
				metaElmentIter = metaElements.iterator();
				while (metaElmentIter.hasNext()) {// 循环meta对象
					Element meta = metaElmentIter.next();
					/**
					 * 适合的格式1 <meta charset=UTF-8 /> 2<meta
					 * http-equiv=Content-Type content=text/html; charset=utf-8
					 * />
					 */
					String charset = meta.attr("charset");
					if (StringUtils.isNotBlank(charset)) {
						pageCharset = charset;
						break;
					} else {
						/**
						 * 适合的格式1<meta http-equiv=Content-Type
						 * content="text/html; charset=utf-8" />
						 */
						String equivCharset = meta.attr("http-equiv");
						if (StringUtils.isNotBlank(equivCharset)
								&& "Content-Type"
										.equalsIgnoreCase(equivCharset)) {// 获取设置编码格式的http-equiv节点
							String contentCharset = meta.attr("content");
							if (StringUtils.isNotBlank(contentCharset)
									&& contentCharset.indexOf("charset=") >= 0) {
								contentCharset = contentCharset
										.substring(contentCharset.indexOf("charset=") + CHARSHET_LEN);
								pageCharset = contentCharset;
								break;
							}
						}
					}
				}
			}
		} catch (Exception e){
			logger.error("获取网页字符集出错"+e.getMessage());
		}
		if(StringUtils.isEmpty(pageCharset)){
			pageCharset = "utf-8";
		}
		return pageCharset;
	}



	

}
