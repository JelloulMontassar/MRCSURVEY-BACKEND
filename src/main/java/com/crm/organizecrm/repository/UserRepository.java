package com.crm.organizecrm.repository;

import com.crm.organizecrm.enumirators.Role;
import com.crm.organizecrm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(long resetToken);
    User getUserByEmail(String email);
    List<User> findAllByRole(Role role);
}
