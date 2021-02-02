package com.company.Netex.service;

import com.company.Netex.model.Movie;
import com.company.Netex.model.MovieSearch;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.Netex.repository.MovieRepo;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepo repository;
    public static final String POSTS_API_URL = "http://www.omdbapi.com/?i=tt3896198&apikey=76003b78&s=Avengers";
    public static final String POSTS_API_URL_SEARCH = "http://www.omdbapi.com/?i=tt3896198&apikey=76003b78&s=Avengers";

    public MovieService(MovieRepo repository) throws IOException, InterruptedException {
        this.repository = repository;
    }

    public Movie saveMovie(Movie movie){
        return repository.save(movie);
    }

    //TO DO : with @PathVariable String {movieName} instead of POSTS_API_URL
    public static List<Movie> getSearchResponse(String POSTS_API_URL_SEARCH) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL))
                .build();

        //Parsing into objects
        ObjectMapper mapper = new ObjectMapper();
        MovieSearch movieSearch = mapper.readValue(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), new TypeReference<MovieSearch>() {});
        List<Movie> movies = movieSearch.getMovieList();
        return movies;
    }

    public List<Movie> saveMovies(List<Movie> movies) {
        return repository.saveAll(movies);
    }

    public List<Movie> getMovies() {
        return repository.findAll();
    }

    public Movie getMoviesById(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteMovie(int id){
        repository.deleteById(id);
        return "The movie with id: " + id + "has been deleted.";
    }

    public String deleteMovies(int start, int end){
        for(int i = start; i <= end; i++){
            repository.deleteById(i);
        }
        return "All the movies with ID's between " + start + " and " + end + " have been deleted.";
    }

    public Movie updateMovie(Movie movie){
        Movie existingMovie = repository.findById(movie.getId()).orElse(null);
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setYear(movie.getYear());
        existingMovie.setImdbID(movie.getImdbID());
        existingMovie.setType(movie.getType());
        existingMovie.setPoster(movie.getPoster());
        return repository.save(existingMovie);
    }
}
