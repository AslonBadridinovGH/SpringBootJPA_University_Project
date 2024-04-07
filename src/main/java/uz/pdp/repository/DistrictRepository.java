package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.District;
@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {
}
