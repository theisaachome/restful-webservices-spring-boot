package com.isaachome.bookstore.repos;

import com.isaachome.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepos extends JpaRepository<Book,Long> {
}
