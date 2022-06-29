package com.example.dev.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LifeCycleBean implements BeanNameAware, InitializingBean, DisposableBean {
	
	private static final Logger log = LoggerFactory.getLogger(LifeCycleBean.class);
	
	
	/**
	 * Se ejecuta durante la construccion del bean "BeanNameAware"
	 */
	@Override
	public void setBeanName(String name) {
		log.info("Bean name {}", name);
	}
	
	/*
	 * Se ejecuta despues de la inyeccion de dependencias
	 */
	@PostConstruct
	public void init() {
		log.info("Post Construct");
	}
	
	/*
	 * Se ejecuta antes de que el bean sea destruido.
	 * No se ejecuta para beans prototype y solo se ejecutan para una terminacion normal de la VM
	 */
	@PreDestroy
	public void destroyBean() {
		log.info("Pre Destroy");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("After properties set method");
	}
	
	@Override
	public void destroy() throws Exception {
		log.info("Destroy Method");
	}

}
