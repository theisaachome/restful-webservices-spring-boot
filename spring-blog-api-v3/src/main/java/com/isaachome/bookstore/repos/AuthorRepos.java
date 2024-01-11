package com.isaachome.bookstore.repos;

import com.isaachome.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepos extends JpaRepository<Author,Long> {
}
