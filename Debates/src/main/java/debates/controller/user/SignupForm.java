package debates.controller.user;

import lombok.Data;

@Data
public class SignupForm {
	private String username;
	private String password;
	private String password2;
}
