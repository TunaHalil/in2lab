package com.haw.srs.customerservice.reservation;

import com.haw.srs.customerservice.movie.Movie;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Movie movie;
    private int platznummer;
    private int saalnummer;

    public Reservation(Movie movie) {

        this.movie = movie;
    }
}
