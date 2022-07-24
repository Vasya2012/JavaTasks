package com.example.marketplace.repositories;

import com.example.marketplace.models.Product;
import com.example.marketplace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
