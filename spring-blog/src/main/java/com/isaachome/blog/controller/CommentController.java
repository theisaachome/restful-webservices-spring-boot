package com.isaachome.blog.controller;

import com.isaachome.blog.entity.Comment;
import com.isaachome.blog.payload.CommentDTO;
import com.isaachome.blog.service.CommentService;
import jakarta.validation.Valid;
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
    public ResponseEntity<CommentDTO> createComment(@PathVariable("postId")long postId,@Valid @RequestBody CommentDTO commentDTO){
        return  new ResponseEntity<>(commentService.createComment(postId,commentDTO), HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable("postId")long postId){
        return commentService.getCommentsByPostId(postId);

    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("commentId")long commentId,@PathVariable("postId")long postId){
        return  new ResponseEntity<>(commentService.getCommentById(postId,commentId),HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("postId")long postId,@PathVariable("id")long id,@Valid @RequestBody CommentDTO commentDTO){
        return  new ResponseEntity<>(commentService.updateComment(postId,id,commentDTO),HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId,@PathVariable("id")long id){
        commentService.deleteComment(postId,id);
        return  new ResponseEntity("Comment Successfully deleted",HttpStatus.OK);
    }
}
