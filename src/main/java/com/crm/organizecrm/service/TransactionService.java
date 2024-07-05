package com.crm.organizecrm.service;

import com.crm.organizecrm.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    Transaction updateTransaction(Long id, Transaction transaction);
    void deleteTransaction(Long id);
    Optional<Transaction> getTransactionById(Long id);
    List<Transaction> getAllTransactions();
}
