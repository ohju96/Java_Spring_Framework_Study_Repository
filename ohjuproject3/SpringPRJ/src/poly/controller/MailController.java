package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.MailDTO;
import poly.service.IMailService;
import poly.util.CmmUtil;

@Controller //이 클래스가 Controller로 사용됨을 Spring Framework에 알린다.
public class MailController {
	
	private Logger log = Logger.getLogger(this.getClass()); //log.info()를 찍어 상태를 확인하기 위한 메소드
	
	@Resource(name = "MailService") //주입하려고 하는 이름이 일치하는 객체를 자동으로 주입한다.
	private IMailService mailService;
	
	//메일 발송하는 로직 구현
	@RequestMapping(value = "mail/sendMail", method = RequestMethod.GET)
	public String sendMail(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		//로그 찍기
		log.info(this.getClass().getName() + "mail.sendMail start!");
		
		//웹 url로부터 전달받는 값들
		String toMail = CmmUtil.nvl(request.getParameter("toMail"));//받는 사람
		String title = CmmUtil.nvl(request.getParameter("title")); //제목
		String contents = CmmUtil.nvl(request.getParameter("contents"));// 내용 

		// 메일 발송할 정보를 넣기 위한 DTO 객체 생성하기
		MailDTO pDTO = new MailDTO();
		
		pDTO.setToMail(toMail);//받는 사람을 DTO 저장
		pDTO.setTitle(title);//제목을 DTO 저장
		pDTO.setContents(contents);// 내용을 DTO 저장
		
		//메일 발송하기
		int res = mailService.doSendMail(pDTO);
		
		if(res==1) {//메일 발송 성공
			log.info(this.getClass().getName() + "mail.sendMail success!");
		}else {//메일 발송 실패
			log.info(this.getClass().getName() + "mail.sendMail fail!");
		}
		
		//메일 발송 결과를 jsp에 전달하기 ( 데이터 전달시, 숫자보다 문자열이 컨트롤하기 편리하기 때문에 강제로 숫자를 문자로 변환한다. )
		model.addAttribute("res", String.valueOf(res));
		//로그 찍기 
		log.info(this.getClass().getName() + "mail.sendMail end!");
		//함수 처리가 끝나고 보여줄 jsp 파일명 (/WEB-INF/view//mail/sendMailResult.jsp)
		return "/mail/sendMailResult";
	}
}


