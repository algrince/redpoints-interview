package com.redpoints.interview.service;

import com.redpoints.interview.exceptions.InvalidIdException;
import com.redpoints.interview.exceptions.MovieNotFoundException;
import com.redpoints.interview.repository.MovieRepository;
import com.redpoints.interview.service.data.MovieEntity;
import com.redpoints.interview.validators.MovieValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private MovieRepository repository;

    @Mock
    private MovieValidator validator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetAllMoviesPositive() {
//        given
        MovieService service = new MovieService(repository, validator);
        List<MovieEntity> movieEntities = new ArrayList<>();
        Collections.addAll(movieEntities,
                new MovieEntity("Tenet", "Christopher Nolan", 2020),
                new MovieEntity("Oppenheimer", "Christopher Nolan", 2023),
                new MovieEntity("TBOSAS", "Francis Lawrence", 2023));
        doReturn(movieEntities).when(repository).findAll();

//        when
        List<MovieEntity> foundMovieEntities = service.getAllMovies();

//        then
        verify(repository).findAll();
        assertEquals(movieEntities, foundMovieEntities);
    }

    @Test
    public void testGetMovieByIdPositive() {
//        given
        MovieService service = new MovieService(repository, validator);
        Long id = 1L;
        MovieEntity movieEntity = new MovieEntity("Tenet", "Christopher Nolan", 2020);
        doReturn(Optional.of(movieEntity)).when(repository).findById(id);

//        when
        MovieEntity foundMovieEntity = service.getMovieById(id);

//        then
        verify(repository).findById(id);
        assertEquals(movieEntity, foundMovieEntity);
    }

    @Test
    public void testGetMovieByIdNullId() {
//        given
        MovieService service = new MovieService(repository, validator);
        Long id = null;
        doThrow(InvalidIdException.class).when(validator).validateIdNotNull(id);

//        then
        assertThrows(InvalidIdException.class, () -> service.getMovieById(id));
    }

    @Test
    public void testGetMovieByIdWrongId() {
//        given
        MovieService service = new MovieService(repository, validator);

//        then
        assertThrows(MovieNotFoundException.class, () -> service.getMovieById(2L));
    }


    @Test
    public void testCreateMovie() {
//        given
        MovieService service = new MovieService(repository, validator);
        MovieEntity movieEntity = new MovieEntity("Tenet", "Christopher Nolan", 2020);
        doReturn(movieEntity).when(repository).save(movieEntity);

//        when
        MovieEntity createdMovieEntity = service.createMovie(movieEntity);

//        then
        verify(repository).save(movieEntity);
        assertEquals(movieEntity, createdMovieEntity);
    }


    @Test
    public void testUpdateMoviePositive() {
//        given
        MovieService service = new MovieService(repository, validator);
        MovieEntity oldMovieEntity = new MovieEntity("Tenet", "Christopher Nolan", 2020);
        MovieEntity newMovieEntity = new MovieEntity("Oppenheimer", "Christopher Nolan", 2023);
        Long id = 1L;
        oldMovieEntity.setId(id);
        newMovieEntity.setId(id);
        doReturn(newMovieEntity).when(repository).save(newMovieEntity);

//        when
        MovieEntity updatedMovieEntity = service.updateMovie(newMovieEntity, id);

//        then
        verify(repository).save(newMovieEntity);
        verify(validator).validateMovieForUpdate(newMovieEntity.getId(), id);
        verify(validator).validateIdNotNull(id);

        assertEquals(newMovieEntity, service.updateMovie(newMovieEntity, id));

    }

    @Test
    public void testDeleteMovie() {
//        given
        MovieService service = new MovieService(repository, validator);
        Long id = 1L;

//        when
        service.deleteMovieById(id);

//        then
        verify(repository).deleteById(id);
    }

}
