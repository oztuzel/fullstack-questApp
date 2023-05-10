package com.monozel.questAppbackend.services;

import com.monozel.questAppbackend.entities.Like;
import com.monozel.questAppbackend.entities.Post;
import com.monozel.questAppbackend.entities.User;
import com.monozel.questAppbackend.repos.PostRepository;
import com.monozel.questAppbackend.requests.PostCreateRequest;
import com.monozel.questAppbackend.requests.PostUpdateRequest;
import com.monozel.questAppbackend.responses.LikeResponse;
import com.monozel.questAppbackend.responses.PostResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;

    private UserService userService;

    private LikeService likeService;

    public PostService (PostRepository postRepository, UserService userService ){
        this.postRepository= postRepository;
        this.userService= userService;

    }

    @Autowired
    @Lazy
    public void setLikeService (LikeService likeService) {
        this.likeService =likeService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        }else{
            list = postRepository.findAll();
        }
        return list.stream().map(post-> {
            List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(post.getId()));
            return new PostResponse(post, likes);
        }).collect(Collectors.toList());


    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUserById(newPostRequest.getUserId());
        if(user == null) {
            return null;
        }
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            return postRepository.save(toUpdate);
        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
