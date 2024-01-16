package spring_rest_docs.rest_docs;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import spring_rest_docs.rest_docs.Name;
import spring_rest_docs.rest_docs.entity.Member;
import spring_rest_docs.rest_docs.repository.JpaMemberRepository;


@Configuration
@RequiredArgsConstructor
public class AppConfig {


//    private final JpaMemberRepository repository;
//
//    //@PostConstruct
//    public void init() {
//
//        for (int i = 0; i < 10; i++) {
//            repository.save(new Member(Name.generateRandomEmail(), Name.randomKoreanName()));
//        }
//    }

    @Bean
    JPQLQueryFactory jpqlQueryFactory(EntityManager manager) {
        return new JPAQueryFactory(manager);
    }
}
