package com.isaachome.blog.controller;

import com.isaachome.blog.entity.Comment;
import com.isaachome.blog.payload.CommentDTO;
import com.isaachome.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable("postId")long postId, @RequestBody CommentDTO commentDTO){
        return  new ResponseEntity<>(commentService.createComment(postId,commentDTO), HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable("postId")long postId){
        return commentService.getCommentsByPostId(postId);

    }
    @GetMapping("/posts/{postId}/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("commentId")long commentId,@PathVariable("postId")long postId){
        return  new ResponseEntity<>(commentService.getCommentById(postId,commentId),HttpStatus.OK);
    }
}
