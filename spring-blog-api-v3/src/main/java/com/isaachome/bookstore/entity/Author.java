package com.isaachome.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name="book_authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @Column(name = "author_name",nullable = false)
    private String name;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "national")
    private String national;
    @Column(name = "country")
    private String country;
}
