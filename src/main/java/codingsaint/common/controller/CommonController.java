package codingsaint.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
	@RequestMapping("login")
	public ModelAndView  login(@RequestParam(value = "error", required = false) String error){
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		model.setViewName("login");
	
		return model;
		
	}
	@RequestMapping("/")
	public String index(){
		System.out.println("HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		return "index";
	}

}
