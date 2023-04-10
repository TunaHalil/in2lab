package com.haw.srs.customerservice;

import com.haw.srs.customerservice.customer.Customer;
import com.haw.srs.customerservice.customer.CustomerNotFoundException;
import com.haw.srs.customerservice.customer.CustomerRepository;
import com.haw.srs.customerservice.customer.CustomerService;
import com.haw.srs.customerservice.reservation.Reservation;
import com.haw.srs.customerservice.movie.Movie;
import com.haw.srs.customerservice.movie.MovieService;
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
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository realCustomerRepository;

    @Autowired
    private MovieService movieService;

    @Test
    void getAllCustomersSuccess() {

        realCustomerRepository.deleteAll();

        Customer customer = new Customer("Jane", "Doe", Gender.FEMALE, "jane.doe@mail.com", null);
        realCustomerRepository.save(customer);

        List<Customer> actual = customerService.getCustomers();
        assertThat(actual).size().isEqualTo(1);
        assertThat(actual.get(0).getFirstName()).isEqualTo("Jane");
    }

    @Test
    void transferReservationsSuccess() throws CustomerNotFoundException {

        realCustomerRepository.deleteAll();

        Movie jamesbond = new Movie("James Bond 007", 120);
        Movie rosamundepilcher = new Movie("Rosamunde Pilcher", 120);
        movieService.save(jamesbond);
        movieService.save(rosamundepilcher);

        Customer from = new Customer("John", "Smith", Gender.MALE);
        from.addReservation(new Reservation(jamesbond));
        from.addReservation(new Reservation(rosamundepilcher));
        realCustomerRepository.save(from);
        Customer to = new Customer("Eva", "Miller", Gender.FEMALE);
        realCustomerRepository.save(to);

        assertThat(from.getReservations()).size().isEqualTo(2);
        assertThat(to.getReservations()).size().isEqualTo(0);

        customerService.transferReservations(from.getLastName(), to.getLastName());

        // versuche es hier einmal ohne Neuladen...warum klappt es nicht?
        from = realCustomerRepository.findByLastName(from.getLastName()).get();
        to   = realCustomerRepository.findByLastName(to.getLastName()).get();
        assertThat(from.getReservations()).size().isEqualTo(0);
        assertThat(to.getReservations()).size().isEqualTo(2);
    }
}