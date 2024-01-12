package com.isaachome.bookstore.controller;

import com.isaachome.bookstore.dto.AuthorDTO;
import com.isaachome.bookstore.dto.AuthorResponse;
import com.isaachome.bookstore.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // create new author
    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO dto){
        var newAuthor = authorService.createAuthor(dto);
     return  new ResponseEntity<>(newAuthor, HttpStatus.CREATED);
    }
    // get all Authors
    // get All with paging
    // localhost:8080/api/v1/authors? pageSize=5&pageNum=1
//    @GetMapping
//    public List<AuthorDTO> getAllAuthor(){
//        return  authorService.getAllAuthor();
//    }
    @GetMapping
    public AuthorResponse getAllAuthor(
                                        @RequestParam(value = "pageNo" ,defaultValue = "0",required = false) int pageNo,
                                        @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                                        @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy){
//        return  authorService.getAllAuthor(pageNo,pageSize);
        return  authorService.getAllAuthor(pageNo,pageSize,sortBy);
    }
    // get author by id
    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") long author_id){
        return  new ResponseEntity<>(authorService.getAuthorById(author_id),HttpStatus.OK);
    }
    // update author
    @PutMapping("{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable("id")long author_id,
                                               @RequestBody AuthorDTO dto){

        return new ResponseEntity<>(authorService.updateAuthor(author_id,dto),HttpStatus.OK);
    }
    // delete author
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id")long author_id){
        authorService.deleteAuthor(author_id);
        return  new ResponseEntity<>("Resource deleted successfully",HttpStatus.ACCEPTED);
    }
}
