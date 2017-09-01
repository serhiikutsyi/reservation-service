package com.serhiikutsyi.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serhiikutsyi.reservation.domain.Reservation;
import com.serhiikutsyi.reservation.exception.ReservationNotFoundException;
import com.serhiikutsyi.reservation.service.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Serhii Kutsyi
 */

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    private Reservation reservation;
    private String jsonReservation;

    @Before
    public void init() throws Exception {
        reservation = new Reservation();
        reservation.setFirstName("Elon");
        reservation.setLastName("Musk");
        reservation.setRoomNumber(101);
        reservation.setStartDate(LocalDate.now());
        reservation.setEndDate(LocalDate.now().plusDays(10));
        ObjectMapper mapper = new ObjectMapper();
        jsonReservation = mapper.writeValueAsString(reservation);
    }

    @Test
    public void shouldRetrieveReservationById() throws Exception {
        Mockito.when(reservationService.findById(Mockito.anyLong())).thenReturn(reservation);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/reservations/{id}", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        JSONAssert.assertEquals(jsonReservation, result.getResponse()
                .getContentAsString(), false);

    }

    @Test
    public void shouldRetrieveReservations() throws Exception {
        List<Reservation> listReservations = Arrays.asList(reservation);

        Mockito.when(reservationService.getAll()).thenReturn(listReservations);

        mockMvc.perform(MockMvcRequestBuilders.get("/reservations")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldCreateReservation() throws Exception {
        Mockito.when(reservationService.saveReservation(Mockito.anyObject())).
                thenReturn(reservation);

        mockMvc.perform(MockMvcRequestBuilders.post("/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Mark\",\"lastName\":\"Zuckerberg\",\"roomNumber\":\"101\"}"))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void shouldUpdateReservation() throws Exception {
        Mockito.when(reservationService.updateReservation(Mockito.anyLong(), Mockito.anyObject())).thenReturn(reservation);

        mockMvc.perform(MockMvcRequestBuilders.put("/reservations/{id}", "1")
                .contentType(APPLICATION_JSON)
                .content("{\"firstName\":\"Mark\",\"lastName\":\"Zuckerberg\",\"roomNumber\":\"201\"}"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldThrowExceptionIfReservationNotFoundWhenUpdate() throws Exception {
        Mockito.doThrow(new ReservationNotFoundException("Reservation not found"))
                .when(reservationService).updateReservation(Mockito.anyLong(), Mockito.anyObject());

        mockMvc.perform(MockMvcRequestBuilders.put("/reservations/{id}", "1")
                .contentType(APPLICATION_JSON)
                .content("{\"firstName\":\"Steve\",\"lastName\":\"Jobs\",\"roomNumber\":\"101\"}"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void shouldDeleteReservation() throws Exception {
        mockMvc.perform(delete("/reservations/{id}", "1")).andExpect(status().isNoContent());
    }

    @Test
    public void shouldThrowExceptionIfReservationNotFoundWhenDelete() throws Exception {
        Mockito.doThrow(new ReservationNotFoundException("Reservation not found")).when(reservationService).deleteReservation(Mockito.anyLong());

        mockMvc.perform(delete("/reservations/{id}", "1")).andExpect(status().isNotFound());
    }


}
