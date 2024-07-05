package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.exception.ResourceNotFoundException;
import com.crm.organizecrm.model.Subscription;
import com.crm.organizecrm.repository.SubscriptionRepository;
import com.crm.organizecrm.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription updateSubscription(Long id, Subscription subscription) {
        Subscription existingSubscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found with id: " + id));
        existingSubscription.setPlanName(subscription.getPlanName());
        existingSubscription.setFeatures(subscription.getFeatures());
        existingSubscription.setPrice(subscription.getPrice());
        existingSubscription.setDuration(subscription.getDuration());
        existingSubscription.setHrUser(subscription.getHrUser());
        return subscriptionRepository.save(existingSubscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found with id: " + id));
        subscriptionRepository.delete(subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }
}
