package com.redpoints.interview.controller;

import com.redpoints.interview.mappers.MovieMapper;
import com.redpoints.interview.model.Movie;
import com.redpoints.interview.service.MovieService;
import com.redpoints.interview.service.data.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("{id}")
	public Movie getMovieById(@PathVariable("id") Long id) {
		return movieMapper.entityToModel(movieService.getMovieById(id));
	}

	@PostMapping
	public Movie createMovie(@RequestBody Movie movieToCreate) {
		MovieEntity createdMovieEntity = movieService.createMovie(movieMapper.modelToEntity(movieToCreate));
		return movieMapper.entityToModel(createdMovieEntity);
	}

	@PutMapping("{id}")
	public Movie updateMovie(
			@PathVariable("id") Long id,
			@RequestBody Movie movieToUpdate) {
		MovieEntity updatedMovieEntity = movieService.updateMovie(movieMapper.modelToEntity(movieToUpdate), id);
		return movieMapper.entityToModel(updatedMovieEntity);
	}

	@DeleteMapping("{id}")
	public void deleteMovie(@PathVariable("id") Long id) {
		movieService.deleteMovieById(id);
	}
}
