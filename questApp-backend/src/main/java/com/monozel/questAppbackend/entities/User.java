package com.monozel.questAppbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="user")
@Data  // this lombok annotation create getter and setter automatically.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String userName;
    String password;
}
