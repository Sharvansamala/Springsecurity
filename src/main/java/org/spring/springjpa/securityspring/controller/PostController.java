package org.spring.springjpa.securityspring.controller;

import lombok.RequiredArgsConstructor;
import org.spring.springjpa.securityspring.dto.PostDTO;
import org.spring.springjpa.securityspring.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        // Assuming there's a method in PostService to handle creation
        return postService.createPost(postDTO);
    }
}
