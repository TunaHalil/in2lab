package com.haw.srs.customerservice;
import com.haw.srs.customerservice.*;
import com.haw.srs.customerservice.movie.Movie;
import com.haw.srs.customerservice.movie.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {

        Movie movie = new Movie("Kevin allein Zuhaus", 120);
        movieRepository.save(movie);
    }
    @Test
    void getMovie(){
        Movie movie = movieRepository.findByTitle("Kevin allein Zuhaus");
        assertThat(movie.getTitel()).isEqualTo("Kevin allein Zuhaus");
    }

}
