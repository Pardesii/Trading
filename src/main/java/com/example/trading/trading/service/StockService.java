package com.example.trading.trading.service;


import com.example.trading.trading.model.Stock;

import java.util.List;

public interface StockService{
    List<Stock> getAllStocks();
    List<Stock> getAllStocksByCategory(String category);
    List<Stock> getAllStocksNonFavorite(Long userId);
    List<Stock> getAllStocksFavorite(Long userId);

    List<Stock> getAllStocksByCategoryFav(String cat, Long userId);

    List<Stock> getAllStocksByCategoryNonFav(String cat, long userId);

    Long getPerStockPrice(Long stockId);

    Stock getIndividualStock(long stockId);

    Stock saveStock(Stock stock);

    String getStockNameFromStockId(Long stockId);
}
