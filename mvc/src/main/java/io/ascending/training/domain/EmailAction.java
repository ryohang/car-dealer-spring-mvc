package io.ascending.training.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="email_actions")
public class EmailAction implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = SEQUENCE, generator = "email_action_id_seq")
    @SequenceGenerator(name = "email_action_id_seq", sequenceName = "email_action_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @CreationTimestamp
    @Column(name="create_at")
    private Instant createAt = Instant.now();

    @UpdateTimestamp
    @Column(name="update_at")
    private Instant updateAt;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    protected Integer category = 0;

    public EmailAction(){}

    public EmailAction(User user){
        this.user= user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
