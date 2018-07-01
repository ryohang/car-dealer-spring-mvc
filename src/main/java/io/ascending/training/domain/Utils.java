package io.ascending.training.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created By.
 * User: hanqinghang
 */
public class Utils {

    public static Collection<GrantedAuthority> getAuthorities(List<Authority> authorities) {
        List<GrantedAuthority> authList = new ArrayList<>();
        for (Authority auth : authorities){
            String ROLE = auth.getAuthority().toUpperCase();
            authList.add(new SimpleGrantedAuthority(ROLE));
        }
        return authList;
    }

    public static List<String> getAuthoritiesAsString(Collection<? extends GrantedAuthority> authorities) {
        List<String> authList = new ArrayList<>();
        for (GrantedAuthority auth : authorities){
            authList.add(auth.getAuthority());
        }
        return authList;
    }

}
