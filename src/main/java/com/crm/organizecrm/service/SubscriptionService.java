package com.crm.organizecrm.service;

import com.crm.organizecrm.dto.SubscriptionDTO;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO);
    SubscriptionDTO updateSubscription(Long id, SubscriptionDTO subscriptionDTO);
    void deleteSubscription(Long id);
    Optional<SubscriptionDTO> getSubscriptionById(Long id);
    List<SubscriptionDTO> getAllSubscriptions();
}
