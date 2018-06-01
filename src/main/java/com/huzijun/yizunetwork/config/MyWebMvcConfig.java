package com.huzijun.yizunetwork.config;

import com.huzijun.yizunetwork.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
    public class MyWebMvcConfig extends WebMvcConfigurationSupport  {

    List<String> urlList  = new ArrayList<>();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        urlList.add("/user");
//        urlList.add("/error");
        urlList.add("/user/*");
        urlList.add("/admin/*");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(urlList);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/index").setContextRelative(true);
        super.addViewControllers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
