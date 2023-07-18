package com.isaachome.blog.service;

import com.isaachome.blog.entity.Post;
import com.isaachome.blog.exception.ResourceNotFoundException;
import com.isaachome.blog.payload.PostDTO;
import com.isaachome.blog.repos.PostRepos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements   PostService{
    private final PostRepos repos;

    public PostServiceImpl(PostRepos repos) {
        this.repos = repos;
    }

    @Override
    public PostDTO createPost(PostDTO data) {
        Post post = mapToPost(data);
        Post newPost=repos.save(post);
        return mapToDTO(newPost);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return repos.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public PostDTO getById(long id) {
        Post post = repos.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","ID",id));
        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO dto, long id) {
        Post post = repos.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        post.setTitle(dto.title());
        post.setDescription(dto.description());
        post.setContent(dto.content());
        Post updatedPost=repos.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePost(long id) {

    }
    // convert Entity into DTO
    private  PostDTO mapToDTO(Post post){
        PostDTO postDTO  = new PostDTO(post.getId(),post.getTitle(),post.getDescription(),post.getContent());
        return  postDTO;
    }
    // convert DTO into DTO
    private  Post mapToPost(PostDTO dto){
        Post post = new Post();
        post.setId(dto.id());
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setDescription(dto.description());
        return  post;
    }
}
