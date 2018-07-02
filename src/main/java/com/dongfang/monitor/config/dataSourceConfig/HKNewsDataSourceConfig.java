package com.dongfang.monitor.config.dataSourceConfig;



import com.dongfang.monitor.support.dao.reposiotry.base.BaseRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef="hkNewsEntityManagerFactory",
        transactionManagerRef="hkNewsTransactionManager",
        basePackages= { "com.dongfang.monitor.dao.hk_news" },repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class HKNewsDataSourceConfig {
    @Autowired
    private JpaProperties jpaProperties;


    @Autowired
    @Qualifier("hkNewsDataSource")
    private DataSource hkNewsDataSource;

    @Bean(name = "hkNewsEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean hkNewsEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(hkNewsDataSource)
                .properties(getVendorProperties(hkNewsDataSource))
                .packages("com.dongfang.monitor.model.hk_news")
                .persistenceUnit("hkNewsPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "hkNewsEntityManagerFactory")
    public EntityManagerFactory hkNewsEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.hkNewsEntityManagerFactoryBean(builder).getObject();
    }

    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "hkNewsTransactionManager")
    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(hkNewsEntityManagerFactory(builder));
    }
}
