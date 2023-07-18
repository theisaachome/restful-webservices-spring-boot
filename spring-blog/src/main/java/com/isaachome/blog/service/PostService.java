package com.isaachome.blog.service;

import com.isaachome.blog.payload.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO data);
    List<PostDTO> getAllPosts();
    PostDTO getById(long id);
    PostDTO updatePost(PostDTO dto,long id);
    void deletePost(long id);
}
