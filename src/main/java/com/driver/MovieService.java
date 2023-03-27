package com.driver;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    //1
    public ResponseEntity<String> addMovie(Movie movie){
        movieRepository.getMovieMap().put(movie.getName(), movie);
        return new ResponseEntity<>("New Movie Added Successfully", HttpStatus.CREATED);
    }

    //2
    public ResponseEntity<String> addDirector(Director director){
        movieRepository.getDirectorMap().put(director.getName(), director);
        return new ResponseEntity<>("New Director Added Successfully", HttpStatus.CREATED);
    }

    //3
    public ResponseEntity<String> addMovieDirectorPair(String movieName, String directorName){
        if(!movieRepository.getDirectorMap().containsKey(directorName) || !movieRepository.getMovieMap().containsKey(movieName)){
            return new ResponseEntity<>("Movie or Director doesn't exist", HttpStatus.NOT_FOUND);
        }
        if(!movieRepository.getDirectorMovieMap().containsKey(directorName)){
            movieRepository.getDirectorMovieMap().put(directorName, new ArrayList<>());
        }

        movieRepository.getDirectorMovieMap().get(directorName).add(movieName);

        return new ResponseEntity<>("Movie mapped to director", HttpStatus.CREATED);
    }

    //4
    public ResponseEntity<Director> getMovieByName(String directorName){
        return new ResponseEntity<>(movieRepository.getDirectorMap().get(directorName), HttpStatus.FOUND);
    }

    //5
    public ResponseEntity<Director> getDirectorByName(String directorName){
        return new ResponseEntity<>(movieRepository.getDirectorMap().get(directorName), HttpStatus.FOUND);
    }

    //6
    public ResponseEntity<List<String>> getMoviesByDirectorName(String directorName){
        return new ResponseEntity<>(movieRepository.getDirectorMovieMap().get(directorName), HttpStatus.FOUND);
    }

    //7
    public ResponseEntity<List<String>> findAllMovies(){
        return new ResponseEntity<>(new ArrayList<>(movieRepository.getMovieMap().keySet()), HttpStatus.CREATED);
    }

    //8
    public ResponseEntity<String> deleteDirectorByName(String directorName){
        for (String movieName : movieRepository.getDirectorMovieMap().get(directorName)){
            movieRepository.getMovieMap().remove(movieName);
        }
        movieRepository.getDirectorMap().remove(directorName);
        movieRepository.getDirectorMovieMap().remove(directorName);

        return new ResponseEntity<>("Data Deleted Successfully", HttpStatus.OK);
    }

    //9
    public ResponseEntity<String> deleteAllDirectors(){
        for(String directorName: movieRepository.getDirectorMap().keySet()) {
            for (String movieName : movieRepository.getDirectorMovieMap().get(directorName)) {
                movieRepository.getMovieMap().remove(movieName);
            }
        }

        movieRepository.setDirectorMovieMap(new HashMap<>());
        movieRepository.setDirectorMap(new HashMap<>());

        return new ResponseEntity<>("All Director data Deleted", HttpStatus.OK);
    }
}
