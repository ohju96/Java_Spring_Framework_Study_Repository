package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 카카오 로그인 해보기
// 클래시스로 빌드 패스 변경
// 빌드 패스 변경시 파일 사라지는 게 맞음.

@Controller
public class UserController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	// ========================== 인덱스 페이지 화면을 보여준다.
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
	
	// ==================== 로그아웃 화면을 보여준다.
	@RequestMapping(value="")
	public String Logout() {
		log.info(this.getClass());
		return "/Logout";
	}
			
}
