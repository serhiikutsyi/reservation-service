package com.serhiikutsyi.reservation.service;

import com.serhiikutsyi.reservation.domain.Reservation;
import com.serhiikutsyi.reservation.exception.ReservationNotFoundException;
import com.serhiikutsyi.reservation.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Serhii Kutsyi
 */
@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findByDateRange(LocalDate start, LocalDate end) {
        return reservationRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(end, start);
    }

    public Reservation findById(Long id) {
        Reservation existingReservation = reservationRepository.findOne(id);
        if (existingReservation == null) {
            throw new ReservationNotFoundException(String.format("Reservation not found with ID %s", id));
        }
        return existingReservation;
    }

    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = findById(id);
        existingReservation.setFirstName(reservation.getFirstName());
        existingReservation.setLastName(reservation.getLastName());
        existingReservation.setRoomNumber(reservation.getRoomNumber());
        existingReservation.setStartDate(reservation.getStartDate());
        existingReservation.setEndDate(reservation.getEndDate());
        return saveReservation(existingReservation);
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        Reservation existingReservation = reservationRepository.findOne(id);
        if (existingReservation == null) {
            throw new ReservationNotFoundException(String.format("Reservation not found with ID %s", id));
        }
        reservationRepository.delete(id);
    }

}
