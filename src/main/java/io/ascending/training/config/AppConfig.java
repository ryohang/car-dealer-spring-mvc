package io.ascending.training.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = "io.ascending.training",
        excludeFilters = @ComponentScan.Filter(type=FilterType.REGEX,pattern="io.ascending.training.api.*"))
public class AppConfig {
    @Autowired
    private Environment environment;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Bean(name = "databaseProperties")
    public PropertiesFactoryBean getDbProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        String profile = environment.getActiveProfiles()[0];
        logger.debug("databaseProperties is "+profile);
        bean.setLocation(new ClassPathResource("META-INF/env/"+profile+"-db.properties"));
        return bean;
    }

    @Bean(name = "shareProperties")
    public PropertiesFactoryBean getShareProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("META-INF/share-runtime.properties"));
        return bean;
    }
}
