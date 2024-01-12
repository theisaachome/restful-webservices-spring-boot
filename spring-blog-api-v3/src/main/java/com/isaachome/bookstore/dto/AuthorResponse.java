package com.isaachome.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AuthorResponse {
    private List<AuthorDTO> contents;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private  int totalPage;
    private  boolean isLast;

}
