package spring_rest_docs.rest_docs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_rest_docs.rest_docs.entity.Member;


public interface JpaMemberRepository extends JpaRepository<Member, Long> {
}
