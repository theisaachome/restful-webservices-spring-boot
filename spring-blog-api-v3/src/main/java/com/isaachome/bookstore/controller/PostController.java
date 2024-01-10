package com.isaachome.bookstore.controller;

import com.isaachome.bookstore.dto.PostDTO;
import com.isaachome.bookstore.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // get A list of posts
    @GetMapping
    public List<PostDTO> getPosts(){
    return  List.of(new PostDTO(1,"","",""));
    }

    // get post by ID
    @GetMapping("{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("id")long  postId){
        var post = postService.getPostById(postId);
        return  new ResponseEntity<>(post, HttpStatus.OK);
    }

    // create post
    // localhost:8080/api/v3/posts
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO dto){
        var newPost = postService.createPost(dto);
        return  new ResponseEntity<>(newPost,HttpStatus.CREATED);
    }


}
