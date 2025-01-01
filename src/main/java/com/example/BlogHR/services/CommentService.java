package com.example.BlogHR.services;

import com.example.BlogHR.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long postId );
    void deleteComment(Long commentId );
}
