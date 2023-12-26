package com.redpoints.interview.service;

import com.redpoints.interview.exceptions.MovieNotFoundException;
import com.redpoints.interview.service.data.MovieEntity;
import com.redpoints.interview.repository.MovieRepository;
import com.redpoints.interview.validators.MovieValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

	private final MovieRepository repository;
	private final MovieValidator validator;

	public MovieService(MovieRepository repository, MovieValidator validator) {
		this.repository = repository;
		this.validator = validator;
	}

	// To ensure that every method will be carried within a transaction,
	// Transactional annotation was added
	@Transactional(readOnly = true)
	public List<MovieEntity> getAllMovies() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public MovieEntity getMovieById(Long id) {
		validator.validateIdNotNull(id);
		return repository.findById(id).orElseThrow(() ->
				new MovieNotFoundException(String.format("There is no movie with this id: %d", id)));
	}

	@Transactional
	public MovieEntity createMovie(MovieEntity movieEntityToCreate) {
		return repository.save(movieEntityToCreate);
	}

	@Transactional
	public MovieEntity updateMovie(MovieEntity movieEntityToUpdate, Long pathVariableId) {
		validator.validateIdNotNull(pathVariableId);
		Long movieEntityToUpdateId = movieEntityToUpdate.getId();
		validator.validateMovieForUpdate(movieEntityToUpdateId, pathVariableId);
		return repository.save(movieEntityToUpdate);
	}

	@Transactional
	public void deleteMovieById(Long id) {
		validator.validateIdNotNull(id);
		// deleteById() method is used. In this case if an object is found in DB it will be deleted.
		// If it's necessary to ensure that object exists, use of delete() method could be considered.
		repository.deleteById(id);
	}
}
