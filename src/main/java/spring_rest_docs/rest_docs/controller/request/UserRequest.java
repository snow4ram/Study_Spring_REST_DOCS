package spring_rest_docs.rest_docs.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import spring_rest_docs.rest_docs.entity.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Schema(description = "미용실 예약을 위한 도메인")
@NoArgsConstructor
public class UserRequest {


    @Schema(title = "이름")
    private String name;

    @Schema(title = "머리 스타일")
    private String style;

    @Schema(title = "예약 시간" , type = "LocalTime", description = "important date")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime reservationTime;

    @Schema(title = "예약 날짜" ,type = "LocalDate", description = "important date")
    @JsonFormat(pattern = "yyyy:MM:dd")
    private LocalDate reservationDay;

    public UserRequest(String name, String style, LocalTime reservationTime, LocalDate reservationDay) {
        this.name = name;
        this.style = style;
        this.reservationTime = reservationTime;
        this.reservationDay = reservationDay;
    }
}
