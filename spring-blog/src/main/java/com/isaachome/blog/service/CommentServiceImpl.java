package com.isaachome.blog.service;

import com.isaachome.blog.entity.Comment;
import com.isaachome.blog.entity.Post;
import com.isaachome.blog.exception.ResourceNotFoundException;
import com.isaachome.blog.payload.CommentDTO;
import com.isaachome.blog.repos.CommentRepos;
import com.isaachome.blog.repos.PostRepos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements  CommentService{
    private final CommentRepos repos;
    private  final PostRepos postRepos;

    public CommentServiceImpl(CommentRepos repos,PostRepos postRepos) {
        this.repos = repos;
        this.postRepos = postRepos;
    }

    @Override
    public CommentDTO createComment(long postId,CommentDTO dto) {
        Comment comment = mapToEntity(dto);
        Post post = postRepos.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","ID",postId));
        comment.setPost(post);
        var newComment = repos.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(long postId) {
        return repos.findByPostId(postId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(long postId, long commentId) {
        return null;
    }

    @Override
    public CommentDTO updateComment(long postId, long commentId, CommentDTO dto) {
        return null;
    }

    @Override
    public void deleteComment(long postId, long commentId) {

    }

    private CommentDTO mapToDTO(Comment entity){
        return  new CommentDTO(entity.getId(),entity.getName(),entity.getEmail(), entity.getContent());
    }

    private  Comment mapToEntity(CommentDTO dto){
        Comment comment = new Comment();
        comment.setId(dto.id());
        comment.setName(dto.name());
        comment.setEmail(dto.email());
        comment.setContent(dto.content());
        return  comment;
    }
}
