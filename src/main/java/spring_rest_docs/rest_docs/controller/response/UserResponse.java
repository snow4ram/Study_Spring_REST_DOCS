package spring_rest_docs.rest_docs.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import spring_rest_docs.rest_docs.dto.UserDTO;
import spring_rest_docs.rest_docs.dto.UserDTOMapper;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class UserResponse {

    private String name;

    private String style; // 머리 짜르고 싶은 스타일

    private LocalTime reservationTime;

    private LocalDate reservationDay;

    public UserResponse(UserDTO userDTO) {
        this.name = userDTO.name();
        this.style = userDTO.style();
        this.reservationTime= userDTO.reservationTime();
        this.reservationDay = userDTO.reservationDay();
    }
}
