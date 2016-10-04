/**
 * @desc  
 * @date 2015年3月18日
 */
package tk.cpusoft.common.redis.cluster;

import org.springframework.beans.factory.annotation.Value;

/**
 * @desc 
 * @date 2015年3月18日-下午3:28:19
 */
public class RedisClusterConfig {
     
    /**
     * @desc 集群中ips 
     * @date 2015年3月18日-下午3:29:40
     */
    private String jedisClusterNodesIps = null;

    /**
     * @desc 最大链接 
     * @date 2015年3月18日-下午3:29:52
     */
    private int maxTotal;
    
    /**
     * @desc 最大空闲链接 
     * @date 2015年3月18日-下午3:30:06
     */
    private int maxIdle;
    
    /**
     * @desc 最大等待时间 
     * @date 2015年3月18日-下午3:30:14
     */
    private int maxWaitMillis;
    
    /**
     * @desc 查询前是否验证 
     * @date 2015年3月18日-下午3:30:23
     */
    private boolean testOnBorrow;
    
    /**
     * @desc 归还前是否验证 
     * @date 2015年3月18日-下午3:30:34
     */
    private boolean testOnReturn;
    
    /**
     * @desc 超时时间 
     * @date 2015年3月18日-下午3:30:52
     */
    private int timeout;
    
    /**
     * @desc 最大重复次数 
     * @date 2015年3月18日-下午3:31:02
     */
    private int maxRedirections;

    public String getJedisClusterNodesIps() {
        return jedisClusterNodesIps;
    }

    public void setJedisClusterNodesIps(String jedisClusterNodesIps) {
        this.jedisClusterNodesIps = jedisClusterNodesIps;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxRedirections() {
        return maxRedirections;
    }

    public void setMaxRedirections(int maxRedirections) {
        this.maxRedirections = maxRedirections;
    }
    
    
    
}
