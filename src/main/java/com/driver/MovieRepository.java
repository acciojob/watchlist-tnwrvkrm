package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMap;


    public MovieRepository() {
        this.movieMap = new HashMap<>();
        this.directorMap = new HashMap<>();
        this.directorMovieMap = new HashMap<>();
    }

    public HashMap<String, Movie> getMovieMap() {
        return movieMap;
    }

    public void setMovieMap(HashMap<String, Movie> movieMap) {
        this.movieMap = movieMap;
    }

    public HashMap<String, Director> getDirectorMap() {
        return directorMap;
    }

    public void setDirectorMap(HashMap<String, Director> directorMap) {
        this.directorMap = directorMap;
    }

    public HashMap<String, List<String>> getDirectorMovieMap() {
        return directorMovieMap;
    }

    public void setDirectorMovieMap(HashMap<String, List<String>> directorMovieMap) {
        this.directorMovieMap = directorMovieMap;
    }
}
