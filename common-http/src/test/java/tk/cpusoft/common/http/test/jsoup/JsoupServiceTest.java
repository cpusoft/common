/**
 * @desc  
 * @date 2015年5月11日
 */
package tk.cpusoft.common.http.test.jsoup;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;







import org.apache.commons.lang3.RandomUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;










import tk.cpusoft.common.http.JsoupService;
import tk.cpusoft.common.http.ResolveService;
import tk.cpusoft.common.http.Resolver;
import tk.cpusoft.common.http.test.AbstractSpringTestNGTest;
import tk.cpusoft.common.util.compile.DynamicCompile;
import tk.cpusoft.common.util.json.JsonUtil;

/**
 * @desc 
 * @date 2015年5月11日-下午3:22:27
 */
public class JsoupServiceTest extends AbstractSpringTestNGTest{

    @Resource
    private JsoupService jsoupService;

    public void compile(){
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    }
    
    @Test
    public void loginTest(){
        String url = "http://192.168.249.51:5112/app-usr/login.shtml";
        Map dataMap = new HashMap();
        dataMap.put("userName", "shaoqing@cpusoft.tk");
        dataMap.put("password","11111111");
        String rr = jsoupService.simpleConnect(url, "", "UTF-8", true, dataMap,null);
        System.out.println(rr);
    }
    

    @Test
    public  void imgTest() throws Exception{
       // String img = "http://www.baidu.com/img/bdlogo.png";
        String img = "https://mail.cpusoft.tk/randomnumber?time=1439802205913";
        byte[] imgs = jsoupService.imgConnectByProxy(img,"","UTF-8",false,new HashMap(),null,null,-1);
        File f = new File("E:\\logs\\1.png");
        if(!f.exists()){
            f.createNewFile();
        }
        FileOutputStream out = (new FileOutputStream(f));
        out.write(imgs);           
        // resultImageResponse.body() is where the image's contents are.
        out.close();
    }
    
    @Test 
    public void proxyIp(){

        //http://www.kuaidaili.com/  http://ip-daili.com/view/?id=2910  http://www.youdaili.net/Daili/guowai/
        Document doc = jsoupService.connect("http://www.kuaidaili.com", "www.kuaidaili.com", "UTF-8",true,
                new HashMap(),null);

        Elements trs = doc.getElementsByTag("tr");
        Iterator<Element> iterator=trs.iterator();  
        List<Map> list = new ArrayList<Map>();
        while(iterator.hasNext()){  
            Element tr=iterator.next();  
            Elements tds = tr.getElementsByTag("td");
            //     System.out.println("=========\r\n"+tds);
            if(tds.size()>1){
                Element ip = tds.get(0);
                Element port = tds.get(1);
                System.out.println("=========\r\n"+ip+":"+port);
                Map map = new HashMap();
                map.put("ip", ip.ownText().trim());
                map.put("port", port.ownText().trim());
                list.add(map);
            }
        }  
        String ret = JsonUtil.Object2JsonString(list);
        System.out.println(ret);
    }

