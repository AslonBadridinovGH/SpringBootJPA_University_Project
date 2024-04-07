package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Continent;

@Repository
public interface ContinentRepository extends JpaRepository<Continent,Integer> {
}
