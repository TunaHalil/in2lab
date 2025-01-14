package com.haw.srs.customerservice.customer;

import com.haw.srs.customerservice.Gender;
import com.haw.srs.customerservice.phoneNumber.PhoneNumber;
import com.haw.srs.customerservice.reservation.Reservation;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data //generiert automatisch Standardmethoden wie Getter, Setter, toString, equals
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    //einkommentieren, wum Abbildung in DB als String zu konfigurieren, anstelle von Integer
    //@Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;

    private PhoneNumber phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name="customerId") // generiert dann keine Jointabelle
    @Setter(AccessLevel.NONE)
    private List<Reservation> reservations = new ArrayList<>();

    public Customer(String firstName, String lastName, Gender gender, String email, PhoneNumber phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String firstName, String lastName, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = null;
        this.phoneNumber = null;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
