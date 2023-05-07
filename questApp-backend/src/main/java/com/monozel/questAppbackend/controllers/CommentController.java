package com.monozel.questAppbackend.controllers;

import com.monozel.questAppbackend.entities.Comment;
import com.monozel.questAppbackend.requests.CommentCreateRequest;
import com.monozel.questAppbackend.requests.CommentUpdateRequest;
import com.monozel.questAppbackend.services.CommentService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@CrossOrigin("http://localhost:3000")
public class CommentController {
    private CommentService commentService;

    public CommentController ( CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments (@RequestParam Optional<Long> userId,
                                         @RequestParam Optional<Long> postId) {
        return commentService.getAllCommentsWithParam(userId,postId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment (@PathVariable Long commentId) {
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest request) {
        return commentService.createOneComment(request);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment (@PathVariable Long commentId,
                                     @RequestBody CommentUpdateRequest request) {
        return commentService.updateOneCommentById(commentId,request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment (@PathVariable Long commentId) {
        commentService.deleteOneCommentById(commentId);
    }
}
