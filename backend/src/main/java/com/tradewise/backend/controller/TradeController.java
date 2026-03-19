package com.tradewise.backend.controller;

import com.tradewise.backend.dto.BuyStockRequest;
import com.tradewise.backend.dto.PortfolioHoldingResponse;
import com.tradewise.backend.dto.SellStockRequest;
import com.tradewise.backend.entity.Trade;
import com.tradewise.backend.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @PostMapping("/buy")
    public Trade buyStock(@RequestBody BuyStockRequest request) {
        return tradeService.buyStock(request);
    }

    @PostMapping("/sell")
    public Trade sellStock(@RequestBody SellStockRequest request) {
        return tradeService.sellStock(request);
    }

    @GetMapping("/user")
    public List<Trade> getUserTrades(@RequestParam String email) {
        return tradeService.getTradesByUserEmail(email);
    }

    @GetMapping("/portfolio")
    public List<PortfolioHoldingResponse> getPortfolio(@RequestParam String email) {
        return tradeService.getPortfolioByUserEmail(email);
    }
}