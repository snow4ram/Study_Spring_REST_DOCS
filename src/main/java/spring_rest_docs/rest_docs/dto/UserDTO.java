package spring_rest_docs.rest_docs.dto;


import lombok.Builder;
import spring_rest_docs.rest_docs.entity.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;
@Builder
public record UserDTO(
        Long id,

        String name,
        String style,

        LocalTime reservationTime,

        LocalDate reservationDay,

        ReservationStatus reservationStatus
) {
}
