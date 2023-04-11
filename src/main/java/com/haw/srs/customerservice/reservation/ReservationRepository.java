package com.haw.srs.customerservice.reservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.haw.srs.customerservice.movie.Movie;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    //@Query ("SELECT m FROM Movie m WHERE m.titel =  Movie.titel")


 //   @PersistenceContext
   // private EntityManager entityManager;


   // @Transactional
     public List<Reservation> findAllByMovie(Movie movie);
   //      return entityManager.createQuery(
   //              "SELECT r FROM Reservation r WHERE r.movie = movie", Reservation.class).getResultList();

     }



