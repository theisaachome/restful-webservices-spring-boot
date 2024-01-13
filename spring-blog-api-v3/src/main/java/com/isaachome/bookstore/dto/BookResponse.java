package com.isaachome.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookResponse {
    private List<BookDTO> contents;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private  int totalPage;
    private  boolean isLast;
}
