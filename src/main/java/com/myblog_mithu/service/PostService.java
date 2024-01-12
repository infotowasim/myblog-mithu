package com.myblog_mithu.service;

import com.myblog_mithu.payload.PostDTO;

public interface PostService {
    public PostDTO createPost(PostDTO postDTO);


    PostDTO getPostById(long id);
}
