package com.example.trading.trading.service;

import com.example.trading.trading.model.Transaction;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);

    List<Transaction> getAllTransactionsForAUser(Long id);

    Page < Transaction > findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
