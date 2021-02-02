package com.company.Netex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class MovieSearch {

        @JsonProperty("Search")
        private List<Movie> movieList;

        public List<Movie> getMovieList() {
            return movieList;
        }

        public void setMovieList(List<Movie> movieList) {
            this.movieList = movieList;
        }
}
