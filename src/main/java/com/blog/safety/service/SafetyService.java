package com.blog.safety.service;

import java.util.List;

import com.blog.safety.model.Posts;


public interface SafetyService {
	List<Posts> fidAll();
	Posts findById(long id);
	Posts save(Posts post);
	void deleteById(long  id);
	Posts getReferenceById(long id);
}
