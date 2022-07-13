package com.blog.safety.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.safety.model.Posts;
import com.blog.safety.repository.SafetyRepository;
import com.blog.safety.service.SafetyService;


@Service
public class SafetyServiceImpl implements SafetyService{
	
	@Autowired
	SafetyRepository safetyRepository;
	
	@Override
	public List<Posts> fidAll() {
		return safetyRepository.findAll();
	}

	@Override
	public Posts findById(long id) {
		return safetyRepository.findById(id).get();
	}

	@Override
	public Posts save(Posts post) {
		return safetyRepository.save(post);
	}

	@Override
	public void deleteById(long id) {
		safetyRepository.deleteById(id);
	}

	@Override
	public Posts getReferenceById(long id) {
		return safetyRepository.getReferenceById(id);
	}
}
