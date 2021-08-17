package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 컨트롤러 나누기
@Controller
public class UserController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="index")
	public String Index() {
		log.info(this.getClass());
		
		return "/index";
	}
			
}
