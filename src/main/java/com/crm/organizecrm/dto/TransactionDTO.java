package com.crm.organizecrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionDTO {
    private Long id;
    private Double amount;
    private LocalDateTime date;
    private Long clientId;
    private Long employeeId;
}
