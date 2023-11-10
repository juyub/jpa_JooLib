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
 * ����ó ������ ��������
 * 
 * ������ MVC ����, �丮���� ������ ����ó ������ ������ ���� XML������ ������ �ϴ� 
      �ڹټ��� ����� ����(WebMvcConfig.java)
 */

@Configuration  //�ڹټ����������� ����, �ڹٺ� ������ �ҽ�������
@EnableWebMvc   //Spring MVC���� ������ �ڵ����� ���ش�. �ʿ��� �� ��ϵ�
//@Controller, @Service��� ���� �˻��� �⺻ ��Ű����� ����
@ComponentScan(basePackages = { "jpa_JooLib"})
public class WebMvcConfig implements WebMvcConfigurer {
	/**
	 * �丮����
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

