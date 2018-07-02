package com.dongfang.monitor.config.dataSourceConfig;

import com.dongfang.monitor.support.dao.reposiotry.base.BaseRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="monitorEntityManagerFactory",
        transactionManagerRef="monitorTransactionManager",
        basePackages= { "com.dongfang.monitor.dao.monitor" },repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class MonitorDataSourceConfig {
    @Autowired
    private JpaProperties jpaProperties;


    @Autowired
    @Qualifier("monitorDataSource")
    private DataSource monitorDataSource;

    @Bean(name = "monitorEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean monitorEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(monitorDataSource)
                .properties(getVendorProperties(monitorDataSource))
                .packages("com.dongfang.monitor.model.monitor")
                .persistenceUnit("monitorPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "monitorEntityManagerFactory")
    @Primary
    public EntityManagerFactory monitorEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.monitorEntityManagerFactoryBean(builder).getObject();
    }

    @Bean(name = "monitorTransactionManager")
    @Primary
    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(monitorEntityManagerFactory(builder));
    }
}
