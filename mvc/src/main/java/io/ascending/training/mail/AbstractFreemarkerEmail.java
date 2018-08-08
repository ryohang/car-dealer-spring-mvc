/**
 * 
 */

package io.ascending.training.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.ascending.training.domain.EmailAction;
import io.ascending.training.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author GuoqingHuang
 * 
 * @since 
 *
 */
public abstract class AbstractFreemarkerEmail {

   protected final Logger logger = LoggerFactory.getLogger(getClass());
   @Autowired
   @Qualifier("applicationProperties")
   private Properties applicationProperties;

   @Value("#{applicationProperties['app.support.email']}")
   public String SUPPORT_EMAIL;

   private Configuration configuration;

   private String freemarkerTemplate;

   private JavaMailSenderImpl mailSender;

   public Configuration getConfiguration() {
      return configuration;
   }

   public void setConfiguration(Configuration configuration) {
      this.configuration = configuration;
   }

   public String getFreemarkerTemplate() {
      return freemarkerTemplate;
   }

   public void setFreemarkerTemplate(String freemarkerTemplate) {
      this.freemarkerTemplate = freemarkerTemplate;
   }

   public JavaMailSenderImpl getMailSender() {
      return mailSender;
   }

   public void setMailSender(JavaMailSenderImpl mailSender) {
      this.mailSender = mailSender;
   }

   protected Template getTemplate(String templateString) throws IOException {
      return getConfiguration().getTemplate(templateString);
   }

   protected void doSend(MimeMessage msg) {
      try {
         getMailSender().send(msg);
      } catch (MailException ex) {
         logger.error("Unable to send out the email", ex);
      }
   }

   public void confirmEmail(EmailAction emailAction, Map<String, Serializable> root) {
      MimeMessage msg = getMailSender().createMimeMessage();
      try {
         User user = emailAction.getUser();
         if (root == null) {
            root = new HashMap<>();
         }
         root.put("support", SUPPORT_EMAIL);
         root.put("user", user);
         putValue(emailAction, root);
         MimeMessageHelper helper = new MimeMessageHelper(msg, true);
         helper.setFrom(SUPPORT_EMAIL);
         helper.setTo(user.getEmail());        
         helper.setSubject(getMailSubject());
         helper.setText(getMailContent(emailAction, root), true);
         processHelper(helper, emailAction, root);
         doSend(msg);
      } catch (IOException | TemplateException | MessagingException e) {
         logger.error("Unable to read or process freemarker configuration or template", e);
      }
   }

   protected String getMailContent(EmailAction emailAction, Map<String, Serializable> root) throws IOException, TemplateException {
      return FreeMarkerTemplateUtils.processTemplateIntoString(getTemplate(getFreemarkerTemplate()), root);
   }

   protected abstract String getMailSubject();

   protected abstract void putValue(EmailAction emailAction, Map<String, Serializable> root);

   protected void processHelper(MimeMessageHelper helper, EmailAction emailAction, Map<String, Serializable> root) {
   }
   protected String getMainUrl(){
      return applicationProperties.getProperty("app.url");
   }
}
