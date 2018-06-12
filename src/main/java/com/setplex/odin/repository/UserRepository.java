package com.setplex.odin.repository;

import com.setplex.odin.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findById(int id);
    User findByLastName(String lastName);
}
