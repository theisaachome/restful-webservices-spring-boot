package com.isaachome.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false,length = 2000)
    private String description;
    @Column(name = "unitPrice",nullable = false)
    private double unitPrice;
    @Column(name = "in_stock_number",nullable = false)
    private  int inStockNumber;
    @Column(name = "publisher",nullable = false)
    private String publisher;
    @Column(name = "pages",nullable = false)
    private int pages;
    @Column(name = "edition",nullable = false)
    private int edition;
    @Column(name = "tags",nullable = false)
    private String tags;

    @OneToMany(mappedBy = "book")
    private List<Author> authors = new ArrayList<>();
}
