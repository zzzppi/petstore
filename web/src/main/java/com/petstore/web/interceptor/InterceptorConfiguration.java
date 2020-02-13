package com.petstore.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(new LoggingInterceptor());
//        registry.addInterceptor(new LoginInterceptor());

        //InterceptorRegistration ir =
        // 配置拦截的路径
        //ir.addPathPatterns("/**");
        // 配置不拦截的路径
        //ir.excludePathPatterns("/**.html");
        // 还可以在这里注册其它的拦截器
        //registry.addInterceptor(new OtherInterceptor()).addPathPatterns("/**");
    }
}
