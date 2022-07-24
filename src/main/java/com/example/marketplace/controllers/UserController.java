package com.example.marketplace.controllers;

import com.example.marketplace.models.Product;
import com.example.marketplace.models.User;
import com.example.marketplace.services.ProductService;
import com.example.marketplace.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/users")
    public String users(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("users", userService.listUsers(name));
        return "users";
    }

    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "user-info";
    }

    @PostMapping("/user/create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }
    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user/{userId}/buy")
    public String userBuy(@RequestParam(id = "id") int productId), @PathVariable Long userId){
        int currentBalance = userService.getUserById(userId).getBalance();
        int newBalance = model.getAttribute();
        userService.editUser(userId, 12);
        return "redirect:/user/{userId}";
    }

}
