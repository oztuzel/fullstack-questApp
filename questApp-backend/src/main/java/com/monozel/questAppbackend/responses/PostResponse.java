package com.monozel.questAppbackend.responses;

import com.monozel.questAppbackend.entities.Like;
import com.monozel.questAppbackend.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    Long id;
    Long userId;
    String username;
    String text;
    String title;
    List<LikeResponse> postLikes;

    public PostResponse(Post entity, List<LikeResponse> likes) {
        this.id = entity.getId();
        this.userId= entity.getUser().getId();
        this.username = entity.getUser().getUserName();
        this.title = entity.getText();
        this.text = entity.getTitle();
        this.postLikes = likes;
    }
}
