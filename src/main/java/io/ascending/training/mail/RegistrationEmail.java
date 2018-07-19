
package io.ascending.training.mail;

import freemarker.template.TemplateException;
import io.ascending.training.domain.EmailAction;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class RegistrationEmail extends AbstractFreemarkerEmail {

   @Override
   protected String getMailSubject() {
      return "Important: verify your email";
   }

   @Override
   protected void putValue(EmailAction emailAction, Map<String, Serializable> root) {
      String token = emailAction.getUser().getConfirmToken();
      String url = getMainUrl() + "/users/verification?confirmation_token=" + token;
      root.put("activation_url", url);
      root.put("url", getMainUrl());
   }

   @Override
   protected String getMailContent(EmailAction emailAction, Map<String, Serializable> root) throws IOException, TemplateException {
      return super.getMailContent(emailAction, root);
   }
}
