package com.haw.srs.customerservice.customer;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<Customer> findCustomersWithoutReservations();
}
