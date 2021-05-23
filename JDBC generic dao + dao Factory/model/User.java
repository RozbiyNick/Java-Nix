package debates.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@javax.persistence.Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements Entity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NaturalId
	private String username;
	
	private String password;
	
	private String name;
	
	private String description;
	
	private Organisation organisation;
	
}
