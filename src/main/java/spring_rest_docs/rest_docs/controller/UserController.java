package spring_rest_docs.rest_docs.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring_rest_docs.rest_docs.controller.request.UserRequest;
import spring_rest_docs.rest_docs.controller.response.UserResponse;
import spring_rest_docs.rest_docs.dto.UserDTO;
import spring_rest_docs.rest_docs.service.UserService;

import java.net.URI;

@Slf4j
@RestController
@Tag(name = "user-controller" , description = "일반 사용자 서비스를 위한 컨트롤러")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService users;

/*  Reservation 예약
    Reservation Cancellation 예약 취소
    Reservation Modification 예약 변경
    Reservation Confirmation 예약 확인*/
    @Operation(summary = "미용실 예약 요청", description = "미용실 정보가 예약 됩니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content =
            @Content(schema = @Schema(
                    implementation = UserResponse.class,
                    name = "사용자 정보"
            ))),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500")
    })
    @PostMapping("/reservation")
    public ResponseEntity<UserResponse> reservation(
            @Parameter(description = "예약 요청",required = true, example = "1")
            @RequestBody UserRequest userRequest) {

        UserDTO reservation = users.reservation(userRequest);

        URI build = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{day}")
                .buildAndExpand(reservation.reservationDay())
                .toUri();

        UserResponse userResponse = new UserResponse(reservation);

        return ResponseEntity
                .created(build)
                .body(userResponse);
    }

}
