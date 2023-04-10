package com.haw.srs.customerservice;

import com.haw.srs.customerservice.customer.Customer;
import com.haw.srs.customerservice.customer.CustomerRepository;
import com.haw.srs.customerservice.phoneNumber.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        this.customerRepository.deleteAll();

        Customer customer = new Customer("Stefan", "Sarstedt", Gender.MALE,
                "stefan.sarstedt@haw-hamburg.de",
                new PhoneNumber("+49", "040", "428758434"));

        customerRepository.save(customer);
    }

    @Test
    void getCustomerSuccess() {
        Optional<Customer> customer = customerRepository.findByLastName("Sarstedt");
        assertThat(customer).isPresent();
    }

    @Test
    void getCustomersWithUnknownGenderSuccess() {
        List<Customer> customers = customerRepository.findCustomersWithUnknownGender();
        assertThat(customers.isEmpty()).isTrue();
    }
}
