package com.isaachome.bookstore.service;

import com.isaachome.bookstore.dto.BookDTO;
import com.isaachome.bookstore.dto.BookResponse;

public interface BookService {
    BookDTO getBookById(Long bookId);
    BookDTO createBook(BookDTO dto);
    BookResponse getAllBook(int pageNo, int pageSize,String sortBy,String sortDir);
    BookDTO updateBook(Long bookId,BookDTO dto);
}
