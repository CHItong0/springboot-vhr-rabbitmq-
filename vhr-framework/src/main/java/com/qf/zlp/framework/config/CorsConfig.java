package com.qf.zlp.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//跨域配置类
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置跨越路径
        registry.addMapping("/**")
                //设置跨越的域名
                .allowedOriginPatterns("*")
                //是否允许coolie
                .allowCredentials(true)
                //设置允许的请求方式
                .allowedMethods("GET","POST","DELETE","PUT")
                //设置允许的header属性
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }
}
