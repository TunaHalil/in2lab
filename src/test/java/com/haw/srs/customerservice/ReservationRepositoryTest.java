package com.haw.srs.customerservice;

import com.haw.srs.customerservice.*;
import com.haw.srs.customerservice.customer.Customer;
import com.haw.srs.customerservice.customer.CustomerRepository;
import com.haw.srs.customerservice.movie.Movie;
import com.haw.srs.customerservice.movie.MovieRepository;
import com.haw.srs.customerservice.phoneNumber.PhoneNumber;
import com.haw.srs.customerservice.reservation.Reservation;
import com.haw.srs.customerservice.reservation.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
public class ReservationRepositoryTest {


    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @BeforeEach
    void setUp() {

        Customer customer1 = new Customer("Stefan", "Sarstedt", Gender.MALE,
                "halil@haw-hamburg.de",
                new PhoneNumber("+49", "040", "12345678"));
        Customer customer2 = new Customer("Max", "Mustermann", Gender.FEMALE,
                "nino@haw-hamburg.de",
                new PhoneNumber("+49", "040", "87654321"));
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Movie movie = new Movie("Kevin allein in New York", 120);
        movieRepository.save(movie);
        Reservation reservation1 = new Reservation(movie);
        Reservation reservation2 = new Reservation(movie);
        customer1.addReservation(reservation1);
        customer2.addReservation(reservation2);
        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
    }
    @Test
    void getListOfReservations(){

        Movie m = movieRepository.findByTitle("Kevin allein in New York");
        List<Reservation> reservations = reservationRepository.findAllByMovie(m);
        assertThat(reservations).size().isEqualTo(2);
    }
}
