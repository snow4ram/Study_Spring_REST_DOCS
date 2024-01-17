package spring_rest_docs.rest_docs.dto;

import org.springframework.stereotype.Service;
import spring_rest_docs.rest_docs.controller.request.UserRequest;
import spring_rest_docs.rest_docs.entity.Member;
import spring_rest_docs.rest_docs.entity.User;

import java.util.function.Function;


@Service
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .style(user.getStyle())
                .reservationTime(user.getReservationTime())
                .reservationDay(user.getReservationDay())
                .build();
    }


}
