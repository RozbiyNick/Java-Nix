package debates.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String login;
	
	private String password;

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
