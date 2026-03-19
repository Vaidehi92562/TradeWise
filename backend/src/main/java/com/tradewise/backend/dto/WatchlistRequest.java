package com.tradewise.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WatchlistRequest {
    private String email;
    private String stockSymbol;
    private String stockName;
}