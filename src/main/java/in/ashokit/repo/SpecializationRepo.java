package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Specialization;

public interface SpecializationRepo extends JpaRepository<Specialization, Long> {
   @Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode")
	Integer getSpecCodeCount(String specCode);
}
