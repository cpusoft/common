/**
 * @desc： 
 * @date： 2015年2月9日
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import tk.cpusoft.common.redis.RedisClusterService;
import tk.cpusoft.common.redis.cluster.RedisClusterConfig;


/**
 * @desc：
 * @date：2015年2月9日-上午10:17:57
 */
@Service(value="redisClusterService")
public class RedisClusterServiceImpl implements RedisClusterService{
    private Logger logger = LoggerFactory.getLogger(RedisClusterServiceImpl.class);


    /**
     * @desc cluster 连接池 
     * @date 2015年3月3日-上午10:00:08
     */
    private JedisCluster jc = null;

    @Resource
    private RedisClusterConfig redisClusterConfig;
    
    @PostConstruct
    public void init(){
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        String[] split = redisClusterConfig.getJedisClusterNodesIps().split(";");
        for(String one:split){
            String[] ipPort = one.split(":");
            jedisClusterNodes.add(new HostAndPort(ipPort[0], Integer.valueOf(ipPort[1])));
        }
        
        
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig(); 
        poolConfig.setMaxIdle(redisClusterConfig.getMaxIdle());
        poolConfig.setMaxTotal(redisClusterConfig.getMaxTotal());
        poolConfig.setMaxWaitMillis(redisClusterConfig.getMaxWaitMillis());
        // poolConfig.setMinIdle(minIdle);
        poolConfig.setTestOnBorrow(redisClusterConfig.isTestOnBorrow());
        poolConfig.setTestOnReturn(redisClusterConfig.isTestOnReturn());
        jc = new JedisCluster(jedisClusterNodes,60000,10,poolConfig);
    }

    @PreDestroy
    public void destory(){
        if(jc!=null){
            try {
                jc.close();
                jc=null;
            } catch (IOException e) {
            }
            
        }
    }


    @Override
    public void set(String key, String value, int expiredSeconds) {
        if(key==null || value==null ){
            return;
        }

        jc.set(key, value);
        setExpire(key,expiredSeconds);
    }

    @Override
    public Long incrOrDecrAtomic(final String key,final  long value, final int expiredSeconds){
        if(key==null || value==0 ){
            return null;
        }
        Long oldValue=0L;
        if(value>0){
            oldValue = jc.incrBy(key, value);
        }else if(value<0){
            oldValue = jc.decrBy(key,-value);
        }
        setExpire(key,expiredSeconds);
        return oldValue;
        
    }



    @Override
    public String get(String key) {
        if(key==null ){
            return null;
        }
        return jc.get(key);
    }


    @Override
    public void del(String key) {
        if(key==null ){
            return ;
        }
        jc.del(key);
    }


    @Override
    public void addList(String key, String value, int expiredSeconds) {
        if(key==null || value==null ){
            return ;
        }
        jc.rpush(key, value);
        setExpire(key,expiredSeconds);
    }

    @Override
    public void addSet(String key, String value, int expiredSeconds) {
        if(key==null || value==null ){
            return ;
        }
        jc.sadd(key, value);
        setExpire(key,expiredSeconds);
    }



    @Override
    public boolean isSetContain(String key, String value) {
        if(key==null || value==null ){
            return false;
        }
        return jc.sismember(key, value);
    }



    @Override
    public void addMap(String key, String hKey, String hValue, int expiredSeconds) {
        if(key==null || hKey ==null || hValue ==null){
            return ;
        }
        jc.hset(key, hKey, hValue);
        setExpire(key,expiredSeconds);
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
        if(key==null || map ==null ){
            return ;
        }
        jc.hmset(key,  map);
        setExpire(key, expiredSeconds);
    }






    @Override
    public boolean isMapContain(String key, String hKey) {
        if(key==null || hKey ==null ){
            return false;
        }
        return jc.hexists(key, hKey);
    }

    @Override
    public String getMapValue(String key, String hKey) {
        // TODO Auto-generated method stub
        if(key==null || hKey ==null ){
            return null;
        }
        return jc.hget(key, hKey);
    }

    @Override
    public Long getTtl(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return jc.ttl(key);
    }
    
    private final static String SESSIONID_PREFIX="FIN_REDIS_SESSIONID:";

    @Override
    public void setSessionAttribute(HttpSession session, String hKey, String hValue, int expiredSeconds) {
        // TODO Auto-generated method stub
        try {
            logger.debug("setRedisCacheInfo():session:"+session+"  hKey:"+hKey+"   hValue:"+hValue);
            if(session==null || hKey==null || hValue==null){
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

            if(session==null || hKey == null ){
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


    protected void setExpire(String key, int expiredSeconds){
        if (expiredSeconds > 0) {
            jc.expire(key, expiredSeconds);
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
        if(key==null || hKey ==null ){
            return null;
        }
        return jc.hdel(key, hKey);        
    }











}
