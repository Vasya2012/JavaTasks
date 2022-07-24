package com.example.marketplace.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "surname", columnDefinition = "text")
    private String surname;
    @Column(name = "balance")
    private int balance;

    @ManyToMany
    @JoinTable(
            name = "userProducts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> userProducts = new ArrayList<>();
}
