package spring_rest_docs.rest_docs.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static spring_rest_docs.rest_docs.entity.ReservationStatus.*;

@Slf4j
@Getter
@Entity
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "style" , nullable = false)
    private String style;

    @Column(name = "reservation_time" , nullable = false)
    private LocalTime reservationTime; //

    @Column(name = "reservation_day" , nullable = false)
    private LocalDate reservationDay;

    @Enumerated(value = EnumType.STRING)
    private ReservationStatus reservationStatus;


    @Builder
    public User(String name, String style, LocalTime reservationTime, LocalDate reservationDay) {
        this.name = name;
        this.style = style;
        this.reservationTime = reservationTime;
        this.reservationDay = reservationDay;
        this.reservationStatus = PROGRESS;
    }

    public void reservationConfirm(ReservationStatus status) {

        
    }




}
