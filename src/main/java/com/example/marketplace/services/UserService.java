package com.example.marketplace.services;

import com.example.marketplace.models.Product;
import com.example.marketplace.models.User;
import com.example.marketplace.repositories.ProductRepository;
import com.example.marketplace.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> listUsers(String name) {
        if(name!=null) return userRepository.findByName(name);
        return userRepository.findAll();
    }

    public void saveUser(User user){
        log.info("Saving new{}", user);
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void editUser(Long id, int newBalance) {
        userRepository.findById(id).orElse(null).setBalance(newBalance);

    }
}
