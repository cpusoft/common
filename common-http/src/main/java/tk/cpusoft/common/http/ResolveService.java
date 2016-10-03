/**
 * @desc  
 * @date 2015年6月26日
 */
package tk.cpusoft.common.http;

import org.jsoup.nodes.Document;

/**
 * @desc 进行解析的接口
 * @date 2015年6月26日-下午4:46:11
 */
public interface ResolveService {

    /**
     * @desc 进行解析，根据类名和源代码
     * @date 2015年6月26日-下午4:55:52
     * @param doc   jsoup得到的document元素
     * @param javaSource  页面源代码
     * @return String 返回的json结果
     */
    String resolve(Document doc,String className,String javaSource);
    
    
    /**
     * @desc 进行解析，根据传入的class类 
     * @date 2015年6月29日-上午9:59:10
     * @param doc  jsoup得到的document元素
     * @param clas  用来进行解析的class类
     * @return String 返回的json结果
     */
    String resolve(Document doc,Class clas);
}
