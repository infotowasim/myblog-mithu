package com.myblog_mithu.service;

import com.myblog_mithu.payload.CommentDTO;

public interface CommentService {
    CommentDTO createComments(CommentDTO commentDTO, long postId);

    String deleteComments(long commentId);

    CommentDTO updateComment(long commentId, long postId, CommentDTO commentDTO);

}
