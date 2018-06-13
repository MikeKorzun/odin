package com.setplex.odin.repository;

import com.setplex.odin.entity.Provider;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ProviderRepo extends JpaRepository<Provider, Integer> {

    @Query("SELECT p FROM Provider p WHERE p.id = ?1")
    Provider findOneById(int providerId);

    @Query("SELECT p FROM Provider p")
    List<Provider> findAll();
}
