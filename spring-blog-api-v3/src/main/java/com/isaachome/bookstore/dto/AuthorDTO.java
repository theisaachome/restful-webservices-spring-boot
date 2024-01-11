package com.isaachome.bookstore.dto;
import lombok.Data;
@Data
public class AuthorDTO {
    private long id;
    private  String uuid;
    private String name;
    private String firstName;
    private String lastName;
    private String address;
    private String national;
    private String country;
}
