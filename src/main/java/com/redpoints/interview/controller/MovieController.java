package com.redpoints.interview.controller;

import com.redpoints.interview.mappers.MovieMapper;
import com.redpoints.interview.model.Movie;
import com.redpoints.interview.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private final MovieService movieService;
	private final MovieMapper movieMapper;

	public MovieController(MovieService movieService, MovieMapper movieMapper) {
		this.movieService = movieService;
		this.movieMapper = movieMapper;
	}

	@GetMapping
	public List<Movie> getAllMovies() {
		return movieMapper.entitiesToModels(movieService.getAllMovies());
	}
}
