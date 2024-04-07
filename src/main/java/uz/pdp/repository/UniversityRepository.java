package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity_1.University;
@Repository
public interface UniversityRepository extends JpaRepository<University,Integer> {
}
