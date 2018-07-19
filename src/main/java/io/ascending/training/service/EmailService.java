package io.ascending.training.service;

import io.ascending.training.domain.EmailAction;
import io.ascending.training.domain.User;
import io.ascending.training.enumdef.EmailCategory;
import io.ascending.training.enumdef.UserConfirmStatus;
import io.ascending.training.mail.RegistrationEmail;
import io.ascending.training.repository.EmailActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

@Service
public class EmailService extends CrudService<EmailAction,Long>{
    @Autowired
    private EmailActionRepository emailActionRepository;
    @Override
    protected CrudRepository<EmailAction, Long> getCrudRepository() {
        return emailActionRepository;
    }
    @Autowired
    private RegistrationEmail registrationEmail;

    /**
     * worker pick up the jms and send confirmationEmail to user
     *
     * @param user
     */
    @Transactional
    public void sendInviteEmailToNewUser(User user) {
        if (user.getConfirmStatus() == UserConfirmStatus.CREATED.ordinal()) {
            try {
                EmailAction mailA = new EmailAction(user);
                mailA.setCreateAt(Instant.now());
                save(mailA);
                registrationEmail.confirmEmail(mailA, null);
                mailA.setCategory(EmailCategory.RegistrationEmail.getValue());
                emailActionRepository.save(mailA);
            } catch (RuntimeException e) {
                logger.error("Send the confirm email to " + user.getUsername() + " error!", e);
            }
        }
    }
}
