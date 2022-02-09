package com.example.trading.trading.service;

import com.example.trading.trading.model.Transaction;
import com.example.trading.trading.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{
    TransactionRepository transactionRepository;
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Override
    public Transaction saveTransaction(Transaction transaction) {
        try {
            return transactionRepository.save(transaction);
        }
        catch (Exception e) {
            System.out.println("Error here");
        }
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsForAUser(Long id) {
        return transactionRepository.getAllTransactionsForAUser(id);
    }

    @Override
    public Page<Transaction> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.transactionRepository.findAll(pageable);
    }

}
