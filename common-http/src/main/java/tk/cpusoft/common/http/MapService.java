/**
 * @desc  
 * @date 2016年4月13日
 */
package tk.cpusoft.common.http;

import java.util.Map;

/**
 * @desc 地图接口
 * @date 2016年4月13日-下午1:39:02
 */
public interface MapService {
    
    /**
     * @desc 根据经纬度，查所在地理名词 
     * @date 2016年4月13日-下午2:20:02
     * @param lat 维度
     * @param lng 经度
     * @return String 
     */
    public  String queryLocationByBaidu(String lat, String lng);
    
    /**
     * @desc 
     * @date 2016年4月13日-下午2:41:41
     * @param lat  维度
     * @param lng 经度
     * @return Map<String,String> 
     */
    public Map<String, String> gps2Baidu(String lat, String lng);
}
