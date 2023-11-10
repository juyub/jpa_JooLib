package jpa_JooLib.config;

import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * web.xml�� �����Ǵ� �ڹٱ�� �������� ����(DispatcherServletInitializer.java)
 * DispatcherServlet�� ���ø����̼��� ���� ���ؽ�Ʈ���� ������ ���ø����̼� ���ؽ�Ʈ�� ����.
 * AbstractAnnotationConfigDispatcherServletInitializer�� �ΰ��� �ڹٺ� �����ڸ� �����ϴµ�
 * DispatcherServlet(�ڽ� ���ؽ�Ʈ)�� ContextLoaderListener(�θ� ���ؽ�Ʈ)�� ����.
 * DispatcherServlet�� ��Ʈ�ѷ�, �� ������, �ڵ鷯 ���ΰ� ���� �� ������Ʈ�� ���Ե� ���� �ε�
 * ContextLoaderListener�� ���ø����̼� ���� �� ���� �ٸ� ���� �ε� �Ǵ� DispatcherServlet��
 * ������ ��� �ѹ��� ������ �ε��ϱ� ���� ���.
 */
public class DispatcherServletInitializer
		extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * ����Ŭ �ѱ� ���� ����
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}

	/**
	 * ContextLoaderListener�� ������ ���ø����̼� ���ؽ�Ʈ�� ����
	 * ���⼭�� JPA ���� �� ���� ���� ��Ŵ
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {
				JPAConfig.class
		};
		// return null;
	}

	/**
	 * DispatcherServlet�� ���ø����̼� ���ؽ�Ʈ�� WebMvcConfig
	 * ���� Ŭ����(java Config)���� ���ǵ� ������ �ε�
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {
				WebMvcConfig.class
		};
	}

	/**
	 * DispatcherServlet�� ���εǱ� ���� �ϳ� Ȥ�� ���� ���� �н��� �����Ѵ�.
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] {
				"/"
		};
	}

}