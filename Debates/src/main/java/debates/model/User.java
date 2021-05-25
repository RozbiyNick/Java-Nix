package debates.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@javax.persistence.Entity(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User implements Entity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NaturalId
	private String username;
	
	private String password;
	
	private String name;
	
	private String description;
	
	@ManyToOne
	private Organisation organisation;
	
}
