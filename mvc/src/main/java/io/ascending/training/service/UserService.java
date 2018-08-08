package io.ascending.training.service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import io.ascending.training.domain.Authority;
import io.ascending.training.domain.Car;
import io.ascending.training.domain.User;
import io.ascending.training.enumdef.AuthorityRole;
import io.ascending.training.extend.exp.NotFoundException;
import io.ascending.training.repository.AuthorityRepository;
import io.ascending.training.repository.CarRepository;
import io.ascending.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ryo on 5/20/18.
 */
@Service
public class UserService extends CrudService<User,Long> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    protected CrudRepository<User, Long> getCrudRepository() {
        return userRepository;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public User createUser(User newUser) {
        String code = UUID.randomUUID().toString();
        String encodedPass = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPass);
        newUser.setConfirmPassword(encodedPass);
        newUser.setConfirmToken(code);
        newUser.setCreatedAt(Instant.now());
        newUser.setLastResetAt(Instant.now());
        save(newUser);
        addAuthority(newUser,AuthorityRole.ROLE_REGISTERED_USER);
        save(newUser);
        return newUser;
    }

    public List<User> findAll(){
        List<User> users = Lists.newArrayList(userRepository.findAll());
        return users;
    }

    @Transactional(readOnly = true)
    public User findByEmailOrUsername(String keyword) throws NotFoundException,NullPointerException {
        if (keyword == null || "".equals(keyword.trim())) {
            throw new NullPointerException("search keyword is null");
        }
        User user = userRepository.findByEmailIgnoreCase(keyword);
        if (user == null) {
            user = userRepository.findByUsernameIgnoreCase(keyword);
        }
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    // --------------------------//
    // authority servic block //
    // --------------------------//
    @Transactional
    public Authority addAuthority(User user,String authorityString) {
        Authority userAuthority = new Authority(user, authorityString);
        return authorityRepository.save(userAuthority);
    }

    @Transactional(readOnly = true)
    public List<Authority> findAuthorities(User user) {
        List<Authority> roles = authorityRepository.findAuthoritiesByUserId(user.getId());
        return roles;
    }
}
