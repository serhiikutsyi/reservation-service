package com.serhiikutsyi.reservation.exception;

/**
 * @author Serhii Kutsyi
 */
public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(String message) {
        super(message);
    }

}
