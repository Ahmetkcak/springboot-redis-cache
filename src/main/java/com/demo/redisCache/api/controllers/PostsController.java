package com.demo.redisCache.api.controllers;

import com.demo.redisCache.business.abstracts.PostService;
import com.demo.redisCache.entities.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {
    private final PostService postService;

    @PostMapping("/add")
    public Post add(@RequestBody Post post) {
        return postService.add(post);
    }

    @PutMapping("/update")
    public Post update(@RequestBody Post post) {
        return postService.update(post);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        postService.delete(id);
    }

    @GetMapping("/getAll")
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Post getById(@PathVariable int id) {
        return postService.getById(id);
    }
}
