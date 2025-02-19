package com.test.cts.dao.repo;

import com.test.cts.dao.model.user.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserWalletRepository extends JpaRepository<UserWallet, Long> {
    List<UserWallet> findEntityByUserId(Long userId);
    UserWallet findEntityByUserIdAndSymbol(Long userId,String symbol);
}

