package com.tradewise.backend.repository;

import com.tradewise.backend.entity.Trade;
import com.tradewise.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findByUser(User user);
}