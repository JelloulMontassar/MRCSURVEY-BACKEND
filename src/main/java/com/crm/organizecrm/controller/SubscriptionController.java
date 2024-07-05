package com.crm.organizecrm.controller;

import com.crm.organizecrm.model.Subscription;
import com.crm.organizecrm.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscriptions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        return ResponseEntity.ok(subscriptionService.createSubscription(subscription));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable Long id, @RequestBody Subscription subscription) {
        return ResponseEntity.ok(subscriptionService.updateSubscription(id, subscription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Subscription>> getSubscriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }
}
