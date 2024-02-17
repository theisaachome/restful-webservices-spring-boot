package com.isaachome.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
@SecondaryTable(name = "t_country")
@SecondaryTable(name = "t_city")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String street1;
    private  String street2;
    @Column(table = "t_city")
    private String city;
    @Column(table = "t_city")
    private String state;
    @Column(table = "t_city")
    private  String zipcode;
    @Column(table = "t_country")
    private  String country;
    @OneToOne(mappedBy = "address")
    private Customer customer;
}
