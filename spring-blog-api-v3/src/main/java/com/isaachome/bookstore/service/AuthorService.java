package com.isaachome.bookstore.service;

import com.isaachome.bookstore.dto.AuthorDTO;
import com.isaachome.bookstore.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    // create New Author
    AuthorDTO createAuthor(AuthorDTO dto);
    List<AuthorDTO>  getAllAuthor();
    AuthorDTO getAuthorById(long id);
    AuthorDTO updateAuthor(long author_id,AuthorDTO dto);
    void deleteAuthor(long author_id);
}
