package com.isaachome.bookstore.controller;

import com.isaachome.bookstore.dto.BookDTO;
import com.isaachome.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // get A list of posts
    @GetMapping
    public List<BookDTO> getBooks(){
    return  List.of(null,null,null);
    }

    // get post by ID
    @GetMapping("{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("id")long  postId){
        var book = bookService.getBookById(postId);
        return  new ResponseEntity<>(book, HttpStatus.OK);
    }

    // create post
    // localhost:8080/api/v1/books
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO dto){
        var newPost = bookService.createBook(dto);
        return  new ResponseEntity<>(newPost,HttpStatus.CREATED);
    }


}
