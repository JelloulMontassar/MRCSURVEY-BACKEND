package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.dto.SubscriptionDTO;
import com.crm.organizecrm.exception.SubscriptionNotFoundException;
import com.crm.organizecrm.mapper.SubscriptionMapper;
import com.crm.organizecrm.model.Subscription;
import com.crm.organizecrm.repository.SubscriptionRepository;
import com.crm.organizecrm.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = SubscriptionMapper.toEntity(subscriptionDTO);
        return SubscriptionMapper.toDTO(subscriptionRepository.save(subscription));
    }

    @Override
    public SubscriptionDTO updateSubscription(Long id, SubscriptionDTO subscriptionDTO) {
        Subscription existingSubscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found with id: " + id));
        existingSubscription.setPlanName(subscriptionDTO.getPlanName());
        existingSubscription.setFeatures(subscriptionDTO.getFeatures());
        existingSubscription.setPrice(subscriptionDTO.getPrice());
        existingSubscription.setDuration(subscriptionDTO.getDuration());
        return SubscriptionMapper.toDTO(subscriptionRepository.save(existingSubscription));
    }

    @Override
    public void deleteSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found with id: " + id));
        subscriptionRepository.delete(subscription);
    }

    @Override
    public Optional<SubscriptionDTO> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id).map(SubscriptionMapper::toDTO);
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(SubscriptionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
