package com.isaachome.blog.service;

import com.isaachome.blog.entity.Post;
import com.isaachome.blog.exception.ResourceNotFoundException;
import com.isaachome.blog.payload.PostDTO;
import com.isaachome.blog.payload.PostResponse;
import com.isaachome.blog.repos.PostRepos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResponse  getAllPosts(int pageNo,int pageSize, String sortBy, String sortDir) {
        Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> posts = repos.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
       List<PostDTO> contents= listOfPosts.stream().map(this::mapToDTO).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse(
                contents,
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.isLast()
        );
        return  postResponse;

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
      Post post=  repos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
      repos.delete(post);
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
