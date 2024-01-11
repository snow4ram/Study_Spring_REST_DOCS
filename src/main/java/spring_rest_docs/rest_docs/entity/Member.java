package spring_rest_docs.rest_docs.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import spring_rest_docs.rest_docs.controller.MemberModificationRequest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;


@Getter
@Entity
@ToString
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at" , nullable = false)
    private LocalDateTime updatedAt;

    public Member(String email, String name) {
        this.email = email;
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void modification(MemberModificationRequest memberModificationRequest) {
        if (memberModificationRequest.getName() == null || memberModificationRequest.getName().isBlank()) {
            throw new RuntimeException("입력한 값이 없습니다.");
        }

        this.name = memberModificationRequest.getName();

        this.updatedAt = LocalDateTime.now();
    }

    public static LocalDateTime createTimestamp() {
        LocalDateTime startDate = LocalDateTime.parse("2023-01-01T00:00:00");
        LocalDateTime endDate = LocalDateTime.now();
        long days = ChronoUnit.DAYS.between(startDate, endDate);

        Random random = new Random();
        long randomDays = random.nextInt((int) days + 1);

        // 시간 부분도 랜덤으로 설정
        return startDate.plusDays(randomDays)
                .plusHours(random.nextInt(24))
                .plusMinutes(random.nextInt(60))
                .plusSeconds(random.nextInt(60));
    }
}
