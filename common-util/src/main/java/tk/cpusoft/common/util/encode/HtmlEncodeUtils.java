package tk.cpusoft.common.util.encode;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @desc：hex, md5, sha1,urlencoder
 * @date：2015年2月10日-下午1:07:24
 */
public class HtmlEncodeUtils {
	protected static final Logger logger = LoggerFactory.getLogger(HtmlEncodeUtils.class);
	
	private static final String DEFAULT_URL_ENCODING = "UTF-8";

	
	private HtmlEncodeUtils(){
	}
	
	

	
	/**
	 * 
	 * Html 转码.
	 *
	 * @param html
	 * @return

	 */
	public static String htmlEscape(String html) {
		return StringEscapeUtils.escapeHtml4(html);
	}
	
	/**
	 * 
	 * Html 解码.
	 *
	 * @param htmlEscaped
	 * @return

	 */
	public static String htmlUnescape(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml4(htmlEscaped);
	}
	
	/**
	 * 
	 * Xml 转码.
	 *
	 * @param xml
	 * @return

	 */
	public static String xmlEscape(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}
	
	/**
	 * 
	 * Xml 解码.
	 *
	 * @param xmlEscaped
	 * @return

	 */
	public static String xmlUnescape(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}
	
	

	
	
}
