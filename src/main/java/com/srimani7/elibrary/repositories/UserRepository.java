package com.srimani7.elibrary.repositories;

import com.srimani7.elibrary.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserNameAndPassword(String userName, String password);

    User findByUserName(String userName);
}
