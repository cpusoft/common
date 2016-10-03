/**
 * @desc  
 * @date 2015年6月29日
 */
package tk.cpusoft.common.http;

import org.jsoup.nodes.Document;

/**
 * @desc 基类的解析器
 * @date 2015年6月29日-上午9:33:58
 */
public interface Resolver {
    /**
     * @desc 对某个页面的document结果，进行解析，得到json结果 
     * @date 2015年6月29日-上午9:36:42
     * @param doc jsoup得到的document页面
     * @return String  返回的json结果
     */
    String execute(Document doc);
    
    public final static String JAVA_SOURCE_FRAME =""
            + "import java.math.*;"
            + "import java.util.regex.*;"
            + "import java.util.*;"
            + "import org.jsoup.Jsoup;"
            + "import org.jsoup.nodes.*;"
            + "import org.jsoup.select.*;"
            + "import tk.cpusoft.common.util.json.JsonUtil;"
            + "import org.apache.commons.lang3.StringUtils;"
            + "import tk.cpusoft.common.http.Resolver;"
            + "public class %s implements Resolver  {"
            + "    public  String execute (Document doc) { "
            + "       %s "
            + "    }"
            + "}"; 
}
