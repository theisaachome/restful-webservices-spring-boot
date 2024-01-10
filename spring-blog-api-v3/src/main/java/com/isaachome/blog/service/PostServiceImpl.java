package com.isaachome.blog.service;

import com.isaachome.blog.dto.PostDTO;
import com.isaachome.blog.repos.PostRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final PostRepos postRepos;

    @Override
    public PostDTO getPostById(Long postId) {
        postRepos.findById(postId);
        return null;
    }
}
