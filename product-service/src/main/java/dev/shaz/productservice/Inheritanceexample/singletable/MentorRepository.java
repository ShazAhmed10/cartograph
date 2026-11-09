package dev.shaz.productservice.Inheritanceexample.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor,Long> {
}
