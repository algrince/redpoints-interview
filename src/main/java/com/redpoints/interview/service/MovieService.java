package com.redpoints.interview.service;

import com.redpoints.interview.service.data.MovieEntity;
import com.redpoints.interview.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

	private final MovieRepository repository;

	public MovieService(MovieRepository repository) {
		this.repository = repository;
	}

	public List<MovieEntity> getAllMovies() {
		return repository.findAll();
	}
}
