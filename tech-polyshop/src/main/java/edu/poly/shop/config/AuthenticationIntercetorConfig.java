package edu.poly.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.interceptor.AdminAuthenticationInterceptor;
import edu.poly.shop.interceptor.SiteAuthenticationInterceptor;
import edu.poly.shop.model.CustomerDto;

@Configuration
public class AuthenticationIntercetorConfig implements WebMvcConfigurer {

	@Autowired
	private AdminAuthenticationInterceptor adminAuthenticationInterceptor;

	@Autowired
	private SiteAuthenticationInterceptor siteAuthenticationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	registry.addInterceptor(adminAuthenticationInterceptor).addPathPatterns("/admin/**");

	registry.addInterceptor(siteAuthenticationInterceptor).addPathPatterns("/site/**");
	

	}

}
