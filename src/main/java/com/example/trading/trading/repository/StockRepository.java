package com.example.trading.trading.repository;

import com.example.trading.trading.model.Stock;
import com.example.trading.trading.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
    @Query(value = "SELECT * FROM stock where category = :cat",
            nativeQuery = true)
    List<Stock> getAllStocksByCategory(@Param("cat")String category);

    @Query(value = "SELECT * FROM stock where id not in (select stock_id from favorite where user_id = :userId)",
            nativeQuery = true)
    List<Stock> getAllStocksNonFavorite(@Param("userId")Long userId);

    @Query(value = "SELECT * FROM stock where id in (select stock_id from favorite where user_id = :userId)",
            nativeQuery = true)
    List<Stock> getAllStocksFavorite(@Param("userId")Long userId);

    @Query(value = "SELECT * FROM stock where category = :cat and id in (select stock_id from favorite where user_id = :userId) ",
            nativeQuery = true)
    List<Stock> getAllStocksByCategoryFav(@Param("cat") String cat, @Param("userId")Long userId);

    @Query(value = "SELECT * FROM stock where category = :cat and id not in (select stock_id from favorite where user_id = :userId)",
            nativeQuery = true)
    List<Stock> getAllStocksByCategoryNonFav(@Param("cat")String cat, @Param("userId")long userId);


    @Query(value = "SELECT cur_price FROM stock where id = :stock_id",
            nativeQuery = true)
    Long getPerStockPrice(@Param("stock_id") Long stockId);
    @Query(value = "SELECT * FROM stock where id = :stockId",
            nativeQuery = true)
    Stock findByStockId(@Param("stockId") long stockId);

    @Query(value = "SELECT symbol FROM stock where id = :stockId",
            nativeQuery = true)
    String getStockNameFromStockId(@Param("stockId") Long stockId);
}
