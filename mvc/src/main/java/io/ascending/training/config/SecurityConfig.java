package io.ascending.training.config;

import io.ascending.training.extend.security.JwtAuthenticationFilter;
import io.ascending.training.extend.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {
    @Configuration
    @Order(1)
    public static class RestWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
        //curl -i -X POST -d username=ryohang1 -d password=password123 -c ./cookies.txt http://localhost:8080/login
        //curl --cookie ./cookies.txt http://localhost:8080/api/cars
        @Autowired
        private UserDetailsService userDetailsService;
        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
        }

        @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                .antMatchers("/resources/**");
        }
        protected void configure(HttpSecurity http) throws Exception {
            //http://www.baeldung.com/securing-a-restful-web-service-with-spring-security
            http.addFilterAt(new AnonymousAuthenticationFilter("ascending_key") ,AnonymousAuthenticationFilter.class)
                    .addFilterAt(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
                    .csrf().disable().authorizeRequests().antMatchers("/api/users/login","/api/users/signup").permitAll()
                .and()
                    //enable curl
                    //.authorizeRequests().antMatchers("/api/**").authenticated()
                    .authorizeRequests().antMatchers("/api/**").hasAnyRole("REGISTERED_USER","ADMIN")
                .and()
                    .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .and()
//                    .formLogin();
        }
    }

    @Configuration
    public static class ResourcesWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers("/resources/**","/swagger-ui.html","/webjars/**");
        }
    }

}
