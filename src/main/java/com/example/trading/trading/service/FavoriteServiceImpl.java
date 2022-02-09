package com.example.trading.trading.service;

import com.example.trading.trading.model.Favorite;
import com.example.trading.trading.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService{

    FavoriteRepository favoriteRepository;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public Favorite addToFavorite(long stockId, long userId) {
        Favorite obj = new Favorite(userId, stockId);
        favoriteRepository.save(obj);
        return obj;
    }

    @Override
    public void removeFromFavorite(long stockId, long userId) {
        favoriteRepository.removeFromFavorite(stockId, userId);
    }
}
