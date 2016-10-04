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
public class RedisServiceTest extends AbstractSpringTestNGTest{

    private Logger logger = LoggerFactory.getLogger(RedisServiceTest.class);

    @Resource
    private RedisService redisService;

  
    @Test
    public void setTest(){
        redisService.set("cpusoft.tk", "newvalue", 99);
        String ret = redisService.get("cpusoft.tk");
        System.out.println(ret);
    }
    
    
    
    
    
    
    
    
}
