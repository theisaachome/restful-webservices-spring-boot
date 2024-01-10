package com.isaachome.blog.service;

import com.isaachome.blog.dto.PostDTO;
import org.springframework.stereotype.Service;

public interface PostService {
    PostDTO getPostById(Long postId);
}
