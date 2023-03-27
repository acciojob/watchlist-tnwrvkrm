package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieService movieService;

    //1
    @PostMapping("add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
       return movieService.addMovie(movie);
    }

    //2
    @PostMapping("add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        return movieService.addDirector(director);
    }

    //3
    @PostMapping("add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movieName, @RequestParam("director") String directorName){
        return movieService.addMovieDirectorPair(movieName, directorName);
    }

    //4
    @GetMapping("get-movie-by-name/{name}")
    public ResponseEntity<Director> getMovieByName(@PathVariable String directorName){
        return movieService.getMovieByName(directorName);
    }

    //5
    @GetMapping("get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String directorName){
        return movieService.getDirectorByName(directorName);
    }

    //6
    @GetMapping("get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String directorName){
        return movieService.getMoviesByDirectorName(directorName);
    }

    //7
    @GetMapping("get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        return movieService.findAllMovies();
    }

    //8
    @DeleteMapping("delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String directorName){
        return movieService.deleteDirectorByName(directorName);
    }

    //9
    @DeleteMapping("delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        return movieService.deleteAllDirectors();
    }
}
