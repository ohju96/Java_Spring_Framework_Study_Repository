package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.OhDTO;
import poly.service.IOhService;


@Controller
public class OhController {
	

	@Resource(name = "OhService")
	private IOhService OhService;
	
	private Logger log = Logger.getLogger(this.getClass());
		
	@RequestMapping(value="index")
	public String Index() {
		log.info(this.getClass());
		
		return "/index";
	}
	
//======================================  로그인  ========================================================================

	@RequestMapping(value="login")
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
		log.info(res);
		
		String result = "";	
		
		
		if ( res == 0) {
			result = "fail";
		} else if (res == 1){
			result = "success";

		}
		
		return result;
	}
	
//======================================  회원가입  ==============================================================================
}
