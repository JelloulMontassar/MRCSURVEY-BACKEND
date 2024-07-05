package com.crm.organizecrm.service;

import com.crm.organizecrm.dto.TransactionDTO;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
    TransactionDTO updateTransaction(Long id, TransactionDTO transactionDTO);
    void deleteTransaction(Long id);
    Optional<TransactionDTO> getTransactionById(Long id);
    List<TransactionDTO> getAllTransactions();
}
