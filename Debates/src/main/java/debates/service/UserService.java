package debates.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import debates.controller.user.SignupForm;
import debates.dao.UserDao;
import debates.model.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	
	private final UserDao userDao;
	
	@Transactional
	public User getUserWithUsernameAndPassword(User user) {
		User u = userDao.findByUsername(user.getUsername());
		if(u == null) {
			return null;
		}
		if(!user.getPassword().equals(u.getPassword())) {
			return null;
		}
		return u;
	}
	
	@Transactional
	public User createUser(User user) {
		return userDao.create(user);
	}
	
	@Transactional
	public ArrayList<String> signupDataErrors(SignupForm form){
		ArrayList<String> errors = new ArrayList<String>();
		if (!loginIsValid(form.getUsername())) errors.add("Login is incorrect. It must contain at least 4 symbols.");
		if (!loginIsFree(form.getUsername())) errors.add("Login is busy.");
		if (!passwordIsValid(form.getPassword())) errors.add("Password is not valid. It must contain at least 4 symbols.");
		if (!form.getPassword().equals(form.getPassword2())) errors.add("Passwords must match.");
		return errors;
	}
	

	private boolean loginIsValid(String login) {
		if(login == null) {
			return false;
		}
		return (login.length() >= 4) ? true:false;
	}
	
	private boolean passwordIsValid(String password) {
		if(password == null) {
			return false;
		}
		return (password.length() >= 4) ? true:false;
	}
	
	@Transactional
	private boolean loginIsFree(String login){
        return (userDao.findByUsername(login) == null) ? true : false;
	}
}
