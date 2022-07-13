package com.blog.safety.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.safety.model.Posts;


public interface SafetyRepository extends JpaRepository<Posts, Long>{

}
