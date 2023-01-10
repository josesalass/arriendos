package com.example.arriendos;

import java.nio.file.Paths;
import java.util.Locale;

import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
    @Bean
	public  SessionLocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		//Por defecto nuestra aplicacion siempre se mostrara en Español
		localeResolver.setDefaultLocale(new Locale("es", "ES"));
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		return localeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.localeChangeInterceptor());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/access-denied").setViewName("access-denied");
		registry.addViewController("/").setViewName("index");
		registry.addViewController("").setViewName("index");
		registry.addViewController("/usuario/nuevo").setViewName("crear_user");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String resoucePath = Paths.get("uploads").toAbsolutePath().toUri().toString();

		// String resoucePath2 = Paths.get("static").toAbsolutePath().toUri().toString();
		// System.out.println(resoucePath2);

		registry.addResourceHandler("/uploads/**").addResourceLocations(resoucePath);

		WebMvcConfigurer.super.addResourceHandlers(registry);
	}



}
