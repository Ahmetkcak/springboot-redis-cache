package com.demo.redisCache.business.concretes;

import com.demo.redisCache.business.abstracts.PostService;
import com.demo.redisCache.dataAccess.PostRepository;
import com.demo.redisCache.entities.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @CacheEvict(value = "posts", allEntries = true)
    //@CachePut(value = "posts", key = "#post.id") is not used here because it only updates the cache for the specific post but does not refresh the cache for the entire list of posts.
    @Override
    public Post update(Post post) {
        return postRepository.save(post);
    }

    @CacheEvict(value = "posts", allEntries = true)
    @Override
    public void delete(int id) {
        postRepository.deleteById(id);
    }
}
