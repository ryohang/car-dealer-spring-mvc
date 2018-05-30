package io.ascending.training.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "io.ascending.training",
        excludeFilters = @ComponentScan.Filter(type=FilterType.REGEX,pattern="io.ascending.training.api.*"))
public class AppConfig {

}
