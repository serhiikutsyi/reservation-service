package com.serhiikutsyi.reservation.service;

import com.serhiikutsyi.reservation.domain.Reservation;
import com.serhiikutsyi.reservation.exception.ReservationNotFoundException;
import com.serhiikutsyi.reservation.repository.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Serhii Kutsyi
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    private ReservationService reservationService;
    private Reservation reservation;

    @Before
    public void init() {
        reservationService = new ReservationServiceImpl(reservationRepository);

        reservation = new Reservation();
        reservation.setFirstName("Elon");
        reservation.setLastName("Musk");
        reservation.setRoomNumber(101);
        reservation.setStartDate(LocalDate.now());
        reservation.setEndDate(LocalDate.now().plusDays(10));
    }

    @Test
    public void shouldRetrieveReservationById() throws Exception {
        when(reservationRepository.findOne(Mockito.anyLong())).thenReturn(reservation);
        assertEquals(reservationService.findById(Mockito.anyLong()), reservation);
    }

    @Test(expected = ReservationNotFoundException.class)
    public void ifReservationIsNotFoundThrowException() {
        reservationService.findById(Long.MAX_VALUE);
    }

}
