package com.setplex.odin.entity;

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

}
