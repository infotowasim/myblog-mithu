package com.myblog_mithu.service;

import com.myblog_mithu.payload.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    PostDTO getPostById(long id);

//    List<PostDTO> getAllPosts();
//    List<PostDTO> getAllPosts(int pageNo, int pageSize);
//    List<PostDTO> getAllPosts(int pageNo, int pageSize, String sortBy);
    List<PostDTO> geyAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);



}
