package com.APIByIntellij.service;

import com.APIByIntellij.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);


    List<PostDto> getAllPosts(int pageNo, int pageSize,String sortBy);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
