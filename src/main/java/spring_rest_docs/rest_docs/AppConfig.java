package spring_rest_docs.rest_docs;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import spring_rest_docs.rest_docs.entity.MemberEntity;
import spring_rest_docs.rest_docs.repository.JpaMemberRpository;


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
