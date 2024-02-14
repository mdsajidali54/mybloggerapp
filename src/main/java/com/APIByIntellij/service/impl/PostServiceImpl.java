package com.APIByIntellij.service.impl;

import com.APIByIntellij.entity.Post;
import com.APIByIntellij.exception.ResourceNotFoundException;
import com.APIByIntellij.payload.PostDto;
import com.APIByIntellij.repository.PostRepository;
import com.APIByIntellij.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

      Post post =  mapToEntity(postDto);
        Post newPost = postRepository.save(post);

      PostDto newPostDto=   mapToDto(newPost);
        return newPostDto;
    }


    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize,String sortBy) {

        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> content = posts.getContent();


        return   content.stream().map(post->mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
      return   mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)

        );

        postRepository.delete(post);

    }


    Post mapToEntity(PostDto postDto){

        Post post= new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

   PostDto mapToDto(Post post){
        PostDto postDto=new PostDto();

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;


    }


}
