package com.serhiikutsyi.reservation.service;

import com.serhiikutsyi.reservation.domain.Reservation;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Serhii Kutsyi
 */
public interface ReservationService {

    List<Reservation> getAll();

    List<Reservation> findByDateRange(LocalDate start, LocalDate end);

    Reservation findById(Long id);

    Reservation saveReservation(Reservation reservation);

    Reservation updateReservation(Long id, Reservation reservation);

    void deleteReservation(Long id);

}
