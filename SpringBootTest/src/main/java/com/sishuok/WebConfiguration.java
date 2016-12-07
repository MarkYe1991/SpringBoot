package com.sishuok;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sishuok.filter.MyFilter;

@Configuration
public class WebConfiguration {
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		MyFilter myFilter = new MyFilter();
		registrationBean.setFilter(myFilter);
		registrationBean.setName("dsfasdfasdf");
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/user/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

}
