/**
 * @desc  
 * @date 2016年4月13日
 */
package tk.cpusoft.common.http.test.map;

import java.util.Map;

import javax.annotation.Resource;

import org.testng.annotations.Test;

import tk.cpusoft.common.http.MapService;
import tk.cpusoft.common.http.test.AbstractSpringTestNGTest;

/**
 * @desc 
 * @date 2016年4月13日-下午2:16:21
 */
public class MapServiceTest extends AbstractSpringTestNGTest{

    
    @Resource
    private MapService mapService;
    
    @Test
    public void addressTest(){
        String lat = "40.005161";
        String lng = "116.345395";
        String address = mapService.queryLocationByBaidu(lng, lat);
        System.out.println(address);
    }
    @Test
    public void gps2BaiduTest(){
        String lat = "40.005161";
        String lng = "116.345395";
        Map address = mapService.gps2Baidu(lng, lat);
        System.out.println(address);
    }
    
    
}
