package com.isaachome.blog.service;

import com.isaachome.blog.payload.PostDTO;
import com.isaachome.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO data);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDTO getById(long id);
    PostDTO updatePost(PostDTO dto,long id);
    void deletePost(long id);
}
