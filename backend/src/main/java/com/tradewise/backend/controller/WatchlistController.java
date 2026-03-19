package com.tradewise.backend.controller;

import com.tradewise.backend.dto.WatchlistRequest;
import com.tradewise.backend.entity.Watchlist;
import com.tradewise.backend.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping("/add")
    public Watchlist addToWatchlist(@RequestBody WatchlistRequest request) {
        return watchlistService.addToWatchlist(request);
    }

    @GetMapping("/user")
    public List<Watchlist> getUserWatchlist(@RequestParam String email) {
        return watchlistService.getUserWatchlist(email);
    }

    @DeleteMapping("/remove")
    public String removeFromWatchlist(@RequestBody WatchlistRequest request) {
        watchlistService.removeFromWatchlist(request);
        return "Removed from watchlist successfully";
    }
}