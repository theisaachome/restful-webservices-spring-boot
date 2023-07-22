package com.isaachome.blog.service;

import com.isaachome.blog.payload.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(long postId,CommentDTO dto);
    List<CommentDTO> getCommentsByPostId(long postId);
    CommentDTO getCommentById(long postId,long commentId);
    CommentDTO updateComment(long postId,long commentId,CommentDTO dto);
    void deleteComment(long postId, long commentId);
}
