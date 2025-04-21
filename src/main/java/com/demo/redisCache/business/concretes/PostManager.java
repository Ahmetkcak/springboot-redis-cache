package com.demo.redisCache.business.concretes;

import com.demo.redisCache.business.abstracts.PostService;
import com.demo.redisCache.dataAccess.PostRepository;
import com.demo.redisCache.entities.Post;
import com.demo.redisCache.entities.dtos.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostManager implements PostService {
    private final PostRepository postRepository;

    @Cacheable(value = "posts")
    @Override
    public List<Post> getAll() {
        try {
            Thread.sleep(5000); // Simulating a delay for cache demonstration
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        return postRepository.findAll();
    }

    @Cacheable(value = "posts", key = "#id")
    @Override
    public Post getById(int id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @CacheEvict(value = "posts", allEntries = true)
    @Override
    public Post add(Post post) {
        return postRepository.save(post);
    }

    @CachePut(value = "posts", key = "#post.id")
    @CacheEvict(value = "posts", allEntries = true)
    @Override
    public Post update(Post post) {
        return postRepository.save(post);
    }

    @CacheEvict(value = "posts", allEntries = true)
    @Override
    public void delete(int id) {
        postRepository.deleteById(id);
    }

    @Cacheable(value = "posts", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    @Override
    public PageDto<Post> getAllWithPagination(Pageable pageable) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        Page<Post> page = postRepository.findAll(pageable);
        PageDto<Post> pageDto = new PageDto<>();
        pageDto.setContent(page.getContent());
        pageDto.setTotalPages(page.getTotalPages());
        pageDto.setTotalElements(page.getTotalElements());
        return pageDto;
    }
}
