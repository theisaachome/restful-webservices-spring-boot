package com.isaachome.bookstore.service;

import com.isaachome.bookstore.dto.AuthorDTO;
import com.isaachome.bookstore.dto.AuthorResponse;
import com.isaachome.bookstore.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    // create New Author
    AuthorDTO createAuthor(AuthorDTO dto);
//    List<AuthorDTO>  getAllAuthor(int pageNo,int pageSize);
   AuthorResponse getAllAuthor(int pageNo, int pageSize,String sortBy,String sortDir);
    AuthorDTO getAuthorById(long id);
    AuthorDTO updateAuthor(long author_id,AuthorDTO dto);
    void deleteAuthor(long author_id);
}
