package com.serhiikutsyi.reservation.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serhiikutsyi.reservation.domain.Reservation;
import com.serhiikutsyi.reservation.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.LocalDate;

/**
 * @author Serhii Kutsyi
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Bean
    CommandLineRunner dataLoader(ReservationRepository reservationRepository) {
        return (args) -> {
            reservationRepository.save(new Reservation("Elon", "Musk", 101, LocalDate.parse("2017-09-01"), LocalDate.parse("2017-09-03")));
            reservationRepository.save(new Reservation("Chris", "Hadfield", 101, LocalDate.parse("2017-09-04"), LocalDate.parse("2017-09-06")));
            reservationRepository.save(new Reservation("Jeff", "Bezos", 102, LocalDate.parse("2017-09-01"), LocalDate.parse("2017-09-07")));
            reservationRepository.save(new Reservation("Travis", "Kalanick", 102, LocalDate.parse("2017-09-08"), LocalDate.parse("2017-09-14")));
        };
    }

}
