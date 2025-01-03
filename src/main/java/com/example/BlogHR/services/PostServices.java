package com.example.BlogHR.services;


import com.example.BlogHR.payload.PostDto;
import com.example.BlogHR.payload.PostResponse;

import java.util.List;

public interface PostServices {
    //    Create Post
    PostDto createPost(PostDto postDto, Long userId, Long categoryId);

    //    Update Post
    PostDto updatePost(PostDto post, Long postId);

    //    Delete Post
    void deletePost(Long postId);

    //    Get postById
    PostDto getPostById(Long postId);

    //    Get All Posts
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sort, String order);

    //   Get All Post by category
    List<PostDto> getAllPostByCategoryId(Long categoryId);

    //   Get All Post by user
    List<PostDto> getAllPostByUserId(Long userId);
    //   Get All Post by keyword
    List<PostDto> getPostByTitle(String kyeword);


    List<PostDto> getTopPosts();
}
