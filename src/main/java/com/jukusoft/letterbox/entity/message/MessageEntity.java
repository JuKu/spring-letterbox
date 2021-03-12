package com.jukusoft.letterbox.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jukusoft.letterbox.entity.general.AbstractEntity;
import com.jukusoft.letterbox.entity.user.UserEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "messages", indexes = {
        //@Index(columnList = "email", name = "email_idx"),
}, uniqueConstraints = {
        @UniqueConstraint(columnNames = "receiver", name = "receiver_id_uqn")
})
@Cacheable//use second level cache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageEntity extends AbstractEntity {

    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver", unique = false, nullable = false, updatable = false)//don't use an extra table, use join column instead
    @JsonIgnore
    private UserEntity receiver;

    @ManyToOne(optional = false, cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "sender", unique = false, nullable = false, updatable = false)//don't use an extra table, use join column instead
    @JsonIgnore
    private UserEntity sender;

    @Column(name = "sender_is_anonym", unique = false, nullable = false, updatable = true)
    private boolean anonym;

    @Column(name = "title", unique = false, nullable = false, updatable = true)
    private String title;

    @Column(name = "text", unique = false, nullable = false, updatable = true)
    private String text;

    public MessageEntity(UserEntity receiver, UserEntity sender, boolean anonym, String title, String text) {
        this.receiver = receiver;
        this.sender = sender;
        this.anonym = anonym;
        this.title = title;
        this.text = text;
    }

    public UserEntity getReceiver() {
        return receiver;
    }

    public UserEntity getSender() {
        return sender;
    }

    public boolean isAnonym() {
        return anonym;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

}
