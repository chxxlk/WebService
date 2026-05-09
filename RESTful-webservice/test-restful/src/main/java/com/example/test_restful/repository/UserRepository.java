package com.example.test_restful.repository;

import com.example.test_restful.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
