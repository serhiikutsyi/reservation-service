package com.serhiikutsyi.reservation.repository;

import com.serhiikutsyi.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Serhii Kutsyi
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
