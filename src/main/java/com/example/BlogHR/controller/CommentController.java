package com.example.BlogHR.controller;


import com.example.BlogHR.payload.CommentDto;
import com.example.BlogHR.response.ApiResponse;
import com.example.BlogHR.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //CREATE new comment
    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createNewPost(@Valid @RequestBody CommentDto commentDto, @PathVariable Long postId) {
        CommentDto comment = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    //    DELETE comment by commentId
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment with id " + commentId + " has been deleted", true), HttpStatus.OK);
    }
}
