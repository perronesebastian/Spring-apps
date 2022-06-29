package com.springboot.app.game.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
	
	@Value("${player.url:http://localhost:8002}")
	private String playerUrl;
	
	@Value("${board.url:http://localhost:8003}")
	private String boardUrl;
	
	@Value("${web-client.conn-timeout:3000}")
	private int connectionTimeOut;
	
	@Value("${web-client.read-timeout:3000}")
	private int readTimeOut;
	
	private ClientHttpConnector setClientConnector() {
		HttpClient httpClient = HttpClient.create()
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeOut)
				.responseTimeout(Duration.ofMillis(readTimeOut))
				.doOnConnected(conn -> 
					conn.addHandlerLast(new ReadTimeoutHandler(readTimeOut, TimeUnit.MILLISECONDS))
						.addHandlerLast(new WriteTimeoutHandler(connectionTimeOut, TimeUnit.MILLISECONDS)));
		return new ReactorClientHttpConnector(httpClient);
	}
	
	@Bean("PlayerWebClient")
	public WebClient configPlayerWebClient() {
		return WebClient.builder()
				.baseUrl(playerUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString())
                .clientConnector(setClientConnector())
                .build();
	}
	
	@Bean("BoardWebClient")
	public WebClient configBoardWebClient() {
		return WebClient.builder()
				.baseUrl(boardUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString())
                .clientConnector(setClientConnector())
                .build();
	}
	
}
