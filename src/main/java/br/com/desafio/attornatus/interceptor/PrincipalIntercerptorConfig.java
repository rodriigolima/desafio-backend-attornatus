package br.com.desafio.attornatus.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


public class PrincipalIntercerptorConfig extends WebMvcConfigurationSupport {
    
    @Autowired
    PrincipalInterceptor principalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(principalInterceptor);
    }
}

