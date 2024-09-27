package com.demo.redisCache.business.abstracts;

import com.demo.redisCache.entities.Post;
import com.demo.redisCache.entities.dtos.PageDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    List<Post> getAll();
    Post getById(int id);
    Post add(Post post);
    Post update(Post post);
    void delete(int id);

    PageDto<Post> getAllWithPagination(Pageable pageable);
}
