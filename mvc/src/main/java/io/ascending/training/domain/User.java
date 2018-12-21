package io.ascending.training.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="users")
public class User implements Serializable,UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="users_id_seq")
    @SequenceGenerator(name="users_id_seq", sequenceName="users_id_seq", allocationSize=1)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @Column(name = "confirm_password")
    private String confirmPassword;

    @JsonIgnore
    @Column(name = "confirm_token")
    private String confirmToken;

    @NotNull
    @CreationTimestamp
    @Column(name="create_at")
    private Instant createdAt = Instant.now();

    @UpdateTimestamp
    @Column(name="update_at")
    private Instant updateAt;

    @Column(name="last_login_at")
    private Instant lastLoginAt;

    @JsonIgnore
    @Column(name = "last_reset_at")
    private Instant lastResetAt;

    @JsonIgnore
    @Column(name = "confirm_at")
    private Instant confirmAt;

    @NotNull
    @JsonIgnore
    @Column(name = "confirm_status")
    private Integer confirmStatus;

    @Column
    private String timezone;

    @JsonIgnore
    private Boolean enabled = true;

    @JsonIgnore
    private Boolean locked = false;

    @JsonIgnore
    private Boolean expired = false;

    @Transient
    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.expired = accountNonExpired;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmToken() {
        return confirmToken;
    }

    public void setConfirmToken(String confirmToken) {
        this.confirmToken = confirmToken;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public Instant getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Instant lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public Instant getLastResetAt() {
        return lastResetAt;
    }

    public void setLastResetAt(Instant lastResetAt) {
        this.lastResetAt = lastResetAt;
    }

    public Instant getConfirmAt() {
        return confirmAt;
    }

    public void setConfirmAt(Instant confirmAt) {
        this.confirmAt = confirmAt;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
