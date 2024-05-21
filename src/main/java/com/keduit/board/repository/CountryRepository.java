package com.keduit.board.repository;

import com.keduit.board.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCountryName(String countryName);
//
//    Country findByMovieId(Long movieId);
}
