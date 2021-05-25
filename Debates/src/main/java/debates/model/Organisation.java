package debates.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@javax.persistence.Entity(name="organisation")
@AllArgsConstructor
@NoArgsConstructor
@Table(name="organisation")
public class Organisation implements Entity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NaturalId
	private String name;
	
	private String description;
}
