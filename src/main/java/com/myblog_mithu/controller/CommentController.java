package com.myblog_mithu.controller;

import com.myblog_mithu.payload.CommentDTO;
import com.myblog_mithu.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    // http://localhost:8080/api/comments?postid=1
    @RequestMapping
    public ResponseEntity<CommentDTO> createComments(@RequestBody CommentDTO commentDTO, @RequestParam("postId") long postId){
        CommentDTO commentDTO1 = commentService.createComments(commentDTO, postId);
        return new ResponseEntity<>(commentDTO1, HttpStatus.CREATED);
    }



    // http://localhost:8080/api/comments/3
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") long commentId){
        commentService.deleteComments(commentId);
        return ResponseEntity.ok("DELETED IS SUCCESSFULLY");
    }



    // http://localhost:8080/api/comments/3
    @PutMapping("/{commentId}/post/{postId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("commentId") long commentId, @RequestBody CommentDTO commentDTO, @PathVariable("postId") long postId){
        CommentDTO commentDTO1 = commentService.updateComment(commentId,postId,commentDTO);
        return ResponseEntity.ok(commentDTO1);
    }



}
