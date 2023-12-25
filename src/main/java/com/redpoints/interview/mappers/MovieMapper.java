package com.redpoints.interview.mappers;

import com.redpoints.interview.model.Movie;
import com.redpoints.interview.service.data.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class MovieMapper {

	public MovieEntity modelToEntity(Movie movie) {
		MovieEntity movieEntity = new MovieEntity();
		movieEntity.setId(movie.getId());
		movieEntity.setTitle(movie.getTitle());
		movieEntity.setYear(movie.getYear());
		movieEntity.setDirector(movie.getDirector());

		return movieEntity;
//		return null;
	}

	public Movie entityToModel(MovieEntity movieEntity) {
		Movie movie = new Movie();
		movie.setId(movieEntity.getId());
		movie.setTitle(movieEntity.getTitle());
		movie.setYear(movieEntity.getYear());
//		added director assignation as well
		movie.setDirector(movieEntity.getDirector());

		return movie;
	}

	public List<Movie> entitiesToModels(List<MovieEntity> movieEntities) {
		return movieEntities.stream().map(this::entityToModel).toList();
	}
}
