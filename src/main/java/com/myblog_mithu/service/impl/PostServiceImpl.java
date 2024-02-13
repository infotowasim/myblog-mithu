package com.myblog_mithu.service.impl;

import com.myblog_mithu.entity.Post;
import com.myblog_mithu.exceptions.ResourceNotFoundException;
import com.myblog_mithu.payload.PostDTO;
import com.myblog_mithu.repository.PostRepository;
import com.myblog_mithu.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    // Entity to DTO
    PostDTO mapToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setDescription(post.getDescription());
        return postDTO;
    }

    // DTO to Entity
    Post mapToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        return post;

    }


    @Override
    public PostDTO createPost(PostDTO postDTO) {

        // DTO to Entity
        Post post = mapToEntity(postDTO);
        Post savePost = postRepository.save(post);


//        Post post = new Post();
//        post.setTitle(postDTO.getTitle());
//        post.setDescription(postDTO.getDescription());
//        post.setContent(postDTO.getContent());
//
//        Post savePost = postRepository.save(post);

        // Post to DTO
        PostDTO dto = mapToDTO(savePost);


//        PostDTO dto = new PostDTO();
//        dto.setId(savePost.getId());
//        dto.setTitle(savePost.getTitle());
//        dto.setDescription(savePost.getDescription());
//        dto.setContent(savePost.getContent());

        return dto;
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id:" + id));


        // Post to DTO
        PostDTO postDTO = mapToDTO(post);

//        PostDTO postDTO = new PostDTO();
//        postDTO.setId(post.getId());
//        postDTO.setTitle(post.getTitle());
//        postDTO.setContent(post.getContent());
//        postDTO.setDescription(post.getDescription());

        return postDTO;
    }





//    @Override
//    public List<PostDTO> getAllPosts() {
//        List<Post> postList = postRepository.findAll();
////        List<PostDTO> postDTOList = postList.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
//        List<PostDTO> postDTOList = postList.stream().map(this::mapToDTO).collect(Collectors.toList());
//
//        return postDTOList;
//    }


//    // Pagination Concept
//    @Override
//    public List<PostDTO> getAllPosts(int pageNo, int pageSize) {
//
//        Pageable pageable = PageRequest.of(pageNo, pageSize);  // PageRequest- Pageable = USING FOR PAGINATION
//        Page<Post> postPage = postRepository.findAll(pageable);  // pageable- USING FOR SAVE PAGENO , PAGESIZE
//        List<Post> postList = postPage.getContent();  // getContent()- CONVERTING PAGE POST TO LISTPAGE
////    List<PostDTO> postDTOList = postList.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
//        List<PostDTO> postDTOList = postList.stream().map(this::mapToDTO).collect(Collectors.toList());
//        return postDTOList;
//    }




//    // Pagination Concept with Sorting
//    @Override
//    public List<PostDTO> getAllPosts(int pageNo, int pageSize, String sortBy) {
//        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        Page<Post> postPage = postRepository.findAll(pageable);
//        List<Post> postList = postPage.getContent();
////        postList.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
//        List<PostDTO> postDTOList = postList.stream().map(this::mapToDTO).toList();
//        return postDTOList;
//    }





    // Pagination Concept with Ascending or Descending
    @Override
    public List<PostDTO> geyAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        // Using the ternary operator to find the Ascending() and Descending() orders.
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post> postList = postPage.getContent();
        List<PostDTO> postDTOList = postList.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
//        List<PostDTO> postDTOList = postList.stream().map(this::mapToDTO).toList();
        return postDTOList;
    }







}
