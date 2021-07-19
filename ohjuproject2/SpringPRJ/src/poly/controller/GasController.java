package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class GasController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	
	//################  로그인 페이지  ###################
	@RequestMapping(value="login")
	public String Index() {
		log.info(this.getClass());
		
		return "/login";	
	}
	
	//################  회원가입 페이지  ###################
	@RequestMapping(value="register")
	public String register() {
		log.info(this.getClass());
		
		return "/register";	
	}
	
	//################  비밀번호 찾기 페이지  ###################	
	@RequestMapping(value="forgotpassword")
	public String forgotpassword() {
		log.info(this.getClass());
		
		return "/forgotpassword";	
	}
}
