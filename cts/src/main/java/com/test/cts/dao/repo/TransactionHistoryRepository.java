package com.test.cts.dao.repo;

import com.test.cts.dao.model.user.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    List<TransactionHistory> findAllByUserId(Long userId);
}
