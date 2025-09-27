package com.lab.performance_lab.repository;

import com.lab.performance_lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
