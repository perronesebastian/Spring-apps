package com.example.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true) //DE SPRING
//@EnableGlobalMethodSecurity(jsr250Enabled = true) //INDEPENDIENTE DE SPRING, SI LO USO PUEDO MIGRAR A OTROS FRAMEWORKS
@EnableGlobalMethodSecurity(prePostEnabled = true) //PERMITE USAR PRE/POST AUTHORIZE (EN ROLESERVICE)
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter{

	//SE DESACTIVA PORQUE EN ConfigUserDetailsService SE USA DESDE LA AUTENTICACION CON DATOS DE LA BD
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("password")).roles("ADMIN")
//		.and().withUser("user").password(encoder().encode("password")).roles("USER");
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/users/**").hasRole("ADMIN").antMatchers("/roles/**")
			.permitAll().anyRequest().authenticated().and().httpBasic();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