    @Test
    public void compileTest(){
        String CLASS = "Resolve";
        String METHOD = "execute";

        String CONTENT    
        = "import org.jsoup.Jsoup;"
                + "import tk.cpusoft.common.util.json.JsonUtil;"
                + "import java.util.ArrayList;"
                + "import java.util.HashMap;"
                + "import java.util.Iterator;"
                + "import java.util.List;"
                + "import java.util.Map;"
                + "import org.jsoup.nodes.Document;"
                + "import org.jsoup.nodes.Element;"
                + "import org.jsoup.select.Elements;"
                + "public class " + CLASS + " {"
                + "    public static Object " + METHOD + "(Document doc) {"
                + "        Elements trs = doc.getElementsByTag(\"tr\");"
                + "Iterator<Element> iterator=trs.iterator();  "
                + "List<Map> list = new ArrayList<Map>();"
                + "while(iterator.hasNext()){  "
                + "    Element tr=iterator.next();  "
                + "    Elements tds = tr.getElementsByTag(\"td\");"
                + "if(tds.size()>1){ "
                + "        Element ip = tds.get(0); "
                + "        Element port = tds.get(1); "
                + "        Map map = new HashMap(); "
                + "     map.put(\"ip\", ip.ownText().trim()); "
                + "    map.put(\"port\", port.ownText().trim());"
                + "    list.add(map);"
                + "}"
                + "}  "
                + "String ret = JsonUtil.Object2JsonString(list);"
                + " return ret;   }"
                + "}";

        Document doc = jsoupService.connect("http://www.kuaidaili.com", "www.kuaidaili.com", "UTF-8",true,
                new HashMap(),null);

        Object[] os = new Object[]{doc};
        DynamicCompile dc = new DynamicCompile();
        String ret = (String)dc.compileAndInvokeClass(CLASS, CONTENT, METHOD, os);
        System.out.println(ret);
    }
    @Test
    public void compileTest2(){
        String CLASS = "Resolve";
        String METHOD = "execute";

        String CONTENT    
        = "import org.jsoup.Jsoup;"
                + "import tk.cpusoft.common.util.json.JsonUtil;"
                + "import java.util.ArrayList;"
                + "import java.util.HashMap;"
                + "import java.util.Iterator;"
                + "import java.util.List;"
                + "import java.util.Map;"
                + "import org.jsoup.nodes.Document;"
                + "import org.jsoup.nodes.Element;"
                + "import org.jsoup.select.Elements;"
                + "public class " + CLASS + " {"
                + "    public static Object " + METHOD + "(Document doc) {"
                + "        Elements trs = doc.getElementsByTag(\"tr\");"
                + "Iterator<Element> iterator=trs.iterator();  "
                + "List<Map> list = new ArrayList<Map>();"
                + "while(iterator.hasNext()){  "
                + "    Element tr=iterator.next();  "
                + "    Elements tds = tr.getElementsByTag(\"td\");"
                + "if(tds.size()>1){ "
                + "        Element ip = tds.get(0); "
                + "        Element port = tds.get(1); "
                + "        Map map = new HashMap(); "
                + "     map.put(\"ip\", ip.ownText().trim()); "
                + "    map.put(\"port\", port.ownText().trim());"
                + "    list.add(map);"
                + "}"
                + "}  "
                + "String ret = JsonUtil.Object2JsonString(list);"
                + " return ret;   }"
                + "}";

        Document doc = jsoupService.connect("http://www.kuaidaili.com", "www.kuaidaili.com", "UTF-8",true,
                new HashMap(),null);

        Object[] os = new Object[]{doc};
        DynamicCompile dc = new DynamicCompile();
        String ret = (String)dc.compileAndInvokeClass(CLASS, CONTENT, METHOD, os);
        System.out.println(ret);
    }
    @Resource
    private ResolveService resolveService;
    
    String javaSource = 
            "  Elements trs = doc.getElementsByTag(\"tr\");"
            + "Iterator<Element> iterator=trs.iterator();  "
            + "List<Map> list = new ArrayList<Map>();"
            + "while(iterator.hasNext()){  "
            + "    Element tr=iterator.next();  "
            + "    Elements tds = tr.getElementsByTag(\"td\");"
            + "if(tds.size()>1){ "
            + "        Element ip = tds.get(0); "
            + "        Element port = tds.get(1); "
            + "        Map map = new HashMap(); "
            + "     map.put(\"ip\", ip.ownText().trim()); "
            + "    map.put(\"port\", port.ownText().trim());"
            + "    list.add(map);"
            + "}"
            + "}  "
            + "String ret = JsonUtil.Object2JsonString(list);"
            + " return ret;   ";
    
    @Test
    public void compileTest3(){
       

        Document doc = jsoupService.connect("http://www.kuaidaili.com", "www.kuaidaili.com", "UTF-8",true,
                new HashMap(),null);
        String ret = resolveService.resolve(doc,"resolveProxy", javaSource);
        System.out.println(ret);
    }
    @Test
    public void compileTest4(){
       

        Document doc = jsoupService.connect("http://www.kuaidaili.com", "www.kuaidaili.com", "UTF-8",true,
                new HashMap(),null);
        DynamicCompile dc = new DynamicCompile();
        Class clas = dc.compileClass("resolveProxy", String.format(Resolver.JAVA_SOURCE_FRAME, "resolveProxy", javaSource)  );
        String ret = resolveService.resolve(doc,clas);
        System.out.println(ret);
    }
    
