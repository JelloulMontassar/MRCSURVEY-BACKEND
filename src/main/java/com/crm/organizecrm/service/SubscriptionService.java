package com.crm.organizecrm.service;

import com.crm.organizecrm.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    Subscription createSubscription(Subscription subscription);
    Subscription updateSubscription(Long id, Subscription subscription);
    void deleteSubscription(Long id);
    Optional<Subscription> getSubscriptionById(Long id);
    List<Subscription> getAllSubscriptions();
}
