package com.example.readcomment.repository;

import com.example.readcomment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findOneByUid(String uid);
}
