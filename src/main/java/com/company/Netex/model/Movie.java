package com.company.Netex.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @JsonProperty("Title")
    @Column(name = "Title")
    private String title;

    @JsonProperty("Year")
    @Column(name = "Year")
    private String year;

    @JsonProperty("imdbID")
    @Column(name = "imdbID")
    private String imdbID;

    @JsonProperty("Type")
    @Column(name = "Type")
    private String type;

    @JsonProperty("Poster")
    @Column(name = "Poster") 
    private String poster;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter("Title")
    public String getTitle() {
        return title;
    }

    @JsonSetter("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonGetter("Year")
    public String getYear() {
        return year;
    }

    @JsonSetter("Year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonGetter("imdbID")
    public String getImdbID() {
        return imdbID;
    }

    @JsonSetter("imdbId")
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    @JsonGetter("Type")
    public String getType() {
        return type;
    }

    @JsonSetter("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonGetter("Poster")
    public String getPoster() {
        return poster;
    }

    @JsonSetter("Poster")
    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}

    