    @Test 
    public void proxyTest(){

        //http://www.kuaidaili.com/  http://ip-daili.com/view/?id=2910  http://www.youdaili.net/Daili/guowai/
        //String url = "http://www.kuaidi.com/index-ajaxselectcourierinfo-603121177396-shunfeng.html";
        String url = "http://www.sohu.com";
        Document doc = jsoupService.connectByProxy(url, "www.kuaidi.com", "UTF-8",true,
                new HashMap(),null, "222.88.236.234", 80);
        //       Document doc = jsoupService.connect("http://www.baidu.com/", "http://www.baidu.com/", "UTF-8",false,
        //               new HashMap());
        System.out.println(doc);
    }
    @Test 
    public void connectSimply(){

        //http://www.kuaidaili.com/  http://ip-daili.com/view/?id=2910  http://www.youdaili.net/Daili/guowai/
        //String url = "http://www.kuaidi.com/index-ajaxselectcourierinfo-603121177396-shunfeng.html";
        //String url = "http://www.kuaidi100.com/query?type=shentong&postid=229343539433&id=19&valicode=&temp=0.1448328756397";
        String url = "http://www.cpusoft.tk";
        String ret = jsoupService.simpleConnect(url, "www.baidu.com", "UTF-8",true,
                new HashMap(),null);
        //       Document doc = jsoupService.connect("http://www.baidu.com/", "http://www.baidu.com/", "UTF-8",false,
        //               new HashMap());
        System.out.println(ret);
    }

    @Test 
    public void connectTest(){
        Document doc = jsoupService.connect("http://www.baidu.com/", "http://www.baidu.com/", "UTF-8",false,
                new HashMap(),null);
        System.out.println(doc);
    }
    @Test 
    public void connectHttpsTest(){
        Document doc = jsoupService.connect("https://www.bing.com/", "https://www.baidu.com/", "UTF-8",false,
                new HashMap(),null);
        System.out.println(doc);
    }
    @Test 
    public void connectShortDnTest(){
        Document doc = jsoupService.connect("http://z.cn/", "https://www.baidu.com/", "UTF-8",false,
                new HashMap(),null);
        System.out.println(doc);
    }
    @Test
    public void connectDns(){
    //curl -X POST https://202.173.9.72/auth_cmd -d "{\"resource_type\":\"token_gen_cmd\", \"attrs\":{\"grant_type\":\"password\",\"shaoqing\":\"test\",\"password\":\"tIoO8729\"}}"    --insecure
        Map m2 = new HashMap();
        m2.put("grant_type", "password");
        m2.put("username", "shaoqing");
        m2.put("password", "tIoO8729");
        
        Map m = new HashMap();
        m.put("resource_type","token_gen_cmd");
        m.put("attrs", m2);
        
  
        String r = jsoupService.simpleConnect("https://202.173.9.72/auth_cmd","https://202.173.9.72/auth_cmd","UTF-8",true,new HashMap(),
                JsonUtil.Object2JsonString(m) );
        System.out.println(r);
        
    }
    @Test(enabled=false)
    public void rong360Test1()  throws Exception{

        List<String> urlList = new ArrayList<String>();
        urlList.add("http://beijing.rong360.com/p_a9fdc133ihn3fisq");
        urlList.add("http://beijing.rong360.com/p_5259c1acihn1biiz");
        urlList.add("http://beijing.rong360.com/p_89863lglx");
        urlList.add("http://beijing.rong360.com/p_6esf8252j");
        urlList.add("http://beijing.rong360.com/p_8e856lakh");
        urlList.add("http://beijing.rong360.com/p_fe8f91ffihnotzb6");   
        urlList.add("http://beijing.rong360.com/p_f9s0df5gd");
        urlList.add("http://beijing.rong360.com/p_3f8b9lfk1");
        urlList.add("http://beijing.rong360.com/p_e7ed61acihn0uuug");
        urlList.add("http://beijing.rong360.com/p_203d51c4ihn3tbc3");
        String result = getResults(urlList);
    }

    private String getResults(List<String> urlList)  throws Exception{
        List results = new ArrayList();
        for(String url:urlList){
            Map m = getResult(url);
            results.add(m);
        }
        String r = JsonUtil.Object2JsonString(results);
        System.out.println("r:\r\n"+r);
        return r;
    }

