package jpa_JooLib.config;

import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * web.xml에 대응되는 자바기반 설정파일 생성(DispatcherServletInitializer.java)
 * DispatcherServlet과 애플리케이션의 서블릿 컨텍스트내의 스프링 애플리케이션 컨텍스트를 설정.
 * AbstractAnnotationConfigDispatcherServletInitializer는 두개의 자바빈 관리자를 생성하는데
 * DispatcherServlet(자식 컨텍스트)과 ContextLoaderListener(부모 컨텍스트)를 생성.
 * DispatcherServlet은 컨트롤러, 뷰 리졸버, 핸들러 매핑과 같은 웹 컴포넌트가 포함된 빈을 로딩
 * ContextLoaderListener는 애플리케이션 내의 그 외의 다른 빈을 로딩 또는 DispatcherServlet이
 * 여럿인 경우 한번에 설정을 로딩하기 위해 사용.
 */
public class DispatcherServletInitializer
		extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 오라클 한글 깨짐 방지
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}

	/**
	 * ContextLoaderListener가 생성한 애플리케이션 컨텍스트를 설정
	 * 여기서는 JPA 관련 빈 들을 포함 시킴
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {
				JPAConfig.class
		};
		// return null;
	}

	/**
	 * DispatcherServlet이 애플리케이션 컨텍스트를 WebMvcConfig
	 * 설정 클래스(java Config)에서 정의된 빈으로 로딩
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {
				WebMvcConfig.class
		};
	}

	/**
	 * DispatcherServlet이 매핑되기 위한 하나 혹은 여러 개의 패스를 지정한다.
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] {
				"/"
		};
	}

}