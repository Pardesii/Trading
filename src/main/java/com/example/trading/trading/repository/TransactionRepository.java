package com.example.trading.trading.repository;

import com.example.trading.trading.model.Favorite;
import com.example.trading.trading.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM transaction where user_id = :userId",
            nativeQuery = true)
    List<Transaction> getAllTransactionsForAUser(@Param("userId") Long id);
}