    private Map getResult(String url) throws Exception{
        String id = RandomUtils.nextInt(0, 99999)+"";

        Document  doc = jsoupService.connect(url, "http://beijing.rong360.com", "UTF-8", false, new HashMap(),null);
        Map map = new HashMap();
        map.put("id", id);

        //贷款机构
        Element loanInstitution  = doc.select("img.pngfix").first();
        String loanInstitutionText = loanInstitution.attr("title");
        System.out.println("loanInstitutionText:"+loanInstitutionText);
        map.put("fs_loan_institution", loanInstitutionText);

        //贷款名称
        Element p  = doc.select("div.face-info").first();
        Elements ps = p.children();
        Element title = ps.tagName("h").first().nextElementSibling();
        String titleText = title.ownText().replaceAll(Jsoup.parse("&nbsp;").text(),"");
        System.out.println("titleText:"+titleText);
        map.put("fs_title", titleText);


        //抵押方式
        Element mortageType = doc.select("span.spec.no-house").first();
        String mortageTypeText = mortageType.ownText();
        System.out.println("mortageTypeText:"+mortageTypeText);
        map.put("fs_mortage_type", mortageTypeText);

        //职业身份
        Element occupation = doc.select("span.spec.can-reg").first();
        String occupationText = occupation.ownText();
        System.out.println("occupationText:"+occupationText);
        map.put("fs_occupation", occupationText);

        //放款时间
        Element loanPeriod = doc.select("span.spec.fangkuan").first();
        String loanPeriodText = loanPeriod.ownText();
        System.out.println("loanPeriodText:"+loanPeriodText);
        map.put("fs_loan_period", loanPeriodText);


        //贷款金额上下限
        Element e1 = doc.select("[data-field=loan_limit]").first();
        Element e2 = e1.child(0);
        String[] loanMoneyText = e2.ownText().replace("可选金额", "").split("~");
        String loanMoneyMin = loanMoneyText[0].replace("万", "")+"0000";
        String loanMoneyMax = loanMoneyText[1].replace("万", "")+"0000";
        System.out.println("loanMoneyMin:"+loanMoneyMin+"   loanMoneyMax:"+loanMoneyMax);
        map.put("fs_loan_money_min", Float.parseFloat(loanMoneyMin));
        map.put("fs_loan_money_max", Float.parseFloat(loanMoneyMax));


        //贷款周期上下限
        e1 = doc.select("[data-field=loan_term]").first();
        e2 = e1.child(0);
        String[] loanTermText = e2.ownText().replace("可选期限", "").split("~");
        String loanTermMin = loanTermText[0].replace("月", "");
        String loanTermMax = loanTermText[1].replace("月", "");
        System.out.println("loanTermMin:"+loanTermMin+"   loanTermMax:"+loanTermMax);
        map.put("fs_loan_term_min", Float.parseFloat(loanTermMin));
        map.put("fs_loan_term_max", Float.parseFloat(loanTermMax));

        //费率
        Element monthFeeRate = doc.select("[data-name=rate_desc]").first();
        String monthFeeRateText = monthFeeRate.ownText();
        System.out.println("monthFeeRateText:"+monthFeeRateText);
        String find = "月管理费\\d+\\.*\\d*%";
        Pattern pattern = Pattern.compile(find);
        Matcher matcher = pattern.matcher(monthFeeRateText);
        while(matcher.find()) {
            String m = matcher.group().replace("月管理费", "").replace("%","");
            BigDecimal bm = new BigDecimal(m);
            bm = bm.divide(new BigDecimal(100));
            map.put("fs_month_fee_rate", bm.floatValue());
        }
        find = "一次性收费\\d+\\.*\\d*%";
        pattern = Pattern.compile(find);
        matcher = pattern.matcher(monthFeeRateText);
        while(matcher.find()) {
            String m = matcher.group().replace("一次性收费", "").replace("%","");
            BigDecimal bm = new BigDecimal(m);
            bm = bm.divide(new BigDecimal(100));
            map.put("fs_one_time_fee", bm.floatValue());
        }



        //申请条件
        Element applyThreshold = doc.select("div.pd_other_item_content").first();
        String applyThresholdText = applyThreshold.ownText();
        System.out.println("applyThresholdText:"+applyThresholdText);
        map.put("fs_apply_threshold", applyThresholdText);

        //所需材料
        Element requiredMaterials =applyThreshold.nextElementSibling().nextElementSibling();
        String requiredMaterialsText = requiredMaterials.ownText();
        System.out.println("requiredMaterialsText:"+requiredMaterialsText);
        map.put("fs_required_materials", requiredMaterialsText);


        System.out.println(map);
        return map;
    }
}
