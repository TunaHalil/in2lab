package com.haw.srs.customerservice.movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MovieService {

    @Autowired
    public MovieRepository repository;
    public Movie save(Movie movie) {
        return repository.save(movie);
    }
}
