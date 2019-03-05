package io.ascending.training.config;

import io.ascending.training.config.viewresolver.JsonViewResolver;
import io.ascending.training.config.viewresolver.XmlViewResolver;
import io.ascending.training.extend.jackson.JsonViewHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("io.ascending.training.api")
@Import({SwaggerConfig.class})
public class MvcConfig  implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    /*
     * Configure ContentNegotiatingViewResolver
     */
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        List<ViewResolver> viewResolversImpl = new ArrayList<>();
        viewResolversImpl.add(jsonViewResolver());
//        resolvers.add(xmlViewResolver());

        resolver.setViewResolvers(viewResolversImpl);
        return resolver;
    }


    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getMultipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(10008439);
        return resolver;
    }

    @Bean
    public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
        return new DeviceHandlerMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(deviceHandlerMethodArgumentResolver());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
//        MappingJackson2JsonView jsonViewHttpMessageConverter = new MappingJackson2JsonView();
        ResourceHttpMessageConverter resourceHttpMessageConverter = new ResourceHttpMessageConverter();
//        converters.add(jsonViewHttpMessageConverter);
        converters.add(resourceHttpMessageConverter);
    }


    /*
     * Configure View resolver to provide JSON output using JACKSON library to
     * convert object in JSON format.
     */
//    @Bean
    private ViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }

//    @Bean
//    public ViewResolver xmlViewResolver() {
//        return new XmlViewResolver();
//    }

    /*
     * Configure View resolver to provide XML output using JACKSON library to
     * convert object in XML format.
     */
//    @Bean
//    public ViewResolver pdfViewResolver() {
//        return new XmlViewResolver();
//    }




}
