package com.crm.organizecrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
public class SubscriptionDTO {

    private Long id;

    @NotBlank(message = "Plan name cannot be blank")
    @Size(max = 255, message = "Plan name must be less than or equal to 255 characters")
    private String planName;

    @Size(max = 2000, message = "Features can be up to 2000 characters")
    private String features;

    // @NotNull(message = "Price cannot be null")

    private Double price;

    // @NotNull(message = "Duration cannot be null")
    @Positive(message = "Duration must be positive")
    private Integer duration;

    private Long hrUserId;
}
