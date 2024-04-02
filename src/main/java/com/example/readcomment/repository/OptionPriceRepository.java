package com.example.readcomment.repository;

import com.example.readcomment.entity.OptionPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionPriceRepository extends JpaRepository<OptionPrice,Long> {
    OptionPrice findOneById(Long id);
}
