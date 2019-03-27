package com.itcuc.configure;

import com.itcuc.interceptor.DashboardInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author z
 */
@Configuration
public class WebMvcConfigurerAdapter extends WebMvcConfigurationSupport {

    /**
     * 静态资源处理
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dashboardInterceptor()).addPathPatterns("/n/**").excludePathPatterns("/n/login","/n/signin");
        super.addInterceptors(registry);
    }

    @Bean
    public DashboardInterceptor dashboardInterceptor(){return new DashboardInterceptor(); }
}
