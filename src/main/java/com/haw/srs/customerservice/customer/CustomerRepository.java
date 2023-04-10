package com.haw.srs.customerservice.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {

    Optional<Customer> findByLastName(String lastName);

    @Query("SELECT c FROM Customer c WHERE c.gender = com.haw.srs.customerservice.Gender.UNKNOWN")
    List<Customer> findCustomersWithUnknownGender();
}
