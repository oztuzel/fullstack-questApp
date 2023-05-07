package com.monozel.questAppbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false) // foreign key (connection tables)
    @OnDelete(action = OnDeleteAction.CASCADE) // all comments delete when post was deleted
    @JsonIgnore
    Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false) // foreign key (connection tables)
    @OnDelete(action = OnDeleteAction.CASCADE) // all comments delete when user was deleted
    @JsonIgnore
    User user;

    @Lob
    @Column(columnDefinition = "text")
    String text;
}
