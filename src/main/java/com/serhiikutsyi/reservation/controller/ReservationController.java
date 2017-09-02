package com.serhiikutsyi.reservation.controller;

import com.serhiikutsyi.reservation.domain.Reservation;
import com.serhiikutsyi.reservation.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Serhii Kutsyi
 */

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @ApiOperation(value = "Get a reservation with an ID ", response = Reservation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved reservation"),
            @ApiResponse(code = 404, message = "The reservation you were trying to reach is not found")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @ApiOperation(value = "View a list of available reservations", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getReservations() {
        return reservationService.getAll();
    }

    @ApiOperation(value = "Search Reservations by date range", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })@GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> findByDateRange(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                             @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return reservationService.findByDateRange(start, end);
    }

    @ApiOperation(value = "Create a reservation", response = Reservation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created reservation")

    })    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@Valid @RequestBody Reservation reservation) {
        return reservationService.saveReservation(reservation);
    }

    @ApiOperation(value = "Update a reservation", response = Reservation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated reservation"),
            @ApiResponse(code = 404, message = "The reservation you were trying to update is not found")

    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Reservation edit(@PathVariable Long id, @Valid @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @ApiOperation(value = "Delete a reservation with an ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted reservation"),
            @ApiResponse(code = 404, message = "The reservation you were trying to delete is not found")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
