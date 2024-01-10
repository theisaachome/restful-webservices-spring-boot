package com.isaachome.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    // create new author
    @PostMapping
    public ResponseEntity<String> createAuthor(){
     return  new ResponseEntity<>("Created", HttpStatus.CREATED);
    }
    // get all Authors
    @GetMapping
    public List<String> getAllAuthor(){
        return  List.of("All","Author");
    }
    // get author by id
    @GetMapping("{id}")
    public ResponseEntity<String> getAuthor(@PathVariable("id") long author_id){
        return  new ResponseEntity<>("",HttpStatus.OK);
    }
    // update author
    @PutMapping
    public ResponseEntity<String> updateAuthor(@PathVariable("id")long author_id,
                                               @RequestBody String dto){
        return new ResponseEntity<>("Update",HttpStatus.OK);
    }
    // delete author
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id")long author_id){
        return  new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);
    }
}
