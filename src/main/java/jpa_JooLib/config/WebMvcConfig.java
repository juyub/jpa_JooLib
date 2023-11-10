package jpa_JooLib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author jclee
 * 디스패처 서블릿의 설정파일
 * 
 * 스프링 MVC 빈설정, 뷰리졸버 설정등 디스패처 서블릿의 설정을 위한 XML파일의 역할을 하는 
      자바설정 기반의 파일(WebMvcConfig.java)
 */

@Configuration  //자바설정파일임을 지정, 자바빈 정의의 소스파일임
@EnableWebMvc   //Spring MVC에서 빈설정을 자동으로 해준다. 필요한 빈 등록등
//@Controller, @Service등에서 빈을 검색할 기본 패키지경로 지정
@ComponentScan(basePackages = { "jpa_JooLib"})
public class WebMvcConfig implements WebMvcConfigurer {
	/**
	 * 뷰리졸버
	 * @return
	 */
    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = 
        		        new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/");
//        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("/image/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
//        registry.addResourceHandler("/index").addResourceLocations("/");
    }
    
}

