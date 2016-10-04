/**
 * @desc  
 * @date 2016年8月1日
 */
package tk.cpusoft.common.redis.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import tk.cpusoft.common.redis.RedisService;
import tk.cpusoft.common.redis.cluster.RedisClusterConfig;
/**
 * @desc 
 * @date 2016年8月1日-上午11:29:01
 */
@Service(value="redisService")
public class RedisServiceImpl implements RedisService{
    
    private Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);
    
    @Resource
    private RedisClusterConfig redisClusterConfig;
    
    
    private JedisPool jedisPool;
    
    @PostConstruct
    public void init(){
        HostAndPort nodes = null;
        String[] split = redisClusterConfig.getJedisClusterNodesIps().split(";");
        for(String one:split){
            String[] ipPort = one.split(":");
            nodes = new HostAndPort(ipPort[0], Integer.valueOf(ipPort[1]));
        }
        
        
        JedisPoolConfig poolConfig = new JedisPoolConfig(); 
        poolConfig.setMaxIdle(redisClusterConfig.getMaxIdle());
        poolConfig.setMaxTotal(redisClusterConfig.getMaxTotal());
        poolConfig.setMaxWaitMillis(redisClusterConfig.getMaxWaitMillis());
        // poolConfig.setMinIdle(minIdle);
        poolConfig.setTestOnBorrow(redisClusterConfig.isTestOnBorrow());
        poolConfig.setTestOnReturn(redisClusterConfig.isTestOnReturn());


        // 创建连接池
        jedisPool = new JedisPool(poolConfig, nodes.getHost(), nodes.getPort());

    }
    
    @PreDestroy
    public void destory(){
        if(jedisPool!=null){
            jedisPool.close();
            jedisPool=null;
        }
    }

    protected Jedis getJedis(){
        return jedisPool.getResource();
    }
    protected void returnJedis(Jedis js){
        js.close();
    }
    protected void setExpire(Jedis js, String key, int expiredSeconds){
        if (expiredSeconds > 0) {
            js.expire(key, expiredSeconds);
        }
    }
    @Override
    public void set(String key, String value, int expiredSeconds) {
        if(StringUtils.isBlank(key) || StringUtils.isBlank(value) ){
            return;
        }
        Jedis js = jedisPool.getResource();
        js.set(key, value);
        setExpire(js,key,expiredSeconds);
        js.close();
    }

    @Override
    public Long incrOrDecrAtomic(final String key,final  long value, final int expiredSeconds){
        if(StringUtils.isBlank(key)|| value==0 ){
            return null;
        }
        Jedis js = jedisPool.getResource();
        Long oldValue=0L;
        if(value>0){
            oldValue = js.incrBy(key, value);
        }else if(value<0){
            oldValue = js.decrBy(key,-value);
        }
        setExpire(js,key,expiredSeconds);
        js.close();
        return oldValue;
        
    }



    @Override
    public String get(String key) {
        if(StringUtils.isBlank(key)){
            return null;
        }
        Jedis js = jedisPool.getResource();
        String ret =  js.get(key);
        js.close();
        return ret;
    }


    @Override
    public void del(String key) {
        if(StringUtils.isBlank(key)){
            return ;
        }
        Jedis js = jedisPool.getResource();
        js.del(key);
        js.close();
    }


    @Override
    public void addList(String key, String value, int expiredSeconds) {
        if(StringUtils.isBlank(key) || value==null ){
            return ;
        }
        Jedis js = jedisPool.getResource();
        js.rpush(key, value);
        setExpire(js,key,expiredSeconds);
        js.close();
    }

    @Override
    public void addSet(String key, String value, int expiredSeconds) {
        if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){
            return ;
        }
        Jedis js = jedisPool.getResource();
        js.sadd(key, value);
        setExpire(js,key,expiredSeconds);
        js.close();
    }



    @Override
    public boolean isSetContain(String key, String value) {
        if(StringUtils.isBlank(key) || StringUtils.isBlank(value) ){
            return false;
        }
        Jedis js = jedisPool.getResource();
        boolean ret =  js.sismember(key, value);
        js.close();
        return ret;
    }



    @Override
    public void addMap(String key, String hKey, String hValue, int expiredSeconds) {
        if(StringUtils.isBlank(key) || StringUtils.isBlank(hKey) || StringUtils.isBlank(hValue)){
            return ;
        }
        Jedis js = jedisPool.getResource();
        js.hset(key, hKey, hValue);
        setExpire(js,key,expiredSeconds);
        js.close();
    }
    /**
     * @desc 
     * @date 2015年8月14日-下午5:19:37
     * @param key
     * @param map
     * @param expiredSeconds 
     * @see tk.cpusoft.common.redis.RedisClusterService#addMaps(java.lang.String, java.util.Map, int)
    */
    @Override
    public void addMaps(String key, Map<String,String> map, int expiredSeconds) {
        // TODO Auto-generated method stub
        if(StringUtils.isBlank(key)  || map ==null ){
            return ;
        }
        Jedis js = jedisPool.getResource();
        js.hmset(key,  map);
        setExpire(js,key, expiredSeconds);
        js.close();
    }






    @Override
    public boolean isMapContain(String key, String hKey) {
        if(StringUtils.isBlank(key)  || StringUtils.isBlank(hKey) ){
            return false;
        }
        Jedis js = jedisPool.getResource();
        boolean ret =  js.hexists(key, hKey);
        js.close();
        return ret;
    }

    @Override
    public String getMapValue(String key, String hKey) {
        // TODO Auto-generated method stub
        if(StringUtils.isBlank(key) || StringUtils.isBlank(hKey) ){
            return null;
        }
        Jedis js = jedisPool.getResource();
        String ret =  js.hget(key, hKey);
        js.close();
        return ret;
    }

    @Override
    public Long getTtl(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        Jedis js = jedisPool.getResource();
        Long ret =  js.ttl(key);
        js.close();
        return ret;
    }
    
    private final static String SESSIONID_PREFIX="FIN_REDIS_SESSIONID:";

    @Override
    public void setSessionAttribute(HttpSession session, String hKey, String hValue, int expiredSeconds) {
        // TODO Auto-generated method stub
        try {
            logger.debug("setRedisCacheInfo():session:"+session+"  hKey:"+hKey+"   hValue:"+hValue);
            if(session==null || StringUtils.isBlank(hKey)  || StringUtils.isBlank(hValue)){
                return ;
            }
            String sessionId = SESSIONID_PREFIX+session.getId();
            addMap(sessionId,hKey,hValue,expiredSeconds);
        } catch (Exception e) {
            logger.error("getRedisCacheInfo():e:"+e.getMessage(),e);
            return ;
        }
    }
    @Override
    public void invalidSession(String sessionId) {
        try {
            logger.debug("delSessionAttribute():sessionId:"+sessionId);
            if(StringUtils.isBlank(sessionId) ){
                return ;
            }
            String sessionId2 = SESSIONID_PREFIX+sessionId;
            this.del(sessionId2);
        } catch (Exception e) {
            logger.error("getRedisCacheInfo():e:"+e.getMessage(),e);
            return ;
        }
    }

    @Override
    public String getSessionAttribute(HttpSession session, String hKey) {
        // TODO Auto-generated method stub
        try {
            logger.debug("getSessionAttribute():session:"+session+"  hKey:"+hKey);

            if(session==null ||  StringUtils.isBlank(hKey) ){
                return null;
            }
            String sessionId = SESSIONID_PREFIX+session.getId();
            String hValue  = getMapValue(sessionId,hKey);
            logger.info("getSessionAttribute():session:"+session+"  hKey:"+hKey+"  hValue:"+hValue);
            return hValue;
        } catch (Exception e) {
            logger.error("getSessionAttribute():e:"+e.getMessage(),e);
            return null;
        }
    }


 

    /**
     * @desc 
     * @date 2015年3月23日-上午11:10:50
     * @param session
     * @param hKey
     * @return Long
     * @see tk.cpusoft.common.redis.RedisClusterService#removeSessionAttribute(javax.servlet.http.HttpSession, java.lang.String, java.lang.String)
    */
    @Override
    public Long removeSessionAttribute(HttpSession session, String hKey) {
        // TODO Auto-generated method stub
        try {
            logger.debug("removeSessionAttribute():session:"+session+"  hKey:"+hKey);

            if(session==null || StringUtils.isBlank(hKey) ){
                return null;
            }
            String sessionId = SESSIONID_PREFIX+session.getId();
            Long count = removeMapValue(sessionId,hKey);
            logger.info("removeSessionAttribute():session:"+session+"  hKey:"+hKey);
            return count;
        } catch (Exception e) {
            logger.error("removeSessionAttribute():e:"+e.getMessage(),e);
            return null;
        }
    }

    /**
     * @desc 
     * @date 2015年3月23日-上午11:13:36
     * @param key
     * @param hKey
     * @return Long
     * @see tk.cpusoft.common.redis.RedisClusterService#removeMapValue(java.lang.String, java.lang.String)
    */
    @Override
    public Long removeMapValue(String key, String hKey) {
        // TODO Auto-generated method stub
        if(StringUtils.isBlank(key) ||StringUtils.isBlank(hKey) ){
            return null;
        }
        Jedis js = jedisPool.getResource();
        Long ret =  js.hdel(key, hKey);
        js.close();
        return ret;
    }



}
