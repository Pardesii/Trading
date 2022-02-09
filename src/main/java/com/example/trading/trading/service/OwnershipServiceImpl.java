package com.example.trading.trading.service;

import com.example.trading.trading.model.Ownership;
import com.example.trading.trading.repository.OwnershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnershipServiceImpl implements OwnershipService{
    OwnershipRepository ownershipRepository;
    public OwnershipServiceImpl(OwnershipRepository ownershipRepository) {
        this.ownershipRepository = ownershipRepository;
    }
    @Override
    public Ownership getOwnership(Long stockId, Long userId) {
        return ownershipRepository.getOwnership(stockId, userId);
    }

    @Override
    public Ownership saveOwnership(Ownership ownership) {
        return ownershipRepository.save(ownership);
    }

    @Override
    public void deleteOwnership(Long userId, Long stockId) {
        ownershipRepository.deleteOwnership(userId, stockId);
    }

    @Override
    public List<Ownership> getAllHeldStocks(Long id) {
        return ownershipRepository.getAllHeldStocks(id);
    }
}
