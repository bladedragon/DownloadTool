package team.redrock.downloadtool.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import team.redrock.downloadtool.interceptor.SessionInterceptor;

@Configuration
public class SessionConfiguration
        extends WebMvcConfigurerAdapter
{

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
    }
}