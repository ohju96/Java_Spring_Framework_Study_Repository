package poly.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.service.IMailService;

@Controller //이 클래스가 Controller로 사용됨을 Spring Framework에 알린다.
public class MailController {
	
	private Logger log = Logger.getLogger(this.getClass()); //log.info()를 찍어 상태를 확인하기 위한 메소드
	
	@Resource(name = "MailService") //주입하려고 하는 이름이 일치하는 객체를 자동으로 주입한다.
	private IMailService mailService;

}


