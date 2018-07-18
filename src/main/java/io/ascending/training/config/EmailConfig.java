package io.ascending.training.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Profile({"dev","test","prod"})
    @Bean(name="mailSender")
    public JavaMailSender getEmailSender(){
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost("smtp.sendgrid.net");
        emailSender.setPort(465);
        emailSender.setProtocol("smtps");
        emailSender.setUsername("ryohang");
        emailSender.setPassword("missionpossible");
        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtps.auth","true");
        mailProperties.setProperty("mail.smtp.ssl.enable","true");
        mailProperties.setProperty("mail.transport.protocol","smtps");
        emailSender.setJavaMailProperties(mailProperties);
        return emailSender;
    }

    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerMailConfiguration(){
        FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
        freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("classpath:/WEB-INF/mail/");
        return freeMarkerConfigurationFactoryBean;
    }

//    @Bean(name="registrationEmail")
//    public

}
