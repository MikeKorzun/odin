package com.setplex.odin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id"))
})
@Getter
@Setter
public class Provider extends AbstractEntity {

    private int providerId;

    @Column(length = 64, nullable = false)
    private String address;

    private boolean status;

    @Column(length = 32, nullable = false)
    private String token;

    @JsonIgnore
    private int deleted;
}
