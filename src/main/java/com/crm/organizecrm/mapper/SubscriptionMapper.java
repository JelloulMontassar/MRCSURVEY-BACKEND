package com.crm.organizecrm.mapper;

import com.crm.organizecrm.dto.SubscriptionDTO;
import com.crm.organizecrm.model.Subscription;

public class SubscriptionMapper {

    public static SubscriptionDTO toDTO(Subscription subscription) {
        return SubscriptionDTO.builder()
                .id(subscription.getId())
                .planName(subscription.getPlanName())
                .features(subscription.getFeatures())
                .price(subscription.getPrice())
                .duration(subscription.getDuration())
                .build();
    }

    public static Subscription toEntity(SubscriptionDTO subscriptionDTO) {
        return Subscription.builder()
                .id(subscriptionDTO.getId())
                .planName(subscriptionDTO.getPlanName())
                .features(subscriptionDTO.getFeatures())
                .price(subscriptionDTO.getPrice())
                .duration(subscriptionDTO.getDuration())
                .build();
    }
}
