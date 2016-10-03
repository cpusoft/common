/**
 * @desc  
 * @date 2016年5月30日
 */
package tk.cpusoft.common.http.test.jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Connection.Method;
import org.testng.annotations.Test;

import tk.cpusoft.common.http.HttpService;
import tk.cpusoft.common.http.model.HttpForm;
import tk.cpusoft.common.http.test.AbstractSpringTestNGTest;
import tk.cpusoft.common.util.encode.MD5Util;
import tk.cpusoft.common.util.json.JsonUtil;

/**
 * @desc 
 * @date 2016年5月30日-下午4:59:20
 */
public class HttpServiceTest  extends AbstractSpringTestNGTest{


    @Resource
    private HttpService httpService;


    //cuifudong abc123
    @Test
    public void testApp() throws IOException{
        
        String loginName = "cuifudong";
        String password = "abc123";
        String t = System.currentTimeMillis()+"";
        String source = "0";
        
        Map map = new HashMap();
        map.put("loginName",loginName);
        map.put("password", MD5Util.md5Hex(MD5Util.md5Hex(password)));
        map.put("source", source);
        map.put("t",t);
       
        String serverTokenClient = loginName+MD5Util.md5Hex(password)+t+source;
        System.out.println(serverTokenClient);
        map.put("token", MD5Util.md5Hex(serverTokenClient));
        System.out.println(map);
        
        StringBuilder sb = new StringBuilder();
        sb.append(loginName)
        .append(DigestUtils.md5Hex(password).toLowerCase())
        .append(t)
        .append(source);
        System.out.println(sb);
        
        String serverToken = DigestUtils.md5Hex(sb.toString()).toLowerCase();
        System.out.println(serverToken);
        HttpForm httpForm = HttpForm.gene().
                setUrl("http://opapp.cpusoft.tk/monitor/client/api?login").
                setReferrer("http://opapp.cpusoft.tk/").
                setMethod(Method.POST).
                setDataMap(map);
        String r = httpService.simpleConnect(httpForm );
        System.out.println(r);
    }

    @Test
    public void connectDns() throws IOException{
        Map m2 = new HashMap();
        m2.put("grant_type", "password");
        m2.put("username", "test");
        m2.put("password", "12345");

        Map m = new HashMap();
        m.put("resource_type","token_gen_cmd");
        m.put("attrs", m2);

        HttpForm httpForm = HttpForm.gene().
                setUrl("https://1.1.1.1/auth_cmd").
                setReferrer("https://1.1.1.1").
                setMethod(Method.POST).
                setDataString(JsonUtil.Object2JsonString(m));

        String r = httpService.simpleConnect(httpForm );
        System.out.println(r);

    }
}
