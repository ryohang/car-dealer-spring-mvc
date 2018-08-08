package io.ascending.training.extend.security;

import java.io.Serializable;

/**
 * Created by ryo on 4/23/17.
 */
public class RestAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String confirmPassword;

    public RestAuthenticationRequest() {
        super();
    }

    public RestAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public RestAuthenticationRequest(String username, String password, String confirmPassword) {
        this.setUsername(username);
        this.setPassword(password);
        this.setConfirmPassword(confirmPassword);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
