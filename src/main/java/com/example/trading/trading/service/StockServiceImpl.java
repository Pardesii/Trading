package com.example.trading.trading.service;

import com.example.trading.trading.model.Stock;
import com.example.trading.trading.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public List<Stock> getAllStocksByCategory(String category) {
        return stockRepository.getAllStocksByCategory(category);
    }

    @Override
    public List<Stock> getAllStocksNonFavorite(Long userId) {
        return stockRepository.getAllStocksNonFavorite(userId);
    }

    @Override
    public List<Stock> getAllStocksFavorite(Long userId) {
        return stockRepository.getAllStocksFavorite(userId);
    }

    @Override
    public List<Stock> getAllStocksByCategoryFav(String cat, Long userId) {
        return stockRepository.getAllStocksByCategoryFav(cat, userId);
    }

    @Override
    public List<Stock> getAllStocksByCategoryNonFav(String cat, long userId) {
        return stockRepository.getAllStocksByCategoryNonFav(cat, userId);
    }

    @Override
    public Long getPerStockPrice(Long stockId) {
        return stockRepository.getPerStockPrice(stockId);
    }

    @Override
    public Stock getIndividualStock(long stockId) {
        return stockRepository.findByStockId(stockId);
    }

    @Override
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public String getStockNameFromStockId(Long stockId) {
        return stockRepository.getStockNameFromStockId(stockId);
    }

}
