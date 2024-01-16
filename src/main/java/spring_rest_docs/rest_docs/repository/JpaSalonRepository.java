package spring_rest_docs.rest_docs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_rest_docs.rest_docs.entity.Salon;
import spring_rest_docs.rest_docs.entity.User;

public interface JpaSalonRepository extends JpaRepository<Salon, Long> {


}
