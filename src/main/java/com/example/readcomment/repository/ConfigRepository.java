package com.example.readcomment.repository;


import com.example.readcomment.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config,Long> {
    Config findOneById(Long id);
}
