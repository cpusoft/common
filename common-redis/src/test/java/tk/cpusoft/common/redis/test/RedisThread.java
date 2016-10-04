/**
 * @desc  
 * @date 2015年3月2日
 */
package tk.cpusoft.common.redis.test;

import org.testng.Assert;

import redis.clients.jedis.JedisCluster;

/**
 * @desc 
 * @date 2015年3月2日-下午3:40:14
 */
public class RedisThread extends Thread{

    
    protected JedisCluster jc;
    
    public RedisThread(JedisCluster jc){
        this.jc = jc;
    }
    
    @Override
    public void run(){
        long threadid = this.getId();
        for(int i = 0; i < 100; i++){
            String key = "key3-"+threadid+"-"+i;
            String value = "value3-"+threadid+"-"+i;
            long start = System.currentTimeMillis();
            jc.set(key, value);
            String valueGet = jc.get(key);
            System.out.println(key+":"+valueGet+"  time:"+(System.currentTimeMillis()-start)+"ms");
            Assert.assertEquals(value, valueGet);
        }
    }
}
