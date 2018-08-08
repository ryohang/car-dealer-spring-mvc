package io.ascending.training.config;


import io.ascending.training.mail.RegistrationEmail;
import org.springframework.beans.factory.annotation.Autowired;
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
    public JavaMailSenderImpl getEmailSender(){
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
        freeMarkerConfigurationFactoryBean.setPreferFileSystemAccess(false);
        return freeMarkerConfigurationFactoryBean;
    }

    @Bean(name="registrationEmail")
    public RegistrationEmail getRegistrationEmail(@Autowired JavaMailSenderImpl sender, @Autowired freemarker.template.Configuration configuration){
        RegistrationEmail emailTemplate = new RegistrationEmail();
        emailTemplate.setMailSender(sender);
        emailTemplate.setConfiguration(configuration);
        emailTemplate.setFreemarkerTemplate("RegistrationEmail.ftl");
        return emailTemplate;
    }

}
