package com.myblog_mithu.controller;


import com.myblog_mithu.payload.PostDTO;
import com.myblog_mithu.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    // Only Admin can create post , User cant create post it means.
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping 
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        PostDTO savePostDTO = postService.createPost(postDTO);
        return new ResponseEntity<>(savePostDTO, HttpStatus.CREATED);
    }


    // http://localhost:8080/api/posts/particular?id=1
    @GetMapping("/particular")
    public ResponseEntity<PostDTO> getPostById(@RequestParam("id") long id) {
        PostDTO postById = postService.getPostById(id);
        return new ResponseEntity<>(postById, HttpStatus.OK);
    }

//    @GetMapping
//    public List<PostDTO> getAllPosts(){
//        List<PostDTO> postDTOList = postService.getAllPosts();
//        return postDTOList;
//    }



//    // Pagination Concept
//    // http://localhost:8080/api/posts?pageno=0&pageSize=3
//    @GetMapping
//    public List<PostDTO> getAllPosts(
//            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
//            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize
//    ) {
//        List<PostDTO> postDTOList = postService.getAllPosts(pageNo, pageSize);
//        return postDTOList;
//    }




//    // Pagination Concept with Sorting
//    // http://localhost:8080/api/posts?pageNo=0&pageSize&sortBy=id
//    // http://localhost:8080/api/posts?pageNo=0&pageSize&sortBy=title
//    // http://localhost:8080/api/posts?pageNo=0&pageSize&sortBy=description
//    // http://localhost:8080/api/posts?pageNo=0&pageSize&sortBy=content
//    @GetMapping
//    public List<PostDTO> getAllPosts(
//            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
//            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize,
//            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy
//    ){
//        List<PostDTO> postDTOList = postService.getAllPosts(pageNo, pageSize, sortBy);
//        return postDTOList;
//    }






    // Pagination Concept with Ascending or Descending
    // http://localhost:8080/api/posts?pageNo=0&pageSize&sortBy=id&sortDir=asc
    // http://localhost:8080/api/posts?pageNo=0&pageSize&sortBy=id&sortDir=desc
    // http://localhost:8080/api/posts?pageNo=0&pageSize&sortBy=title&sortDir=asc
    // http://localhost:8080/api/posts?pageNo=0&pageSize&sortBy=title&sortDir=desc
    // http://localhost:8080/api/posts?pageNo=0&pageSize&sortBy=description&sortDir=asc
    // http://localhost:8080/api/posts?pageNo=0&pageSize&sortBy=description&sortDir=desc
    // http://localhost:8080/api/posts?pageNo=0&pageSize&sortBy=content&sortDir=asc
    // http://localhost:8080/api/posts?pageNo=0&pageSize&sortBy=content&sortDir=desc
    @GetMapping
    public List<PostDTO> getAllPosts(
            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(name = "pagesize", required = false, defaultValue = "3") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "id") String sortDir
    ){
      List<PostDTO> postDTOList =  postService.geyAllPosts(pageNo,pageSize,sortBy,sortDir);
      return postDTOList;
    }

}