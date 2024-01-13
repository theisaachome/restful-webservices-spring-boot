package com.isaachome.bookstore.service;

import com.isaachome.bookstore.dto.BookDTO;

public interface BookService {
    BookDTO getBookById(Long postId);
    BookDTO createBook(BookDTO dto);
}
