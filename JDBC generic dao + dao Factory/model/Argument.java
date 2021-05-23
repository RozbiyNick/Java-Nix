package debates.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import debates.model.status.ArgumentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
public class Argument implements Entity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String text;

	@Enumerated(EnumType.STRING)
	private ArgumentStatus status;
	
	private Argument parent;
	
	public Argument(Long id) {
		this.id = id;
	}
}
