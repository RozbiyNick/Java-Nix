package debates.controller.user;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import debates.Constants;
import debates.model.User;
import debates.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {
	
	private final UserService userService;
	
	@RequestMapping(path="/signup", method = RequestMethod.GET)
	public ModelAndView showSignup(SignupForm form) {
		if(form == null) {
			form = new SignupForm();
		}
		ModelAndView result = new ModelAndView("/signup");
		result.addObject("signupForm", form);
		return result;
	}
	
	@RequestMapping(path="/signup", method = RequestMethod.POST)
	public ModelAndView doSignup(SignupForm form, HttpSession session) {
		ArrayList<String> errors = userService.signupDataErrors(form);
		if (errors.isEmpty()) {
			User newUser = User.builder()
					.username(form.getUsername())
					.password(form.getPassword())
					.build();
			newUser = userService.createUser(newUser);
			session.setAttribute(Constants.CURRENT_USER, newUser);
			return new ModelAndView("/me");
		}
		else {
			ModelAndView modelAndView = new ModelAndView("/signup");
            modelAndView.addObject("errors", errors);
            modelAndView.addObject(form);
            return modelAndView;
		}
	}
}
