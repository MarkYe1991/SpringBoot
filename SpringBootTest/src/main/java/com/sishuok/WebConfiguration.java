package com.sishuok;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sishuok.filter.AuthenticationFilter;

@Configuration
public class WebConfiguration {

	//Register AuthenticationFilter.
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		registrationBean.setFilter(authenticationFilter);
		registrationBean.setName("AuthenticationFilter");
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/messageGateWay/legalSenior");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

}
