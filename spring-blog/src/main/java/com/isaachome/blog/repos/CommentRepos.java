package com.isaachome.blog.repos;

import com.isaachome.blog.entity.Comment;
import com.isaachome.blog.payload.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepos extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(long id);
}
