package com.myblog_mithu.service.impl;

import com.myblog_mithu.entity.Comment;
import com.myblog_mithu.entity.Post;
import com.myblog_mithu.exceptions.ResourceNotFoundException;
import com.myblog_mithu.payload.CommentDTO;
import com.myblog_mithu.repository.CommentRepository;
import com.myblog_mithu.repository.PostRepository;
import com.myblog_mithu.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;


    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    // CommentDTO To Comment (Entity)
    public Comment mapToEntity(CommentDTO commentDTO) {

//        Comment comment = new Comment();
//        comment.setText(commentDTO.getText());
//        comment.setEmail(commentDTO.getEmail());
//        return comment;

        // using Model Mapper.
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        return comment;

    }


    // Comment (Entity) To CommentDTO
    public CommentDTO mapToDTO(Comment comment) {

//        CommentDTO commentDTO = new CommentDTO();
//        commentDTO.setId(comment.getId());
//        commentDTO.setText(comment.getText());
//        commentDTO.setEmail(comment.getEmail());
//        return commentDTO;

        // MODEL MAPPER CONCEPTS
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        return commentDTO;
    }


    @Override
    public CommentDTO createComments(CommentDTO commentDTO, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("POST NOT FOUND WITH POST ID:" + postId));

        // CommentDTO To Comment (Entity)
        Comment comment = mapToEntity(commentDTO);
        comment.setPost(post);
        Comment saveComment = commentRepository.save(comment);

        // Comment (Entity) To CommentDTO
        CommentDTO commentDTO1 = mapToDTO(saveComment);
        return commentDTO1;
    }

    @Override
    public String deleteComments(long commentId) {
        commentRepository.deleteById(commentId);
        return "Deleted is Successfully";
    }




    @Override
    public CommentDTO updateComment(long commentId, long postId, CommentDTO commentDTO) {

        // Find the post
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("POST NOT FOUND WITH ID:" + postId));

        // Find the comment
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("COMMENT NOT FOUND WITH COMMENTID: " + commentId));

        Comment comment1 = mapToEntity(commentDTO);

        // Note: If you want the comment ID to rema
        // DTO TO ENTITY in constant, don't set it explicitly.
        // If you want it to change (if allowed by the database), you can omit this line.
        // comment1.setId(comment.getId());

        // SET AND GET COMMENT ID
        comment1.setId(comment.getId());

        // SET POST ID
        comment1.setPost(post);

        // Save the updated comment using commentRepository
        Comment updateComment = commentRepository.save(comment1);

        // ENTITY TO DTO
        CommentDTO commentDTO1 = mapToDTO(updateComment);

        return commentDTO1;
    }




}
