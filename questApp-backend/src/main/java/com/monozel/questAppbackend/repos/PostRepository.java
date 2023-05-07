package com.monozel.questAppbackend.repos;

import com.monozel.questAppbackend.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserId(Long userId);
    // <??> burada icinde bulunan objenin icindeki herhangi bir field'i findBy... kalibi ile birlestirebiliriz.
}
