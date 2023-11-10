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
 * �ڹټ������ Spring Data JPA�� ���̹�����Ʈ ����(JPAConfig.java)
 */
@Configuration                  //�ڹټ��� ����
// @EnableTransactionManagement �� <tx:annotation-driven/>�� 
// ���ǵǾ� �ִ� ���ø����̼� ���ؽ�Ʈ �ȿ��� ���� @Transactional �� ã�Ƽ� Ʈ������� ����ǰ� �Ѵ�.
@EnableTransactionManagement    
@PropertySource({ "classpath:database.properties" })
@ComponentScan({ "jpa_JooLib" })
// JpaRepository �� ���� ���������� �ڵ������� �ε��ϰ� �� ������ ���� Repository ���� �ڵ����
@EnableJpaRepositories(basePackages = "jpa_JooLib.repository")
public class JPAConfig {

    @Autowired
    private Environment env;

    public JPAConfig() {
        super();
    }

    /**
     * LocalContainerEntityManagerFactoryBean��
     * EntityManagerFactoryBean�� Spring���� ����ϱ� ���� Ŭ�����̸�
	 * 
	 * JPA�� ���� ����ü�� �����ϱ� ������ ����ü�� ������ �����ϱ� ���� Ŭ������ 
	 * ����������  hibernate�� ����ϱ� ������ HibernateJpaVendorAdapter�� ����Ѵ�.
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
     * ���̹�����Ʈ ���� ����
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
     * DB������ ���� �����ͼҽ� ����
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
     * Ʈ����ǸŴ��� ����
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
     * PersistenceExceptionTranslationPostProcessor��
     * @Repository Ŭ�����鿡 ���� �ڵ����� ���ܸ� Spring�� DataAccessException���� �ϰ� ��ȯ.
     * @return
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}