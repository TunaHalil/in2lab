package com.haw.srs.customerservice.reservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.haw.srs.customerservice.movie.Movie;
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    public List<Reservation> findAllByMovie(Movie movie);
}
