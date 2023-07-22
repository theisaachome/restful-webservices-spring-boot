package com.isaachome.blog.service;

import com.isaachome.blog.entity.Comment;
import com.isaachome.blog.entity.Post;
import com.isaachome.blog.exception.BlogAPIException;
import com.isaachome.blog.exception.ResourceNotFoundException;
import com.isaachome.blog.payload.CommentDTO;
import com.isaachome.blog.repos.CommentRepos;
import com.isaachome.blog.repos.PostRepos;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements  CommentService{
    private final CommentRepos repos;
    private  final PostRepos postRepos;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepos repos,PostRepos postRepos,ModelMapper mapper) {
        this.repos = repos;
        this.postRepos = postRepos;
        this.mapper=mapper;
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
        Post post = postRepos.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","ID",postId));
        Comment comment = repos.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","ID",commentId));
        if(!post.getId().equals(comment.getPost().getId())){
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to POST");
        }
        return mapToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(long postId, long commentId, CommentDTO dto) {
        // retrieve post entity by id
        Post post = postRepos.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = repos.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setContent(dto.getContent());
        // save to database

        return mapToDTO(repos.save(comment));
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        // retrieve post entity by id
        Post post = postRepos.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = repos.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }
      repos.deleteById(commentId);
    }


    private CommentDTO mapToDTO(Comment entity){
//        return  new CommentDTO(entity.getId(),entity.getName(),entity.getEmail(), entity.getContent());
        return  mapper.map(entity,CommentDTO.class);
    }

    private  Comment mapToEntity(CommentDTO dto){
         var comment= mapper.map(dto,Comment.class);
//        Comment comment = new Comment();
//        comment.setId(dto.id());
//        comment.setName(dto.name());
//        comment.setEmail(dto.email());
//        comment.setContent(dto.content());
        return  comment;
    }
}
