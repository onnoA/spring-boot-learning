package com.onnoa.redis.cache.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/16 11:27
 */
/*
@Component
//@ConfigurationProperties(prefix = "redis")
public class RedisTemplateConfig {

    @Value("${redis.hostname}")
    private String hostName;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.usePool}")
    private boolean usePool;

    @Value("${redis.pool.maxActive}")
    private int maxTotal;

    @Value("${redis.pool.maxIdle}")
    private int maxIdle;

    @Value("${redis.pool.maxWaitMillis}")
    private int maxWaitMillis;

    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow;



    */
/**
     * JedisPoolConfig 连接池
     * @return
     *//*

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        //jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        //jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        //jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 在空闲时检查有效性, 默认false
        //jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }

    */
/**
     * 单机版配置
     * @Title: JedisConnectionFactory
     * @param @param jedisPoolConfig
     * @param @return
     * @return JedisConnectionFactory
     * @autor lpl
     * @date 2018年2月24日
     * @throws
     *//*

    @Bean
    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory JedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        //连接池
        JedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        //IP地址
        JedisConnectionFactory.setHostName(hostName);
        //端口号
        JedisConnectionFactory.setPort(port);
        //如果Redis设置有密码
        //JedisConnectionFactory.setPassword(password);
        //客户端超时时间单位是毫秒
        JedisConnectionFactory.setTimeout(5000);
        JedisConnectionFactory.setUsePool(usePool);
        return JedisConnectionFactory;
    }



}
*/
