package com.example.marketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "price")
    private int price;

    @JsonIgnore
    @ManyToMany(mappedBy = "userProducts")

    private List<User> productUsers = new ArrayList<>();

    }

