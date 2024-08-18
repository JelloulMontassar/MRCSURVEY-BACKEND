package com.crm.organizecrm.controller;

import com.crm.organizecrm.dto.SubscriptionDTO;
import com.crm.organizecrm.model.UserSubscription;
import com.crm.organizecrm.service.SubscriptionService;
import com.crm.organizecrm.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscriptions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final UserSubscriptionService userSubscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionDTO> createSubscription(@Valid @RequestBody SubscriptionDTO subscriptionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(subscriptionService.createSubscription(subscriptionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> updateSubscription(@PathVariable Long id, @Valid @RequestBody SubscriptionDTO subscriptionDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(subscriptionService.updateSubscription(id, subscriptionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<SubscriptionDTO>> getSubscriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }
    @PostMapping("/user/{userId}/{subscriptionId}/subscription")
    public ResponseEntity<UserSubscription> assignSubscription(@PathVariable Long userId, @PathVariable Long subscriptionId) {
        UserSubscription subscription = userSubscriptionService.assignSubscriptionToUser(subscriptionId, userId);
        return ResponseEntity.ok(subscription);
    }
}
