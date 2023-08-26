package funix.huutt.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import funix.huutt.prj.entity.Test;
import funix.huutt.prj.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;
	
	@GetMapping("/view")
	public String displayTest(Model model) {
		
		List<Test> tests = testService.displayTests();
		
		model.addAttribute("tests", tests);
		
		return "display-test";
		
	}
	
	@GetMapping("/inputTest")
	public String saveTest(@ModelAttribute("testValue") String testValue, Model model) {
		
		
		if(testValue.length() > 0 ) {
		
			Test value = new Test();
			
			value.setText(testValue);
			
			testService.save(value);
		}
		
		List<Test> tests = testService.displayTests();
		
		model.addAttribute("tests", tests);
		
		return "redirect:/test/view";
	}
}
