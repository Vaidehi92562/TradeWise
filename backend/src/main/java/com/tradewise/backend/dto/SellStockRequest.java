package com.tradewise.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellStockRequest {
    private String email;
    private String stockSymbol;
    private String stockName;
    private Integer quantity;
    private Double price;
}