package spring_rest_docs.rest_docs.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring_rest_docs.rest_docs.controller.request.UserRequest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.util.StringUtils.hasText;
import static spring_rest_docs.rest_docs.entity.QSalon.*;
import static spring_rest_docs.rest_docs.entity.QUser.*;


@Slf4j
@Repository
@RequiredArgsConstructor
public class ScheduleJpqlQueryRepository {


    private final JPQLQueryFactory query;


    @Transactional
    public Boolean salonReservation(UserRequest request) {

        long count = query.selectFrom(salon)
                .join(user).on(salon.userId.eq(user.id))
                .where(day(request.getReservationDay()))
                .where(reservationTime(request.getReservationTime()))
                .fetchCount();

        return count == 0 ? true : false;
    }

    private BooleanExpression day(LocalDate reservationDay) {

        BooleanExpression booleanExpressionDay = hasText(String.valueOf(reservationDay)) ? user.reservationDay.eq(reservationDay) : null;

        log.info("예약된 시간 비교 ={}" , booleanExpressionDay);

        return booleanExpressionDay;
    }

    private BooleanExpression reservationTime(LocalTime reservationTime) {
        return hasText(String.valueOf(reservationTime)) ? user.reservationTime.eq(reservationTime) : null ;
    }
}
