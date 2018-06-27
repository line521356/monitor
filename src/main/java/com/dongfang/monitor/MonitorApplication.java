package com.dongfang.monitor;

import com.dongfang.monitor.enums.ResourceTypeEnum;
import com.dongfang.monitor.model.Permission;
import com.dongfang.monitor.model.User;
import com.dongfang.monitor.service.PermissionService;
import com.dongfang.monitor.service.UserService;
import com.dongfang.monitor.support.dao.reposiotry.base.BaseRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.dongfang.monitor.dao"},repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class MonitorApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }
}
