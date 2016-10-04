/**
 * @desc： 
 * @date： 2015年2月9日
 */
package tk.cpusoft.common.redis.test;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import tk.cpusoft.common.redis.RedisClusterService;
import tk.cpusoft.common.redis.RedisConfigurationService;
import tk.cpusoft.common.redis.RedisService;
import tk.cpusoft.common.redis.test.common.AbstractSpringTestNGTest;
import tk.cpusoft.common.util.json.JsonUtil;



/**
 * @desc：
 * @date：2015年2月9日-上午10:46:23
 */
public class RedisClusterServiceTest extends AbstractSpringTestNGTest{

    private Logger logger = LoggerFactory.getLogger(RedisClusterServiceTest.class);

    @Resource
    private RedisService redisService;

    @Resource
    private RedisClusterService redisClusterService;
    
    @Resource
    private RedisConfigurationService redisConfigurationService;

    @Test
    public void redisClusterThreadTest() throws InterruptedException{
        ExecutorService pool = Executors.newFixedThreadPool(9);
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("192.168.249.17", 5051));
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig(); 
        poolConfig.setMaxIdle(1024);
        poolConfig.setMaxTotal(200);
        poolConfig.setMaxWaitMillis(1024);
        // poolConfig.setMinIdle(minIdle);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        JedisCluster jc = new JedisCluster(jedisClusterNodes,60000,10,poolConfig);
        
        
        for(int i = 0; i < 9; i++){
            RedisThread t1 = new RedisThread(jc);
            pool.execute(t1);
        }
        
        Thread.sleep(99);
        pool.shutdown();
        pool.awaitTermination(9, TimeUnit.SECONDS);
        pool.shutdownNow();
        //jc.close();
    }

    @Test
    public void redisClusterServiceStrTest(){
        
        redisClusterService.set("FIN_BANK_MANAGEc904d5b801bf4bc8b666a5a6a3aeec23", "2baidu.com", 604800);
        
        String aaa = redisClusterService.get("FIN_BANK_MANAGEc904d5b801bf4bc8b666a5a6a3aeec23");
        System.out.println(aaa);
    }
    @Test
    public void redisClusterServiceMapTest(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("string", "Test");
        redisClusterService.addMaps("addMaps", map, -1);
    }
    @Test
    public void redisClusterServiceTest(){
        redisClusterService.set("test", "value", 999);
        redisClusterService.addMap("addMapKey", "addMaphKey", "hValue", 60*60);
        String hValue = redisClusterService.getMapValue("addMapKey", "addMaphKey");
        Assert.assertEquals(hValue, "hValue");
        
        Long count = redisClusterService.removeMapValue("addMapKey", "addMaphKey");
        System.out.println(count);
        hValue = redisClusterService.getMapValue("addMapKey", "addMaphKey");
        Assert.assertNull(hValue);        
    }
    @Test
    public void redisClusterTtlTest(){
        String key = null;
        for(int i = 0; i < 999;i++){
            key = "LOGINERR_KEY_PREFIX:"+i;
            redisClusterService.del(key);
        }
        key = "LOGINERR_KEY_PREFIX:375";
        Long ttl = redisClusterService.getTtl(key);
        String count = redisClusterService.get(key);
        System.out.println(ttl);
        System.out.println(count);
        
        
        redisClusterService.set("testTtl", "valueTtl", 60);
        ttl = redisClusterService.getTtl("testTtl");
        System.out.println(ttl);
        
    }
    
    @Test
    public void redisConfiTest(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("string", "Test");
        map.put("date", JsonUtil.Object2JsonString(new Date()));
        map.put("long", JsonUtil.Object2JsonString(99L));
        
        redisConfigurationService.addProperty("sina", "www.sina.com.cn");
        redisConfigurationService.addAllProperty(map);
        
        String ret = redisConfigurationService.getString("string", "fail");
        System.out.println(ret);
        
        Date d = redisConfigurationService.getDate("date", null);
        System.out.println(d);
        
        Long l = redisConfigurationService.getLong("long", null);
        System.out.println(l);
        
        String si = redisConfigurationService.getString("sina", "fail");
        System.out.println(si);
    }
    
    
    
    @Test
    public void setTest(){
        redisService.set("www.cpusoft.tk", "newvalue", 99);
        String ret = redisService.get("cpusoft.tk");
        System.out.println(ret);
    }
    
    
    
    
    
    
    
    
}
