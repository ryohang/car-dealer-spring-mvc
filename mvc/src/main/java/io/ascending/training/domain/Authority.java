package io.ascending.training.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created By.
 * User: hanqinghang
 */


@Entity
@Table(name="authorities")
public class Authority implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="authorities_id_seq")
    @SequenceGenerator(name="authorities_id_seq", sequenceName="authorities_id_seq", allocationSize=1)
    private Long id;
    
    @NotNull
    private String authority;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    public Authority(){}


    public Authority(User user, String authority){
        this.user=user;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

}