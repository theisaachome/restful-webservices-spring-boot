package com.isaachome.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String firstName;
    private String lastName;
    private  String email;
    private  String phone;

}
