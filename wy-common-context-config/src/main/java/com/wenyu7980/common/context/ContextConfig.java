package com.wenyu7980.common.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author wenyu
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.wenyu7980.common.context")
public class ContextConfig implements WebMvcConfigurer {

    @Autowired
    private ContextHandlerInterceptor handlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
    }
}
