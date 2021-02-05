package com.company.Netex.repository;

import com.company.Netex.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MovieRepo extends JpaRepository<Movie, Integer> {

}
