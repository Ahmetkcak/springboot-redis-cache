package com.demo.redisCache.dataAccess;

import com.demo.redisCache.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
