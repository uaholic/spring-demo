package com.gyq.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: guanyanqi
 * @Date: 2019-05-21 17:26
 */
@Configuration
@EnableJpaRepositories("com.gyq.dao")//包名
@EnableTransactionManagement
public class JpaConfig {

    @Value("${jdbc.mysql.driver}")
    private String jdbcDriver;
    @Value("${jdbc.mysql.url}")
    private String jdbcUrl;
    @Value("${jdbc.mysql.username}")
    private String jdbcUsername;
    @Value("${jdbc.mysql.password}")
    private String jdbcPassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcUrl);//数据库名
        dataSource.setUsername(jdbcUsername);//username
        dataSource.setPassword(jdbcPassword);//password
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.gyq.entity");//包名
        factory.setDataSource(dataSource());
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.show_sql","true");
        jpaProperties.setProperty("hibernate.format_sql","true");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto","update");
        factory.setJpaProperties(jpaProperties);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator(){
        return new HibernateExceptionTranslator();
    }
}
