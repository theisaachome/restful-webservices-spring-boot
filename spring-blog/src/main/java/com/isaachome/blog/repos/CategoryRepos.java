package com.isaachome.blog.repos;

import com.isaachome.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepos extends JpaRepository<Category,Long> {
}
