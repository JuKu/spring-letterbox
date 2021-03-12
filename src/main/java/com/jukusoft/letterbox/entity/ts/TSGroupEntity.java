package com.jukusoft.letterbox.entity.ts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jukusoft.letterbox.entity.general.AbstractEntity;
import com.jukusoft.letterbox.entity.user.UserEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "ts_groups", indexes = {
        //@Index(columnList = "email", name = "email_idx"),
}, uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "name_uqn")
})
@Cacheable//use second level cache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TSGroupEntity extends AbstractEntity {

    @Size(min = 2, max = 45)
    @Column(name = "name", unique = true, nullable = false, updatable = true)
    @NotEmpty(message = "name is required")
    private String name;

    @JsonIgnore
    @ManyToMany(/*mappedBy = "id", */cascade = {}, fetch = FetchType.LAZY)
    private List<UserEntity> members;

    public TSGroupEntity(@Size(min = 2, max = 45) @NotEmpty(message = "name is required") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
