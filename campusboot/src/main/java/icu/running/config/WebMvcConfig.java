package icu.running.config;

import icu.running.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器配置
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源的位置
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");

    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //放行路径
        List<String> jwtExcludePatterns = new ArrayList();
        //系统静态资源的放行  前后端分离项目不用考虑静态资源的放行，只需要验权即可
        jwtExcludePatterns.add("/**/fonts/*");
        jwtExcludePatterns.add("/**/*.css");
        jwtExcludePatterns.add("/**/*.js");
        jwtExcludePatterns.add("/**/*.png");
        jwtExcludePatterns.add("/**/*.gif");
        jwtExcludePatterns.add("/**/*.jpg");
        jwtExcludePatterns.add("/**/*.jpeg");
        //需要放行的接口
        jwtExcludePatterns.add("/");
        jwtExcludePatterns.add("/login.action");
        jwtExcludePatterns.add("/register.action");
        jwtExcludePatterns.add("/codeLogin");
        jwtExcludePatterns.add("/getCode");
        jwtExcludePatterns.add("/getSmsCode");


        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                //放行的静态资源列表
                .excludePathPatterns(jwtExcludePatterns);
        //super.addInterceptors(registry);
    }
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 解决controller返回字符串中文乱码问题，
        //responsebody默认response返回的格式是ISO-8859的编码，也就是欧洲编码不支持中文。
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter)converter).setDefaultCharset(StandardCharsets.UTF_8);
            }
        }
    }

}