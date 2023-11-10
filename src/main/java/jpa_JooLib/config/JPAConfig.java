package jpa_JooLib.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 자바설정기반 Spring Data JPA와 하이버네이트 설정(JPAConfig.java)
 */
@Configuration                  //자바설정 파일
// @EnableTransactionManagement 와 <tx:annotation-driven/>은 
// 정의되어 있는 어플리케이션 컨텍스트 안에서 빈의 @Transactional 을 찾아서 트랜잭션이 적용되게 한다.
@EnableTransactionManagement    
@PropertySource({ "classpath:database.properties" })
@ComponentScan({ "jpa_JooLib" })
// JpaRepository 에 대한 설정정보를 자동적으로 로딩하고 이 정보를 토대로 Repository 빈을 자동등록
@EnableJpaRepositories(basePackages = "jpa_JooLib.repository")
public class JPAConfig {

    @Autowired
    private Environment env;

    public JPAConfig() {
        super();
    }

    /**
     * LocalContainerEntityManagerFactoryBean은
     * EntityManagerFactoryBean을 Spring에서 사용하기 위한 클래스이며
	 * 
	 * JPA는 여러 구현체가 존재하기 때문에 구현체별 설정을 지원하기 위한 클래스로 
	 * 예제에서는  hibernate를 사용하기 때문에 HibernateJpaVendorAdapter를 사용한다.
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[] {
            "jpa_JooLib.entity"
        });

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(additionalProperties());

        return entityManagerFactoryBean;
    }

    /**
     * 하이버네이트 설정 정보
     * @return
     */
    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
        hibernateProperties.setProperty("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
        return hibernateProperties;
    }

    /**
     * DB연결을 위한 데이터소스 정의
     * @return
     */
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        return dataSource;
    }

    /**
     * 트랜잭션매니저 설정
     * @param emf
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    /**
     * PersistenceExceptionTranslationPostProcessor는
     * @Repository 클래스들에 대해 자동으로 예외를 Spring의 DataAccessException으로 일괄 변환.
     * @return
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}