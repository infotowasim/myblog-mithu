package com.myblog_mithu.service.impl;

import com.myblog_mithu.entity.Post;
import com.myblog_mithu.payload.PostDTO;
import com.myblog_mithu.repository.PostRepository;
import com.myblog_mithu.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post savePost = postRepository.save(post);

        PostDTO dto = new PostDTO();
        dto.setId(savePost.getId());
        dto.setTitle(savePost.getTitle());
        dto.setDescription(savePost.getDescription());
        dto.setContent(savePost.getContent());

        return dto;
    }
}
