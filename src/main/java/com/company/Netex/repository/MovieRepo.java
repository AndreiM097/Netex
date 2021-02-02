package com.company.Netex.repository;

import com.company.Netex.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
}
