package io.ascending.training.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataSourceInitializer {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Environment environment;

    @Value("${database.serverName}")
    protected String databaseUrl;

    @Value("${database.username}")
    protected String databaseUserName;

    @Value("${database.password}")
    protected String databasePassword;

//    @Value("${database.dataSourceClassName}")
    protected String driverClassName="org.postgresql.ds.PGSimpleDataSource";

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

    private BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        logger.debug("retreive database server name"+databaseUrl);
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

    @Bean
    public HibernateTransactionManager transactionManager(@Autowired SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
//
//    @Bean(name="hibernate4AnnotatedSessionFactory")
//    public SessionFactory getSessionFactory(){
//        Configuration configuration = new Configuration();
//        configuration.configure("/j2n-hibernate.cfg.xml");
//        configuration.addAnnotatedClass(Employee.class);
//        ServiceRegistry srvcReg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        sessionFactory = configuration.buildSessionFactory(srvcReg);
//    }
//    @DependsOn("flyway")
    @Profile({"dev","test","staging","prod"})
    @Bean(name="hibernate5AnnotatedSessionFactory")
    public LocalSessionFactoryBean getLocalSessionFactoryBean(@Autowired DataSource dataSource){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(new String[] { "io.ascending.training.domain","io.ascending.training.repository"});
        Properties props = getDefaultHibernate();
        props.put("hibernate.show_sql","false");
        sessionFactoryBean.setHibernateProperties(props);
        return sessionFactoryBean;
    }
//
    @Bean(name="hibernate5AnnotatedSessionFactory")
//    @DependsOn("flyway")
    @Profile({"unit"})
    public LocalSessionFactoryBean getLocalSessionFactoryBeanUnit(@Autowired DataSource dataSource){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(new String[] { "io.ascending.training.domain","io.ascending.training.repository"});
        Properties props = getDefaultHibernate();
        props.put("hibernate.show_sql","true");
        props.put("org.hibernate.flushMode","ALWAYS");
        sessionFactoryBean.setHibernateProperties(props);
        return sessionFactoryBean;
    }
//
    public Properties getDefaultHibernate(){
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.spatial.dialect.postgis.PostgisDialect");
        props.put("hibernate.hbm2ddl.auto", "validate");
//        props.put("hibernate.physical_naming_strategy", "io.ascending.training.extend.hibernate.ImprovedNamingStrategy");
        props.put("hibernate.connection.charSet","UTF-8");
        return props;
    }

//    @Profile({"test","stage","prod"})
//    @Bean(name="flyway",initMethod="migrate")
//    public Flyway flywayDefault() {
//        return setupFlyway();
//    }
//
//    @Profile({"dev","unit"})
//    @Bean(name="flyway",initMethod = "validate")
//    public Flyway flywayDev() {
//        return setupFlyway();
//    }
//
//    private Flyway setupFlyway(){
//        Flyway flyway = new Flyway();
//        flyway.setBaselineOnMigrate(true);
//        flyway.setLocations("classpath:db/migration/");
//        flyway.setDataSource(getDataSource());
//        return flyway;
//    }

}
