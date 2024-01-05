package com.myblog_mithu.controller;

import com.myblog_mithu.payload.PostDTO;
import com.myblog_mithu.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        PostDTO savePostDTO = postService.createPost(postDTO);
        return  new ResponseEntity<>(savePostDTO, HttpStatus.CREATED);
    }
}
