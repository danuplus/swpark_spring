package com.ahsan.validation.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ahsan.validation.domain.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	//@Autowired
	//private Validator userValidator;
	
	@RequestMapping(value = {"/", "new"}, method = RequestMethod.GET)
	public String enroll(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "new";
	}
	
	@RequestMapping(value = "result", method = RequestMethod.POST)
	public String result(@Valid User user, BindingResult bindingResult, Model model) {
		//userValidator.validate(user, bindingResult);
		
		if(bindingResult.hasErrors()) {
			model.addAllAttributes(bindingResult.getModel());
			return "new";
		} else {
			model.addAttribute("user", user);
			return "result";
		}
	}
	
	
}
