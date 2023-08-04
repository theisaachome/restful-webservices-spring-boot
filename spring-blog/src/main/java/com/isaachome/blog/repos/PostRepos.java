package com.isaachome.blog.repos;

import com.isaachome.blog.entity.Post;
import com.isaachome.blog.payload.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepos extends JpaRepository<Post,Long> {
    List<Post> findPostsByCategoryId(Long categoryId);
}
