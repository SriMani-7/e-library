package com.srimani7.elibrary.repositories;

import com.srimani7.elibrary.Entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByUsernameAndPassword(String userName, String password);

    Optional<MyUser> findByUsername(String userName);

    boolean existsByUsername(String username);
}
