package com.example.marketplace.controllers;

import com.example.marketplace.models.Product;
import com.example.marketplace.models.User;
import com.example.marketplace.services.ProductService;
import com.example.marketplace.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    //private final UserService userService;
    @GetMapping("/products")
    public String products(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("products", productService.listProducts(name));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(Product product){
        productService.saveProduct(product);
        return "redirect:/products";
    }
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }

   /* @PostMapping("/{productId}/users/{userId}")
    public String enrollUserToProduct(@PathVariable Long productId, Long userId){
        Product product = productService.getProductById(productId);
        User user = userService.getUserById(userId);
        product.getProductUsers().add();
            }*/

}
