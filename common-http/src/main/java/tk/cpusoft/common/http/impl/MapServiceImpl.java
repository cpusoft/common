/**
 * @desc  
 * @date 2016年4月13日
 */
package tk.cpusoft.common.http.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;












import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;










import tk.cpusoft.common.http.JsoupService;
import tk.cpusoft.common.http.MapService;
import tk.cpusoft.common.http.model.BaiduLocationResult;
import tk.cpusoft.common.util.encode.Base64Util;
import tk.cpusoft.common.util.json.JsonUtil;


/**
 * @desc 
 * @date 2016年4月13日-下午1:39:34
 */
@Service(value="mapService")
public class MapServiceImpl implements MapService{

    private static Logger logger = LoggerFactory.getLogger(MapServiceImpl.class);
    @Autowired
    protected CompositeConfiguration config;
    
    @Resource
    private JsoupService jsoupService;
    
    /**
     * @desc 根据经纬度通过百度接口插地址
     * @date 2016年4月13日-下午4:23:50
     * @param lat 维度
     * @param lng 精度
     * @return 
     * @see tk.cpusoft.common.http.MapService#queryLocationByBaidu(java.lang.String, java.lang.String)
    */
    @Override
    public  String queryLocationByBaidu(String lat, String lng ) {
        try {
            String urlS = config.getString("fin.baidu.location.url");
            String url = String.format(urlS,lat,lng);
            logger.info("queryLocationByBaidu():查询百度地址:"+url);
            String location = jsoupService.simpleConnect(url, "http://map.baidu.com", "UTF-8", false, new HashMap(),null);
            logger.info("queryLocationByBaidu():查询百度地址:"+url+"   location:"+location);
            //renderReverse&&renderReverse({"status":0,"result":{"location":{"lng":116.34539499545,"lat":40.005161057819},"formatted_address":"北京市海淀区王庄路1","business":"五道口,学院路,北航","addressComponent":{"adcode":"110108","city":"北京市","country":"中国","direction":"附近","distance":"44","district":"海淀区","province":"北京市","street":"王庄路","street_number":"1","country_code":0},"poiRegions":[{"direction_desc":"\u5185","name":"\u6e05\u534e\u540c\u65b9\u79d1\u6280\u5e7f\u573a\u5927\u53a6","tag":"\u623f\u5730\u4ea7"}],"sematic_description":"清华同方科技广场大厦内,清华同方科技大厦B座内0米","cityCode":131}})
            /*
    {
    "status": 0, 
    "result": {
        "location": {
            "lng": 116.34539499545, 
            "lat": 40.005161057819
        }, 
        "formatted_address": "北京市海淀区王庄路1", 
        "business": "五道口,学院路,北航", 
        "addressComponent": {
            "adcode": "110108", 
            "city": "北京市", 
            "country": "中国", 
            "direction": "附近", 
            "distance": "44", 
            "district": "海淀区", 
            "province": "北京市", 
            "street": "王庄路", 
            "street_number": "1", 
            "country_code": 0
        }, 
        "poiRegions": [
            {
                "direction_desc": "内", 
                "name": "清华同方科技广场大厦", 
                "tag": "房地产"
            }
        ], 
        "sematic_description": "清华同方科技广场大厦内,清华同方科技大厦B座内0米", 
        "cityCode": 131
        }
    }

             */
            if(StringUtils.isBlank(location) || location.indexOf("(")<0 || location.lastIndexOf(")")<0){
                return null;
            }
            String subLocation = location.substring(location.indexOf("(") + 1, location.lastIndexOf(")"));
            if(StringUtils.isBlank(subLocation)){
                return null;
            }
            BaiduLocationResult baidu = (BaiduLocationResult)JsonUtil.JsonString2SimpleObject(subLocation, BaiduLocationResult.class);
            if(baidu==null){
                return null;
            }
            String address =  baidu.getResult().getFormatted_address();
            logger.info("queryLocationByBaidu():查询百度地址:"+url+"   address:"+address);
            return address;
        } catch (Exception ex) {
            return null;
        }
        
    }
  
    
    /**
     * gps坐标转换成百度坐标
     * gps2BaiduXY: <br/>
     * Date: 2015年12月18日 下午4:10:42 <br/>
     * @param x
     * @param y
     * @return
     */
    public Map<String, String> gps2Baidu(String lat, String lng) {
        String urlS = config.getString("fin.baidu.gps2baidu.url");
        String url = String.format(urlS,lat,lng);
        logger.info("gps2Baidu():gps转为百度地址:"+url);
        
        String location = jsoupService.simpleConnect(url, "http://map.baidu.com", "UTF-8", false, new HashMap(),null);
        logger.info("gps2Baidu():查询百度地址:"+url+"   location:"+location);
        if(StringUtils.isBlank(location)){
            return null;
        }
        //返回结果是json数据，转换成json对象
        //{"error":0,"x":"MTE2LjM1ODE5Njk3NTEy","y":"NDAuMDEyMjMzMDE5MDMz"}
        Map map = (Map)JsonUtil.JsonString2SimpleObject(location, Map.class);
        logger.info("gps2Baidu():查询百度地址:"+url+"   map:"+map);
        if(map==null){
            return null;
        }
        String lngS = (String)map.get("x");
        String latS = (String)map.get("y");
        String baiduLng = new String(Base64Util.base64Decode(lngS));
        String baiduLat = new String(Base64Util.base64Decode(latS));
        map.clear();
        map.put("lng", baiduLng);
        map.put("lat", baiduLat);
        return map;
    }
   
    
}
