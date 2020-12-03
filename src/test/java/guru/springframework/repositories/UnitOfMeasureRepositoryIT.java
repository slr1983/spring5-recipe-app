package guru.springframework.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testFindByDescription() {

		Optional<UnitOfMeasure> optional = unitOfMeasureRepository.findByDescription("Pinch");

		assertEquals("Pinch", optional.get().getUnitOfMeasure());
	}

	@Test
	public final void testFindByDescriptionCup() {

		Optional<UnitOfMeasure> optional = unitOfMeasureRepository.findByDescription("Cup");

		assertEquals("Cup", optional.get().getUnitOfMeasure());
	}

}
