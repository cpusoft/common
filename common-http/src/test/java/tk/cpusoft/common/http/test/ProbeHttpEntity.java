package tk.cpusoft.common.http.test;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * http探测，记录域名和返回结果等
 *
 */
public class ProbeHttpEntity implements Cloneable,Serializable{
	/**
	 * uid
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * log4j打印
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	/**
	 * 域名
	 */
	private String domainName;
	
	/**
	 * 探测返回的结果
	 */
	private String httpResult;
	
	/**
	 * http探测返回的状态
	 */
	private int statusCode;
	
	/**
	 * 是就得到http的statuscode，还是要所有的网页代码，默认是false
	 */
	private boolean needHtmlContent = false;
	
	/**
	 * needHtmlContent为true时，网页代码
	 */
	private String htmlContent;

	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getHttpResult() {
		return httpResult;
	}
	public void setHttpResult(String httpResult) {
		this.httpResult = httpResult;
	}

	


	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public boolean isNeedHtmlContent() {
		return needHtmlContent;
	}
	public void setNeedHtmlContent(boolean needHtmlContent) {
		this.needHtmlContent = needHtmlContent;
	}
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {  
		return  super.clone();  
		  
	}  
}
