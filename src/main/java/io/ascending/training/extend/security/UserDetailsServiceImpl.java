package io.ascending.training.extend.security;

import io.ascending.training.domain.Authority;
import io.ascending.training.domain.User;
import io.ascending.training.domain.Utils;
import io.ascending.training.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Created By.
 * User: hanqinghang
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailorUsername) throws UsernameNotFoundException {
            logger.debug(emailorUsername+" is trying to log in from spring security");
            User domainUser = null;
            try {
                domainUser = userService.findByEmailOrUsername(emailorUsername);
            }catch (Exception repositoryProblem) {
                logger.debug("catch AuthenticationServiceException from AuthenticationProvider");
                throw new UsernameNotFoundException("can't find username in database: "+domainUser.getUsername());
            }
            if (domainUser == null) {
                throw new BadCredentialsException("AbstractUserDetailsAuthenticationProvider.UsernameNotFound " + emailorUsername +" has no GrantedAuthority");
            }
            List<Authority> userAuthorities = userService.findAuthorities(domainUser);
//            Collection<GrantedAuthority> authorities = Utils.getAuthorities(userAuthorities);
            domainUser.setAuthorities(userAuthorities);
            return  domainUser;
    }
}