package com.platzi.javatests.movies.service;

import com.platzi.javatests.movies.data.MovieRepository;
import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;

public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findMoviesByGenre(Genre genre) {

        return movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre() == genre).collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByLength(int length) {

        return movieRepository.findAll().stream()
                .filter(movie -> movie.getMinutes() <= length).collect(Collectors.toList());
    }
    public Collection<Movie> findMoviesByName(String name){
        return  movieRepository.findAll().stream().filter(movie -> movie.getName().toLowerCase().contains(name)).collect(Collectors.toList());

    }
    public Collection<Movie> findMoviesByDirector(String name){

        Collection<Movie> allMovies = movieRepository.findAll();
        Collection<Movie> moviesFound = new ArrayList<>();

        for (Movie movie: allMovies){
            if(movie.getDirector().toLowerCase().contains(name.toLowerCase()))
                 moviesFound.add(movie);
        }


        return  moviesFound;
    }
}
