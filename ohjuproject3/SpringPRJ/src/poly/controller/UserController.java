package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 카카오 로그인 해보기

@Controller
public class UserController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="index")
	public String Index() {
		log.info(this.getClass());
		
		return "/index";
	}
	
	// ==================== 로그인 화면을 보여준다.
	@RequestMapping(value="")
	public String Login() {
		log.info(this.getClass());
		return "/Login";
	}
	
			
}
