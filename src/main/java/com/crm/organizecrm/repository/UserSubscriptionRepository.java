package com.crm.organizecrm.repository;

import com.crm.organizecrm.model.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {

}
