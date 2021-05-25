package debates.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import debates.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@RequestMapping(path="/me", method = RequestMethod.GET)
	public ModelAndView showMe(HttpSession session) {
		System.out.println();
		return new ModelAndView("/my_profile");
	}
}
