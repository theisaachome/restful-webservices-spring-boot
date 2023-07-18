package com.isaachome.blog.controller;

import com.isaachome.blog.payload.PostDTO;
import com.isaachome.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    // get all posts
    @GetMapping
    public List<PostDTO> getAllPosts(){
        System.out.println("Hello");
        return postService.getAllPosts();
    }
    // create blog post rest api
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        return  new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }
}
