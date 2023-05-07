package com.monozel.questAppbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false) // foreign key (connection tables)
    @OnDelete(action = OnDeleteAction.CASCADE) // all posts delete when user was deleted
    User user;
    String title;
    @Lob
    @Column(columnDefinition = "text")
    String text;
}
