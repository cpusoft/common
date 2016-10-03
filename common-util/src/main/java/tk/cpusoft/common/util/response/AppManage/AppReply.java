/**
 * @desc  
 * @date 2016年8月5日
 */
package tk.cpusoft.common.util.response.AppManage;

import java.util.HashMap;
import java.util.Map;

import tk.cpusoft.common.base.BaseModel;

/**
 * @desc 
 * @date 2016年8月5日-上午10:07:41
 */
public class AppReply extends BaseModel{

    /**
     * @desc 
     * @date 2016年8月5日-上午10:10:19
     */
    private static final long serialVersionUID = 1L;

    private Opt opt;
    
    private Map data = new HashMap();

    
    public AppReply(){
        
    }
    /**
     * @desc 
     * @date 2016年8月5日-上午10:15:10
     */
    public AppReply(Opt opt2, Map<String, Object> map) {
        this.setOpt(opt2);
        this.setData(map);
    }

    public Opt getOpt() {
        return opt;
    }

    public void setOpt(Opt opt) {
        this.opt = opt;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
    
    
    
}
