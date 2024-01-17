package spring_rest_docs.rest_docs.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class UserReservation {

    private String name;

    private String style; // 머리 짜르고 싶은 스타일

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime reservationTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDate reservationDay;

    public UserReservation(String name, String style, LocalTime reservationTime, LocalDate reservationDay) {
        this.name = name;
        this.style = style;
        this.reservationTime = reservationTime;
        this.reservationDay = reservationDay;
    }
}
