package com.example.trading.trading.service;

import com.example.trading.trading.model.Ownership;
import org.springframework.transaction.annotation.Transactional;

import java.security.acl.Owner;
import java.util.List;

public interface OwnershipService {
    Ownership getOwnership(Long stockId, Long userId);
    Ownership saveOwnership(Ownership ownership);

    @Transactional
    void deleteOwnership(Long userId, Long stockId);

    List<Ownership> getAllHeldStocks(Long id);
}
