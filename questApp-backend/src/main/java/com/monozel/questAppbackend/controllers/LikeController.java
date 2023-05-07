package com.monozel.questAppbackend.controllers;

import com.monozel.questAppbackend.entities.Like;
import com.monozel.questAppbackend.requests.LikeCreateRequest;
import com.monozel.questAppbackend.responses.LikeResponse;
import com.monozel.questAppbackend.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
@CrossOrigin("http://localhost:3000")
public class LikeController {
    private LikeService likeService;

    public LikeController (LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId,
                                          @RequestParam Optional<Long> postId) {
        return likeService.getAllLikesWithParam(userId,postId);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike (@PathVariable Long likeId) {
        return likeService.getOneLikeById(likeId);
    }

    @PostMapping
    public Like createOneLike (@RequestBody LikeCreateRequest request) {
        return likeService.createOneLike(request);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike (@PathVariable Long likeId) {
        likeService.deleteOneLikeById(likeId);
    }

}
