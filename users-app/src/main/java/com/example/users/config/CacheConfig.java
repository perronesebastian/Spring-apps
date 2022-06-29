package com.example.users.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean(destroyMethod ="shutdown")
	public RedissonClient redisson() throws IOException {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://127.0.0.1:6379");
		return Redisson.create(config);
	}

	@Bean
	public CacheManager cacheManager(RedissonClient redissonClient){
		Map<String, CacheConfig> config = new HashMap<>() ;
		config.put("users", new CacheConfig());
		return new RedissonSpringCacheManager(redissonClient);
	}
}
