package com.example.trading.trading.repository;

import com.example.trading.trading.model.Stock;
import com.example.trading.trading.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);


    @Modifying
    @Query(value = "UPDATE user SET wallet = :prev where id = :user_id",
            nativeQuery = true)
    void modifyWallet(@Param("user_id") Long id, @Param("prev") Long prev);
}