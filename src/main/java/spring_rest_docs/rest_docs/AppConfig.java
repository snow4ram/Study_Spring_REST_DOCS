package spring_rest_docs.rest_docs;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import spring_rest_docs.rest_docs.entity.Member;
import spring_rest_docs.rest_docs.repository.JpaMemberRepository;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final JpaMemberRepository repository;

    @PostConstruct
    public void init() {

        for (int i = 0; i < 10; i++) {
            repository.save(new Member(Name.generateRandomEmail(), Name.randomKoreanName()));
        }
    }
}
