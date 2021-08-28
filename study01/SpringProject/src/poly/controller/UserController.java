package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//스프링 프레임워크에게 컨트롤러로 사용할 것임을 알려준다.
@Controller
public class UserController {

	// 로그를 찍기 위한 메소드.
	private Logger log = Logger.getLogger(this.getClass());
	
}
