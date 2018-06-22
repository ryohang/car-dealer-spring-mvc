package io.ascending.training.config;

import io.ascending.training.extend.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

//@Configuration
@EnableWebSecurity
public class SecurityConfig  {
//    step1
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.inMemoryAuthentication().withUser("user")
//                .password("password").roles("USER");
//    }
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
//    }
//    step2
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user")
//                .password("password").roles("USER");
//    }
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().antMatchers("/api/users/login","/api/users/signup").permitAll()
//                .and()
//                    .authorizeRequests().antMatchers("/api/**").hasAnyRole("ROLE_REGISTERED_USER","ROLE_ADMIN")
//                .and()
//                    .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
//    }
    @Configuration
    @Order(1)
    public static class RestWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("user")
                .password("password").roles("USER");
        }
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                .antMatchers("/resources/**");
        }
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().authorizeRequests().antMatchers("/api/users/login","/api/users/signup").permitAll()
                .and()
                    .authorizeRequests().antMatchers("/api/**").hasAnyRole("ROLE_REGISTERED_USER","ROLE_ADMIN")
                .and()
                    .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }

    @Configuration
    public static class ResourcesWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers("/resources/**");
        }
    }

}
