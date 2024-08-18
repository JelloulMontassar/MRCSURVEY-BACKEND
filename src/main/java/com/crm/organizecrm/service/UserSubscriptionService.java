package com.crm.organizecrm.service;

import com.crm.organizecrm.exception.ResourceNotFoundException;
import com.crm.organizecrm.model.Subscription;
import com.crm.organizecrm.model.User;
import com.crm.organizecrm.model.UserSubscription;
import com.crm.organizecrm.repository.SubscriptionRepository;
import com.crm.organizecrm.repository.UserRepository;
import com.crm.organizecrm.repository.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSubscriptionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    public UserSubscription assignSubscriptionToUser(Long userId, Long subscriptionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found with id " + subscriptionId));

        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setUser(user);
        userSubscription.setSubscription(subscription);

        return userSubscriptionRepository.save(userSubscription);
    }
}

