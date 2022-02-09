package com.example.trading.trading.service;


import com.example.trading.trading.model.Favorite;
import org.springframework.transaction.annotation.Transactional;

public interface FavoriteService{
    Favorite addToFavorite(long stockId, long userId);

    @Transactional
    void removeFromFavorite(long stockId, long userId);
}