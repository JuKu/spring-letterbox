package com.jukusoft.letterbox.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jukusoft.authentification.jwt.account.IAccount;
import com.jukusoft.letterbox.entity.general.AbstractEntity;
import com.jukusoft.letterbox.entity.general.LogEntryEntity;
import com.jukusoft.letterbox.entity.message.MessageEntity;
import com.jukusoft.letterbox.entity.ts.TSGroupEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", indexes = {
        //@Index(columnList = "email", name = "email_idx"),
}, uniqueConstraints = {
        @UniqueConstraint(columnNames = "username", name = "username_uqn")
})
@Cacheable//use second level cache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEntity extends AbstractEntity implements IAccount {

    @Size(min = 2, max = 45)
    @Column(name = "username", unique = true, nullable = false, updatable = true)
    @NotEmpty(message = "username is required")
    private String username;

    /**
     * if password is null, no login is allowed
     */
    @Size(min = 2, max = 255)
    @Column(name = "password", unique = false, nullable = true, updatable = true)
    //@NotEmpty(message = "password is required")
    private String password;

    @Size(min = 2, max = 255)
    @Column(name = "salt", unique = false, nullable = true, updatable = true)
    private String salt;

    @Size(min = 2, max = 45)
    @Column(name = "source", unique = false, nullable = false, updatable = true)
    @NotEmpty(message = "source is required")
    private String source;

    @Size(min = 2, max = 90)
    @Column(name = "mail", unique = false, nullable = true, updatable = true)
    private String mail;

    @JsonIgnore
    @OneToOne(mappedBy = "user", orphanRemoval = true, optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserPreferencesEntity userPreferences;

    @ManyToMany(/*mappedBy = "id", */cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<RoleEntity> roles = new ArrayList<>();

    @Size(min = 2, max = 255)
    @Column(name = "last_ip_address", unique = false, nullable = true, updatable = true)
    private String lastIP;

    @OneToMany(/*mappedBy = "customer", */cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    //@Column(name = "user_entity")
    private List<LogEntryEntity> logs = new ArrayList<>();

    @ManyToMany(/*mappedBy = "id", */cascade = {}, fetch = FetchType.LAZY)
    private List<TSGroupEntity> tsGroups = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Column(name = "receiver")
    private List<MessageEntity> messages = new ArrayList<>();

    @Size(min = 2, max = 90)
    @Column(name = "prename", unique = false, nullable = false, updatable = true)
    private String prename;

    @Size(min = 2, max = 90)
    @Column(name = "lastname", unique = false, nullable = false, updatable = true)
    private String lastname;

    public UserEntity(@Size(min = 2, max = 45) @NotEmpty(message = "username is required") String username, String prename, String lastname) {
        Objects.requireNonNull(username);

        if (username.isEmpty()) {
            throw new IllegalArgumentException("username cannot be empty");
        }

        this.username = username;
        this.prename = prename;
        this.lastname = lastname;
        this.source = "local";
    }

    private UserEntity() {
        //
    }

    @Override
    public long getUserID() {
        return getId();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserPreferencesEntity getUserPreferences() {
        if (userPreferences == null) {
            userPreferences = new UserPreferencesEntity(this);
        }

        return userPreferences;
    }

    public List<RoleEntity> listRoles() {
        return roles;
    }

    public void addRole(final RoleEntity role) {
        if (!this.roles.contains(role)) {
            this.roles.add(role);
        }
    }

    public void removeRole(RoleEntity role) {
        this.roles.remove(role);
    }

    public List<TSGroupEntity> listTSGroups() {
        return tsGroups;
    }

    public List<MessageEntity> listMessages() {
        return messages;
    }

    public void addMessage(MessageEntity message) {
        this.messages.add(message);
    }

    @PrePersist
    public final void prePersist1() {
        if (this.userPreferences == null) {
            this.userPreferences = new UserPreferencesEntity(this);
        }
    }

}
