package com.isaachome.blog.controller;

import com.isaachome.blog.payload.PostDTO;
import com.isaachome.blog.payload.PostResponse;
import com.isaachome.blog.service.PostService;
import com.isaachome.blog.util.AppConstants;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    // get all posts
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false)String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }
    // create blog post rest api
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        return  new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name="id")long id){
      return   ResponseEntity.ok(postService.getById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable(name="id")long id){
        PostDTO responsePost = postService.updatePost(postDTO,id);
        return  new ResponseEntity<>(responsePost,HttpStatus.OK);
    }
    // delete post rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePost(@PathVariable(name="id") long id){
        postService.deletePost(id);
        return  new ResponseEntity<>("Post entity deleted successfully.",HttpStatus.OK);
    }
}
