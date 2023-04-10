package com.haw.srs.customerservice;

import com.haw.srs.customerservice.customer.Customer;
import com.haw.srs.customerservice.customer.CustomerRepository;
import com.haw.srs.customerservice.movie.Movie;
import com.haw.srs.customerservice.movie.MovieService;
import com.haw.srs.customerservice.reservation.Reservation;
import com.haw.srs.customerservice.phoneNumber.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Component
//@Profile("testing")
class PopulateTestDataRunner implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    public PopulateTestDataRunner(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) {
        customerRepository.deleteAll();

        Arrays.asList(
                        "Miller,Doe,Smith".split(","))
                .forEach(
                        name -> customerRepository.save(new Customer("Jane", name, Gender.FEMALE, name + "@dummy.org", null))
                );

        Customer customer =
                new Customer("Stefan", "Sarstedt", Gender.MALE, "stefan.sarstedt@haw-hamburg.de", new PhoneNumber("+49-40-123456"));
        Movie movie = new Movie("James Bond 007", 149);
        movieService.save(movie);
        Reservation reservation = new Reservation(movie);
        customer.addReservation(reservation);
        customerRepository.save(customer);
    }
}
