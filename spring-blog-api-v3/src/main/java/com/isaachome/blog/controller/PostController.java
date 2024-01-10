package com.isaachome.blog.controller;

import com.isaachome.blog.dto.PostDTO;
import com.isaachome.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // get A list of posts
    @GetMapping
    public List<PostDTO> getPosts(){
    return  List.of(new PostDTO("","",""));
    }

    // get post by ID
    @GetMapping("{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("id")long  postId){
        var post = postService.getPostById(postId);
        return  new ResponseEntity<>(post, HttpStatus.OK);
    }
}
