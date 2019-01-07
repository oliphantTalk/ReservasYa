package com.ttps.reservasya.repository.transaction;

import com.ttps.reservasya.models.user.User;
import com.ttps.reservasya.models.user.history.UserTransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTransactionHistoryRepository extends JpaRepository<UserTransactionHistory, Long> {

    Optional<UserTransactionHistory> findByUser(User user);

}
