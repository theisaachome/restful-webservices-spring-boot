package com.isaachome.bookstore.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @Column(nullable = false)
    private  String uuid;
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

    @ManyToMany
    @JoinTable(
            name = "author_books",
    joinColumns = @JoinColumn(name = "author_fk"),
    inverseJoinColumns = @JoinColumn(name = "book_fk"))
    private List<Book> authorBooks;
}
