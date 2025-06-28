package org.spring.springjpa.securityspring.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.spring.springjpa.securityspring.dto.PostDTO;
import org.spring.springjpa.securityspring.entity.PostEntity;
import org.spring.springjpa.securityspring.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.spring.springjpa.securityspring.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .toList();
    }
    public PostDTO getPostById(Long id) {
        return postRepository.findById(id)
                .map(post -> modelMapper.map(post, PostDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    }

    public PostDTO createPost(PostDTO postDTO) {
        // Assuming there's a method in PostRepository to save the post
        PostEntity post = modelMapper.map(postDTO, PostEntity.class);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDTO.class);
    }
}
