package spring_rest_docs.rest_docs;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import spring_rest_docs.rest_docs.entity.MemberEntity;
import spring_rest_docs.rest_docs.repository.JpaMemberRpository;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final JpaMemberRpository repository;

    @PostConstruct
    public void init() {

        for (int i = 0; i < 10; i++) {
            repository.save(new MemberEntity(Name.generateRandomEmail(), Name.randomKoreanName()));
        }
    }
}
