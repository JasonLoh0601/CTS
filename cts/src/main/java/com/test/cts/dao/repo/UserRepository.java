package com.test.cts.dao.repo;

import com.test.cts.dao.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findEntityById(Long id);
}
