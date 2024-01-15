package com.isaachome.bookstore.controller;

import com.isaachome.bookstore.dto.BookDTO;
import com.isaachome.bookstore.dto.BookResponse;
import com.isaachome.bookstore.service.BookService;
import com.isaachome.bookstore.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // get A list of posts
    @GetMapping
    public ResponseEntity<BookResponse> getBooks(@RequestParam(name="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
                                                 @RequestParam(name = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                                 @RequestParam(name = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
                                                 @RequestParam(name = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false)String sortDir){
      var bookResponse=  bookService.getAllBook(pageNo,pageSize,sortBy,sortDir);
    return  new ResponseEntity<>(bookResponse,HttpStatus.OK);
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

    // delete book by Id
    // localhost:8080/api/v1/books
    @DeleteMapping("id")
    public  ResponseEntity<String> deleteByID(@PathVariable("id") long book_id){
        bookService.deleteById(book_id);
      return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }


}
