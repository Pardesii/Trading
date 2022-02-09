package com.example.trading.trading.repository;

import com.example.trading.trading.model.Favorite;
import com.example.trading.trading.model.Stock;
import com.example.trading.trading.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long>{

    @Modifying
    @Query(value = "DELETE FROM favorite where id <> 0 and stock_id = :stock_id and user_id = :user_id",
            nativeQuery = true)
    void removeFromFavorite(@Param("stock_id") long stockId, @Param("user_id") long userId);
}