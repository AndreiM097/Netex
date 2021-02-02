package com.company.Netex.controller;


import com.company.Netex.model.Movie;
import com.company.Netex.model.MovieSearch;
import com.company.Netex.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import static com.company.Netex.service.MovieService.getSearchResponse;

@RestController
public class MovieController {


    List<Movie> Search;

    @Autowired
    private MovieService service;

    @GetMapping("/searchMovies")
    public List<Movie> searchMovies() throws IOException, InterruptedException{
        return service.saveMovies(getSearchResponse(MovieService.POSTS_API_URL_SEARCH));
    }

    @PostMapping("/addMovie")
    public Movie addMovie(@RequestBody Movie movie){
        return service.saveMovie(movie);
    }

    @PostMapping("/addMovies")
    public List<Movie> addMovie(@RequestBody List<Movie> movie) {
        return service.saveMovies(movie);
    }
    //Gets movies from DB!
    @GetMapping("/getMovies")
    public List<Movie> findMovies(){
        return service.getMovies();
    }

    @GetMapping("/getMovieById/{id}")
    public Movie findMoviesById(@PathVariable int id){
        return service.getMoviesById(id);
    }

    @PutMapping("/updateMovie")
    public Movie updateMovie(@RequestBody Movie movie){
        return service.updateMovie(movie);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMovie(@PathVariable int id){
        return service.deleteMovie(id);
    }

    @DeleteMapping("/deleteMovies/{start}/{end}")
    public String deleteMovies(@PathVariable int start,@PathVariable int end){return service.deleteMovies(start, end);}
}

