package com.example.interview.configuration;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "crmTransactionManager",
        entityManagerFactoryRef = "crmEntityManagerFactory")
@Slf4j
public class JpaConfig {


    @Value("${spring.datasource.url:#{null}}")
    private String datasourceUrl;

    @Value("${spring.datasource.username:#{null}}")
    private String datasourceName;

    @Value("${spring.datasource.password:#{null}}")
    private String datasourcepassword;

    @Value("${spring.datasource.driverClassName:#{null}}")
    private String driverName;

    @Value("${spring.jpa.database-platform:#{null}}")
    private String dialect;



    @Bean(name = "crmDataSource")
    public DataSource dataSource() {
        log.info("data source start log message");

            String dbUsername = datasourceName;
            String dbPassword = datasourcepassword;
            String dbUrl = datasourceUrl;
            String dbDriverClassname = driverName;
            log.info("data source end log message");

            return DataSourceBuilder.create().username(dbUsername).password(dbPassword).url(dbUrl)
                    .driverClassName(dbDriverClassname).build();


    }

    private HibernateJpaVendorAdapter vendorAdaptor() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(false);
        return vendorAdapter;
    }

    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryContainer() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
        entityManagerFactoryBean.setPersistenceUnitName("crmEntityManagerPU");
        entityManagerFactoryBean.setPackagesToScan("com.example.interview");
        log.info("entity manager start log message");

        return entityManagerFactoryBean;
    }

    private Properties jpaHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate_hbm2ddl.auto", "none");
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        return properties;

    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryContainer().getObject());
        log.info("transaction manager start log message");

        return transactionManager;
    }
}


