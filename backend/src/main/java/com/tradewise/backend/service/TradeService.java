package com.tradewise.backend.service;

import com.tradewise.backend.dto.BuyStockRequest;
import com.tradewise.backend.dto.PortfolioHoldingResponse;
import com.tradewise.backend.dto.SellStockRequest;
import com.tradewise.backend.entity.Trade;
import com.tradewise.backend.entity.User;
import com.tradewise.backend.repository.TradeRepository;
import com.tradewise.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;
    private final UserRepository userRepository;

    public Trade buyStock(BuyStockRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        double totalAmount = request.getPrice() * request.getQuantity();

        if (user.getBalance() < totalAmount) {
            throw new RuntimeException("Insufficient balance");
        }

        user.setBalance(user.getBalance() - totalAmount);
        userRepository.save(user);

        Trade trade = Trade.builder()
                .stockSymbol(request.getStockSymbol())
                .stockName(request.getStockName())
                .type("BUY")
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .user(user)
                .build();

        return tradeRepository.save(trade);
    }

    public Trade sellStock(SellStockRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Trade> trades = tradeRepository.findByUser(user);

        int totalBoughtQuantity = trades.stream()
                .filter(trade -> trade.getStockSymbol().equalsIgnoreCase(request.getStockSymbol()))
                .mapToInt(trade -> "BUY".equalsIgnoreCase(trade.getType()) ? trade.getQuantity() : -trade.getQuantity())
                .sum();

        if (request.getQuantity() > totalBoughtQuantity) {
            throw new RuntimeException("Not enough stock quantity to sell");
        }

        double totalAmount = request.getPrice() * request.getQuantity();
        user.setBalance(user.getBalance() + totalAmount);
        userRepository.save(user);

        Trade trade = Trade.builder()
                .stockSymbol(request.getStockSymbol())
                .stockName(request.getStockName())
                .type("SELL")
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .user(user)
                .build();

        return tradeRepository.save(trade);
    }

    public List<Trade> getTradesByUserEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return tradeRepository.findByUser(user);
    }

    public List<PortfolioHoldingResponse> getPortfolioByUserEmail(String email) {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    List<Trade> trades = tradeRepository.findByUser(user);

    Map<String, PortfolioHoldingResponse> holdingsMap = new LinkedHashMap<>();

    for (Trade trade : trades) {
        String symbol = trade.getStockSymbol();
        double tradeAmount = trade.getPrice() * trade.getQuantity();

        if (!holdingsMap.containsKey(symbol)) {
            holdingsMap.put(
                    symbol,
                    PortfolioHoldingResponse.builder()
                            .stockSymbol(trade.getStockSymbol())
                            .stockName(trade.getStockName())
                            .totalQuantity(0)
                            .averagePrice(0.0)
                            .investedAmount(0.0)
                            .build()
            );
        }

        PortfolioHoldingResponse existing = holdingsMap.get(symbol);

        if ("BUY".equalsIgnoreCase(trade.getType())) {
            int newQuantity = existing.getTotalQuantity() + trade.getQuantity();
            double newInvestedAmount = existing.getInvestedAmount() + tradeAmount;
            double newAveragePrice = newQuantity > 0 ? newInvestedAmount / newQuantity : 0.0;

            existing.setTotalQuantity(newQuantity);
            existing.setInvestedAmount(newInvestedAmount);
            existing.setAveragePrice(newAveragePrice);

        } else if ("SELL".equalsIgnoreCase(trade.getType())) {
            int newQuantity = existing.getTotalQuantity() - trade.getQuantity();

            if (newQuantity < 0) {
                newQuantity = 0;
            }

            double newInvestedAmount = existing.getAveragePrice() * newQuantity;
            double newAveragePrice = newQuantity > 0 ? newInvestedAmount / newQuantity : 0.0;

            existing.setTotalQuantity(newQuantity);
            existing.setInvestedAmount(newInvestedAmount);
            existing.setAveragePrice(newAveragePrice);
        }
    }

    return holdingsMap.values()
            .stream()
            .filter(h -> h.getTotalQuantity() > 0)
            .toList();
}
}