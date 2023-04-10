package com.haw.srs.customerservice;

import com.haw.srs.customerservice.customer.Customer;
import com.haw.srs.customerservice.movie.Movie;
import com.haw.srs.customerservice.movie.MovieService;
import com.haw.srs.customerservice.reservation.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
class JPAExceptionTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MovieService movieService;

    @Test
    @Transactional
    void getAllCustomersSuccess() {

        Customer customer = new Customer("Jane", "Doe", Gender.FEMALE,
                "jane.doe@mail.com",null);
        Movie jamesbond = new Movie("James Bond 007",120);
        movieService.save(jamesbond);
        Reservation reservation = new Reservation(jamesbond);
        customer.addReservation(reservation);

        entityManager.persist(customer);
        entityManager.detach(customer);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> { entityManager.persist(customer); });
    }
}
