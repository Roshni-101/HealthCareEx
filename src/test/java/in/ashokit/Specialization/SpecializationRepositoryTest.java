package in.ashokit.Specialization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import in.ashokit.entity.Specialization;
import in.ashokit.repo.SpecializationRepo;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SpecializationRepositoryTest {
	@Autowired
	private SpecializationRepo repo;

	/**
	 * 1. Test Save Operation
	 */
	@Test
	@Order(1)
	public void testSpecCreate() {

		Specialization spec = new Specialization(null, "CRDLS", "Cardiologists",
				" Cardiologists are expected to act as only consultants with respect to cardiovascular disease.");

		 spec=repo.save(spec);
		 assertNotNull(spec.getId(),"spec not created!");
	}

	/**
	 * 2. Test display all Operation
	 */
	@Test
	@Order(2)
	public void testSpecFetchAll() {
		List<Specialization> list= repo.findAll();
		assertNotNull(list);
		assertThat(list.size()>0);
		
	}
}
