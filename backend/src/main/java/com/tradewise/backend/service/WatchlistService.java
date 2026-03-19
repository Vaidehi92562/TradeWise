package com.tradewise.backend.service;

import com.tradewise.backend.dto.WatchlistRequest;
import com.tradewise.backend.entity.User;
import com.tradewise.backend.entity.Watchlist;
import com.tradewise.backend.repository.UserRepository;
import com.tradewise.backend.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final UserRepository userRepository;

    public Watchlist addToWatchlist(WatchlistRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (watchlistRepository.findByUserAndStockSymbol(user, request.getStockSymbol()).isPresent()) {
            throw new RuntimeException("Stock already in watchlist");
        }

        Watchlist watchlist = Watchlist.builder()
                .stockSymbol(request.getStockSymbol())
                .stockName(request.getStockName())
                .user(user)
                .build();

        return watchlistRepository.save(watchlist);
    }

    public List<Watchlist> getUserWatchlist(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return watchlistRepository.findByUser(user);
    }

    public void removeFromWatchlist(WatchlistRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Watchlist watchlist = watchlistRepository.findByUserAndStockSymbol(user, request.getStockSymbol())
                .orElseThrow(() -> new RuntimeException("Stock not found in watchlist"));

        watchlistRepository.delete(watchlist);
    }
}