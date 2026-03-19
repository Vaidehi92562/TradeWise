package com.tradewise.backend.repository;

import com.tradewise.backend.entity.User;
import com.tradewise.backend.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
    List<Watchlist> findByUser(User user);
    Optional<Watchlist> findByUserAndStockSymbol(User user, String stockSymbol);
}