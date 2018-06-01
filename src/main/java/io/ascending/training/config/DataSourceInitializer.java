package io.ascending.training.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "io.ascending.training.repository")
public class DataSourceInitializer {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Environment environment;

    @Value("#{ databaseProperties['database.serverName'] }")
    protected String databaseUrl;

    @Value("#{ databaseProperties['database.username'] }")
    protected String databaseUserName = "";

    @Value("#{ databaseProperties['database.password'] }")
    protected String databasePassword = "";

    @Value("#{ databaseProperties['database.dataSourceClassName'] }")
    protected String driverClassName;

//    @Value("#{ environment['jdbc.validation.query'] }")
//    protected String databaseValidationQuery;

    @PostConstruct
    public void init(){
        String profile = environment.getActiveProfiles()[0];
        logger.info("database initialization with profile: "+profile);
    }



    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        DataSource dataSource = createDataSource();
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }

    private BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUserName);
        dataSource.setPassword(databasePassword);
//        dataSource.setValidationQuery(databaseValidationQuery);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        return dataSource;
    }

    @Profile({"test","stage","prod"})
    @Bean(name="flyway",initMethod="migrate")
    public Flyway flywayDefault() {
        return setupFlyway();
    }

    @Profile({"dev","unit"})
    @Bean(name="flyway",initMethod = "validate")
    public Flyway flywayDev() {
        return setupFlyway();
    }


    @Bean(name="entityManagerFactory")
    @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan(new String[] { "io.ascending.training.domain","io.ascending.training.repository" });
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.spatial.dialect.postgis.PostgisDialect");
        props.put("hibernate.hbm2ddl.auto", "validate");
//        props.put("hibernate.physical_naming_strategy", "com.overture.family.extend.hibernate.ImprovedNamingStrategy")
        props.put("hibernate.connection.charSet","UTF-8");
        props.put("hibernate.show_sql","true");
//        props.put("")


//            <property name="hibernate.ejb.interceptor" value="com.overture.family.repository.jpa.DBNullsFirstLastInteceptor"/>

        factoryBean.setJpaProperties(props);

        return factoryBean;
    }

    private Flyway setupFlyway(){
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("classpath:db/migration/");
        flyway.setDataSource(getDataSource());
        return flyway;
    }
}
