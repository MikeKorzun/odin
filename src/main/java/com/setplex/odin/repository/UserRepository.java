package com.setplex.odin.repository;

import com.setplex.odin.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findOneById(int id);

    @Query("SELECT u FROM User u WHERE u.login = ?1")
    User findOneByLogin(String login);

}