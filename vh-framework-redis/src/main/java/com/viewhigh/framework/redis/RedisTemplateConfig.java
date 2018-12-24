/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月24日
 * 修改日期：2018年5月24日
 */
package com.viewhigh.framework.redis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月24日
 */
@Configuration
public class RedisTemplateConfig {
	
	@Autowired
	private RedisConfig redisConfig;
	
	@Autowired
	private RedisCommonConfig redisCommonConfig;
	
	@Autowired
	private RedisClusterConfig redisClusterConfig;

	@Bean
	@Primary
	public JedisPoolConfig initPoolConfig(){
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(redisCommonConfig.getMaxIdle());
		poolConfig.setMinIdle(redisCommonConfig.getMinIdle());
		poolConfig.setMaxTotal(redisCommonConfig.getMaxActive());
		poolConfig.setMaxWaitMillis(redisCommonConfig.getMaxWait());
		poolConfig.setTestOnBorrow(redisCommonConfig.getTestOnBorrow());
		return poolConfig;
	}
	
	@Bean
	@Primary
	public JedisConnectionFactory initConnectionFactory(JedisPoolConfig poolConfig){
		//jedis的连接工厂
		JedisConnectionFactory connectionFactory = null;
		if(StringUtils.isEmpty(redisClusterConfig.getNodes())){
			connectionFactory = new JedisConnectionFactory(poolConfig);
			connectionFactory.setHostName(redisConfig.getHost());
			connectionFactory.setPort(redisConfig.getPort());
			connectionFactory.setDatabase(redisCommonConfig.getDbIndex());
			
		}else{
			//集群版 连接工厂
			//初始化nodes
			List<RedisNode> nodeList = new ArrayList<RedisNode>();
			for(String node:redisClusterConfig.getNodes().split(",")){
				nodeList.add(new RedisNode(node.split(":")[0], Integer.valueOf(node.split(":")[1])));
			}
			
			//初始化 redisClusterConfiguration
			RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
			redisClusterConfiguration.setMaxRedirects(redisClusterConfig.getMaxRedirects());
			redisClusterConfiguration.setClusterNodes(nodeList);
			
			//初始化 connectionFactory
			connectionFactory = new JedisConnectionFactory(redisClusterConfiguration, poolConfig);
		}
		
		connectionFactory.setTimeout(redisCommonConfig.getTimeout());
		connectionFactory.setPassword(redisCommonConfig.getPassword());
		connectionFactory.afterPropertiesSet();
		return connectionFactory;
	}
	
	/**
	 * 实例化redis 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	@Primary
	public RedisTemplate initRedis(JedisConnectionFactory connectionFactory){
		
		//初始化 RedisTemplate
		RedisTemplate redis = new RedisTemplate();
		redis.setConnectionFactory(connectionFactory);
		redis.setKeySerializer(new StringRedisSerializer());
		redis.setValueSerializer(new StringRedisSerializer());
		redis.setHashKeySerializer(new StringRedisSerializer());
		redis.setHashValueSerializer(new StringRedisSerializer());
		redis.afterPropertiesSet();
		return redis;
	}
	
	
}
