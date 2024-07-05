package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.dto.TransactionDTO;
import com.crm.organizecrm.exception.ResourceNotFoundException;
import com.crm.organizecrm.mapper.TransactionMapper;
import com.crm.organizecrm.model.Transaction;
import com.crm.organizecrm.repository.TransactionRepository;
import com.crm.organizecrm.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = TransactionMapper.toEntity(transactionDTO);
        return TransactionMapper.toDTO(transactionRepository.save(transaction));
    }

    @Override
    public TransactionDTO updateTransaction(Long id, TransactionDTO transactionDTO) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        existingTransaction.setAmount(transactionDTO.getAmount());
        existingTransaction.setDate(transactionDTO.getDate());
        return TransactionMapper.toDTO(transactionRepository.save(existingTransaction));
    }

    @Override
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        transactionRepository.delete(transaction);
    }

    @Override
    public Optional<TransactionDTO> getTransactionById(Long id) {
        return transactionRepository.findById(id).map(TransactionMapper::toDTO);
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
