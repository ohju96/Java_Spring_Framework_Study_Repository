package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.UserInfoDTO;
import poly.service.IUserInfoService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Controller
public class UserInfoController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "UserInfoService")
	private IUserInfoService userInfoService;

	// 회원가입 화면으로 이동
	@RequestMapping(value = "user/userRegForm")
	public String userRegForm() {
		log.info(this.getClass().getName() + ".user/userRegForm ok !");

		return "/user/UserRegForm";
	}

	@RequestMapping(value = "user/insertUserInfo")
	public String insertUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + ".insertUserInfo start !");

		String msg = "";

		UserInfoDTO pDTO = null;

		try {
			String user_id = CmmUtil.nvl(request.getParameter("user_id"));
			String user_name = CmmUtil.nvl(request.getParameter("user_name"));
			String password = CmmUtil.nvl(request.getParameter("password"));
			String email = CmmUtil.nvl(request.getParameter("email"));
			String addr1 = CmmUtil.nvl(request.getParameter("addr1"));
			String addr2 = CmmUtil.nvl(request.getParameter("addr2"));

			log.info("user_id : " + user_id);
			log.info("user_name : " + user_name);
			log.info("password : " + password);
			log.info("email : " + email);
			log.info("addr1 : " + addr1);
			log.info("addr2 : " + addr2);

			pDTO = new UserInfoDTO();

			pDTO.setUser_id(user_id);
			pDTO.setUser_name(user_name);

			pDTO.setPassword(EncryptUtil.encHashSHA256(password));
			pDTO.setEmail(EncryptUtil.encAES128CBC(email));
			pDTO.setAddr1(addr1);
			pDTO.setAddr2(addr2);

			// 회원가입
			int res = userInfoService.insertUserInfo(pDTO);

			if (res == 1) {
				msg = "회원가입되었습니다.";
			} else if (res == 2) {
				msg = "이미 가입된 이메일 주소입니다.";
			} else {
				msg = "오류로 인해 회원가입이 실패하였습니다.";
			}

		} catch (Exception e) {
			msg = "실패하였습니다. : " + e.toString();
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + ".insetUserInfo end ! ");

			model.addAttribute("msg", msg);

			model.addAttribute("pDTO", pDTO);

			pDTO = null;

		}

		return "/user/Msg";
	}
	
	//로그인을 위한 입력 화면으로 이동
	
	@RequestMapping(value="user/loginForm")
	public String lgoinForm() {
		log.info(this.getClass().getName() + ".user/loginForm ok !");
		return "/user/LoginForm";
	}

	// 로그인 처리 및 결과 알려주는 화면으로 이동
	@RequestMapping(value = "user/getUserLoginCheck")
	public String getUserLoginCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + ".getUserLoginCheck start !!");

		// 로그인 처리 결과를 저장할 변수
		int res = 0;

		// 웹에서 받는 정보를 저장할 변수
		UserInfoDTO pDTO = null;

		try {

			String user_id = CmmUtil.nvl(request.getParameter("user_id"));
			String password = CmmUtil.nvl(request.getParameter("password"));

			// 웹에서 받는 정보를 String 변수에 저장 시작 !!
			// 무조건 웹으로 받은 정보는 DTO에 저장히기 위해 임시로 String 변수에 저장.

			log.info("user_id : " + user_id);
			log.info("password : " + password);

			// 웹에서 받는 정보를 저장할 변수를 메모리에 올리기
			pDTO = new UserInfoDTO();

			pDTO.setUser_id(user_id);
			pDTO.setPassword(EncryptUtil.encHashSHA256(password));

			res = userInfoService.getUserLoginCheck(pDTO);

			if (res == 1) { // 로그인 성공
				session.setAttribute("SS_USER_ID", user_id);
			}

		} catch (Exception e) {
			// 저장이 실패하면 사용자에게 보여줄 메시지
			res = 2;
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + ".insertUserInfo end !");

			model.addAttribute("res", String.valueOf(res));

			pDTO = null;

		}

		return "user/LoginResult";
	}
}
