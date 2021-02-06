package com.company.Netex.controller;


import com.company.Netex.model.Movie;
import com.company.Netex.model.QMovie;
import com.company.Netex.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import static com.company.Netex.service.MovieService.*;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    List<Movie> Search;

    @Autowired
    private MovieService service;

    @GetMapping("/searchMovies/{search}")
    public void searchMovies(@PathVariable String search) throws IOException, InterruptedException, IllegalArgumentException {
        for(int i = 1; i <10; i++) {
            service.saveMovies(getSearchResponse("http://www.omdbapi.com/?i=tt3896198&apikey=76003b78&page=" + i + "&s=" + search + "/"));
        }
    }

    @GetMapping("/getMoviesOrderedByYear/{from}/{to}")
    public List<Movie> getMoviesOrderedByYear(@PathVariable String from, @PathVariable String to){
        return service.getJpaQueryFactory().selectFrom(QMovie.movie).where(QMovie.movie.year.between(from, to)).fetch();
    }

    @GetMapping("/searchMovieByTitle/{title}")
    public List<Movie> searchMovieByTitle(@PathVariable String title){
        return service.getJpaQueryFactory().selectFrom(QMovie.movie).where(QMovie.movie.title.contains(title)).fetch();
    }

    @GetMapping("/orderMoviesByTitle")
    public List<Movie> orderMoviesByTitle(){
        return service.getJpaQueryFactory().selectFrom(QMovie.movie).orderBy(QMovie.movie.title.asc()).fetch();
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

