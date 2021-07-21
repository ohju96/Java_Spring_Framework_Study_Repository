package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.GasDTO;
import poly.service.IGasService;
import poly.service.impl.GasService;


@Controller
public class GasController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="GasService")
	private IGasService GasService;
	
	
	//################  로그인 페이지  ###################
	@RequestMapping(value="login")
	public String Index() {
		log.info(this.getClass());
		
		return "/login";	
	}
	
	@RequestMapping(value="/index.do")
	@ResponseBody
	public String gaslogin(HttpServletRequest request) throws Exception{
		
		log.info("로그인 시작");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		log.info(id);
		log.info(pwd);

		GasDTO gDTO = new GasDTO();
		
		gDTO.setUser_id(id);
		gDTO.setUser_pwd(pwd);
		
		log.info("ID : " + gDTO.getUser_id());
		log.info("PWD : " + gDTO.getUser_pwd());
		
		int res = GasService.gaslogin(gDTO);
		
		return "/index";
	}
	
	
	/*
	 * //################ 회원가입 페이지 ###################
	 * 
	 * @RequestMapping(value="register") public String register() {
	 * log.info(this.getClass());
	 * 
	 * return "/register"; }
	 * 
	 * //################ 비밀번호 찾기 페이지 ###################
	 * 
	 * @RequestMapping(value="forgotpassword") public String forgotpassword() {
	 * log.info(this.getClass());
	 * 
	 * return "/forgotpassword"; }
	 */
}
