package com.springboot.app.player.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {
	
	@Value("${coordinate.url:http://localhost:8004}")
	private String coordinateUrl;
	
	@Value("${web-client.conn-timeout:3000}")
    private int connectionTimeOut;
    
    @Value("${web-client.read-timeout:3000}")
    private int readTimeout;
    
    private ClientHttpConnector setClientConnector() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeOut)
                .responseTimeout(Duration.ofMillis(readTimeout))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
                        	.addHandlerLast(new WriteTimeoutHandler(connectionTimeOut, TimeUnit.MILLISECONDS)));
        return new ReactorClientHttpConnector(httpClient);
    }
    
    @Bean("CoordinateWebClient")
    public WebClient configCoordinateWebClient() {
    	return WebClient.builder()
    			.baseUrl(coordinateUrl)
    			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString())
                .clientConnector(setClientConnector())
                .build();
    }
}
