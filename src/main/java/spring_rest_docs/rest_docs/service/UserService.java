package spring_rest_docs.rest_docs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_rest_docs.rest_docs.controller.request.UserRequest;
import spring_rest_docs.rest_docs.controller.request.UserReservation;
import spring_rest_docs.rest_docs.dto.UserDTO;
import spring_rest_docs.rest_docs.dto.UserDTOMapper;
import spring_rest_docs.rest_docs.entity.Salon;
import spring_rest_docs.rest_docs.entity.User;
import spring_rest_docs.rest_docs.repository.JpaSalonRepository;
import spring_rest_docs.rest_docs.repository.JpaUserRepository;
import spring_rest_docs.rest_docs.repository.ScheduleJpqlQueryRepository;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {


    private final JpaUserRepository jpaUserRepository;

    private final JpaSalonRepository salonRepository;

    private final ScheduleJpqlQueryRepository jpqlQueryRepository;

    private final UserDTOMapper mapper;

    @Transactional
    public UserDTO reservation(UserRequest userRequest) {

        if (Objects.nonNull(userRequest)) {
            if (jpqlQueryRepository.salonReservation(userRequest)) {
                User build = User.builder()
                        .name(userRequest.getName())
                        .style(userRequest.getStyle())
                        .reservationDay(userRequest.getReservationDay())
                        .reservationTime(userRequest.getReservationTime()).build();
                User user = jpaUserRepository.save(build);

                Salon reservationNumber = Salon.builder()
                        .userId(user.getId())
                        .build();

                salonRepository.save(reservationNumber);

                return mapper.apply(user);
            }
        }
        throw new RuntimeException("사용자의 정보가 없습니다.");
    }


}
