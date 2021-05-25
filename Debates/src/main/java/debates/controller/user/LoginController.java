package debates.controller.user;

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
public class LoginController{
	
	private final UserService userService;
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(LoginForm form) {
		if(form == null) {
			form = new LoginForm();
		}
		ModelAndView result = new ModelAndView("/login");
		result.addObject("loginForm", form);
		return result;
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin(LoginForm form, HttpSession session) {
		User user = userService
				.getUserWithUsernameAndPassword(User.builder()
						.username(form.getUsername())
						.password(form.getPassword())
						.build());
		if(user == null) {
			ModelAndView modelAndView = new ModelAndView("/login");
            modelAndView.addObject("formWrong", "true");
            return modelAndView;
		}
		session.setAttribute(Constants.CURRENT_USER, user);
        return new ModelAndView("redirect:/me");
	}
}
