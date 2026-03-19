package com.tradewise.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PortfolioHoldingResponse {
    private String stockSymbol;
    private String stockName;
    private Integer totalQuantity;
    private Double averagePrice;
    private Double investedAmount;
}