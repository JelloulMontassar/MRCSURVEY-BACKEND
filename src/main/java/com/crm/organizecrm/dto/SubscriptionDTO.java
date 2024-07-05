package com.crm.organizecrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubscriptionDTO {
    private Long id;
    private String planName;
    private String features;
    private Double price;
    private Integer duration;
    private Long hrUserId;
}
