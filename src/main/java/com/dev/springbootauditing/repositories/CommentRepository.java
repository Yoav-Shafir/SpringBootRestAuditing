package com.dev.springbootauditing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.springbootauditing.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
