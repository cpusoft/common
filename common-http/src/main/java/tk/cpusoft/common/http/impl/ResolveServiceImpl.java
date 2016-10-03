/**
 * @desc  
 * @date 2015年6月26日
 */
package tk.cpusoft.common.http.impl;

import java.text.MessageFormat;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;





import tk.cpusoft.common.http.ResolveService;
import tk.cpusoft.common.http.Resolver;
import tk.cpusoft.common.util.compile.DynamicCompile;

/**
 * @desc 
 * @date 2015年6月26日-下午4:47:27
 */
@Service(value="resolveServiceImpl")
public class ResolveServiceImpl implements ResolveService{

    
    
    private final static String METHOD = "execute";

    /**
     * @desc 进行解析 
     * @date 2015年6月26日-下午4:47:58
     * @param doc 页面元素
     * @param javaSource  页面源代码
     * @return 
     * @see tk.cpusoft.common.http.ResolveService#resolve(org.jsoup.nodes.Document, java.lang.String)
    */
    @Override
    public String resolve(Document doc,String className, String javaSource) {
        if(doc==null || StringUtils.isBlank(className) || StringUtils.isBlank(javaSource)){
            return null;
        }
        String allSource = String.format(Resolver.JAVA_SOURCE_FRAME, className, javaSource);  
        Object[] os = new Object[]{doc};
        DynamicCompile dc = new DynamicCompile();
        Class clas = dc.compileClass(className, allSource);
        String ret = (String)dc.invokeClass(clas, METHOD, os);
       // String ret = (String)dc.compileAndInvokeClass(CLASS, allSource, METHOD, os);
        return ret;
    }

    /**
     * @desc 进行解析，根据传入的class类 
     * @date 2015年6月29日-上午9:59:10
     * @param doc  jsoup得到的document元素
     * @param clas  用来进行解析的class类
     * @return String 返回的json结果
     * @see tk.cpusoft.common.http.ResolveService#resolve(org.jsoup.nodes.Document, java.lang.Class)
    */
    @Override
    public String resolve(Document doc,Class clas) {
        if(doc==null || clas==null){
            return null;
        }
        Object[] os = new Object[]{doc};
        DynamicCompile dc = new DynamicCompile();
        String ret = (String)dc.invokeClass(clas, METHOD, os);
       // String ret = (String)dc.compileAndInvokeClass(CLASS, allSource, METHOD, os);
        return ret;
    }
}
