package com.example.BlogHR.repositories;

import com.example.BlogHR.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

