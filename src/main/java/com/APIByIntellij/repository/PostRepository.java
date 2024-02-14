package com.APIByIntellij.repository;

import com.APIByIntellij.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
