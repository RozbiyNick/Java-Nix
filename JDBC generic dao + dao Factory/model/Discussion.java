package debates.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@javax.persistence.Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discussion implements Entity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Organisation organisation;
	
	private User author;
	
	private String description;
	
	private Argument question;
}
