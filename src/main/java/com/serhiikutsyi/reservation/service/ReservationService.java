package com.serhiikutsyi.reservation.service;

import com.serhiikutsyi.reservation.domain.Reservation;

import java.util.List;

/**
 * @author Serhii Kutsyi
 */
public interface ReservationService {

    List<Reservation> getAll();

    Reservation findById(Long id);

    Reservation saveReservation(Reservation reservation);

    Reservation updateReservation(Long id, Reservation reservation);

    void deleteReservation(Long id);

}
