package spring_rest_docs.rest_docs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_rest_docs.rest_docs.member.MemberEntity;

public interface JpaMemberRpository extends JpaRepository<MemberEntity, Long> {
}
