package com.isaachome.bookstore.service;

import com.isaachome.bookstore.dto.PostDTO;

public interface PostService {
    PostDTO getPostById(Long postId);
    PostDTO createPost(PostDTO dto);
}
