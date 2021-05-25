package debates.model;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@javax.persistence.Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="discussion")
public class Discussion implements Entity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Organisation organisation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User author;
	
	private String description;
	
	@OneToOne
	private Argument question;
}
