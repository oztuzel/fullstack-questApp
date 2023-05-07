package com.monozel.questAppbackend.controllers;

import com.monozel.questAppbackend.entities.Post;
import com.monozel.questAppbackend.requests.PostCreateRequest;
import com.monozel.questAppbackend.requests.PostUpdateRequest;
import com.monozel.questAppbackend.responses.PostResponse;
import com.monozel.questAppbackend.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin("http://localhost:3000")
public class PostController {
    private PostService postService;

    public PostController (PostService postService) {
        this.postService = postService;
    }

    // /posts
    // /posts?userId={userId}   paths.
    // only return postsof the users  if ?userId={userId} is present
    // return all posts if ?userId={userId} is not present
    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    //  path == /posts/{postId}
    @GetMapping("/{postId}")
    public Post getOnePost (@PathVariable Long postId) {
        return postService.getOnePostById(postId);
    }

    @PostMapping
    public Post createOnePost (@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost (@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost) {
        return postService.updateOnePostById(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost (@PathVariable Long postId) {
        postService.deleteOnePostById(postId);
    }
}
