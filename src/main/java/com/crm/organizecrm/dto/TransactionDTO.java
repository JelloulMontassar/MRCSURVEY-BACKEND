package com.crm.organizecrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionDTO {

    private Long id;

    // @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private Double amount;

    // @NotNull(message = "Date cannot be null")
    private LocalDateTime date;

    private Long clientId;

    private Long employeeId;
}
