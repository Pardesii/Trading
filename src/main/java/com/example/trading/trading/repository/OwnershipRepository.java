package com.example.trading.trading.repository;

import com.example.trading.trading.model.Favorite;
import com.example.trading.trading.model.Ownership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnershipRepository extends JpaRepository<Ownership, Long> {
    @Query(value = "SELECT * FROM ownership where stock_id = :stockId and user_id = :userId",
            nativeQuery = true)
    Ownership getOwnership(@Param("stockId") Long stockId,@Param("userId") Long userId);

    @Modifying
    @Query(value = "DELETE FROM ownership where id <> 0 and user_id = :userId and stock_id = :stockId",
            nativeQuery = true)
    void deleteOwnership(@Param("userId") Long userId, @Param("stockId") Long stockId);

    @Query(value = "SELECT * FROM ownership where user_id = :userId",
            nativeQuery = true)
    List<Ownership> getAllHeldStocks(@Param("userId") Long userId);
}
