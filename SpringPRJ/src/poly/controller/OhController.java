package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.OhDTO;
import poly.service.IOhService;
import poly.service.impl.OhService;


@Controller
public class OhController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@Resource(name = "OhSevice")
	private IOhService OhService;
	
	@RequestMapping(value="index")
	public String Index() {
		log.info(this.getClass());
		
		return "/index";
	}
	
//======================================  로그인  ========================================================================

	@RequestMapping(value="Oh/login")
	public String login() {
		log.info("로그인 페이지 출력"	);
		
		return "/login";
	}
	
	@RequestMapping(value="/checklogin")
	@ResponseBody
	public String checklogin(HttpServletRequest request) throws Exception{
	
		log.info("로그인 시작");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		log.info(id);
		log.info(pwd);
		log.info("로그인");
		
		OhDTO oDTO = new OhDTO();
		oDTO.setOh_id(id);
		oDTO.setOh_pwd(pwd);
		
		log.info("ID :" + oDTO.getOh_id());
		log.info("PWD : " + oDTO.getOh_pwd());
		
		int res = OhService.checklogin(oDTO);
		
		String result = "";	
		
		if ( res == 0) {
			result = "fail";
		} else {
			result = "success";
		}
		
		return result;
	}
	
//======================================  회원가입  ==============================================================================
}
