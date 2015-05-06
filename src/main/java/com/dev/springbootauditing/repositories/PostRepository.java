package com.dev.springbootauditing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.dev.springbootauditing.entities.Post;

public interface PostRepository extends RevisionRepository<Post, Long, Integer>, JpaRepository<Post, Integer> {

}
