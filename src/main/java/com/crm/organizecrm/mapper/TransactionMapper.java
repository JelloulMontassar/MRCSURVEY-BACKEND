package com.crm.organizecrm.mapper;

import com.crm.organizecrm.dto.TransactionDTO;
import com.crm.organizecrm.model.Transaction;

public class TransactionMapper {

    public static TransactionDTO toDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .clientId(transaction.getClient() != null ? transaction.getClient().getId() : null)
                .employeeId(transaction.getEmployee() != null ? transaction.getEmployee().getId() : null)
                .build();
    }

    public static Transaction toEntity(TransactionDTO transactionDTO) {
        return Transaction.builder()
                .id(transactionDTO.getId())
                .amount(transactionDTO.getAmount())
                .date(transactionDTO.getDate())
                .build();
    }
}
