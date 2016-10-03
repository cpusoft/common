package tk.cpusoft.common.util.uuid;

import java.util.UUID;

/**
 * @desc：生产uuid值
 * @date：2015年7月16日-上午12:23:32
 */
public class UuidUtil {

    /**
     * @desc 生成uuid，得到的都是小写
     * @date 2015年7月16日-上午8:19:55
     * @param keepLine 是否保留横线
     * @return String 
     */
    public static String geneUuid(boolean keepLine){
        UUID uuid = UUID.randomUUID(); 
        if(!keepLine){
            return uuid.toString().replaceAll("-", "").toLowerCase();
        }else{
            return uuid.toString().toLowerCase();
        }
    }
}
