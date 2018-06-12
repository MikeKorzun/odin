package com.setplex.odin.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "userId"))
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity {

    private String password;
    private String firstName;
    private String lastName;

}