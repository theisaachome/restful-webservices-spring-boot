package com.isaachome.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private long id;
    private String title;
    private String description;
    private double unitPrice;
    private  int inStockNumber;
    private String publisher;
    private int pages;
    private int edition;
    private String tags;


}