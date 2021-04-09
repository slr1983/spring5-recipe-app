package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnitOfMeasure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnitOfMeasure() {
		return description;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.description = unitOfMeasure;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UnitOfMeasure() {
	}

	public UnitOfMeasure getUnitOfMeasureCopy() {
		UnitOfMeasure unitOfMeasureCopy = new UnitOfMeasure();
		unitOfMeasureCopy.id = this.id;
		unitOfMeasureCopy.description = this.description;
		return unitOfMeasureCopy;
	}

}