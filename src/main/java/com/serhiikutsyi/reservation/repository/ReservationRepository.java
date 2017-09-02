package com.serhiikutsyi.reservation.repository;

import com.serhiikutsyi.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Serhii Kutsyi
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate, LocalDate endDate);
}
