package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.NlpDTO;
import poly.service.INlpService;
import poly.util.CmmUtil;

@Controller("NlpController")
public class NlpController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "NlpService")
	private INlpService nlpService;
	
	//문자 입력창
	@RequestMapping(value = "nlp/inputForm")
	public String inputForm() {
		
		log.info(this.getClass().getName() + ".inputForm !");
		
		return "/nlp/inputForm";
	}
	
	//긍정, 부정 분석하기
	@RequestMapping(value = "nlp/wordAnalysis")
	public String wordAnalysis(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		
		log.info(this.getClass().getName() + ".wordAnalysis start !");
		
		String res = "";
		
		//분석할 문장
		String text_message = CmmUtil.nvl(request.getParameter("text_message"));
		
		NlpDTO pDTO = new NlpDTO();
		
		pDTO.setWord(text_message);
		
		int point = nlpService.preProcessWordAnalysisForMind(pDTO);
		
		if(point < 0) {
			res = "\"" + text_message + "\" 문장의 분석결과는 " + point + "로 부정적인 결과가 나왔습니다.";
			
		}else if (point == 0) {
			res = "\"" + text_message + "\" 문장의 분석결과는 데이터 사전에 존재하지 않아 분석이 불가능합니다.";
			
		}else {
			res = "\"" + text_message + "\" 문장의 분석결과는" + point + "로 긍정적인 결과가 나왔습니다.";
			
		}
		
		//jsp 단으로 넘겨준다.
		model.addAttribute("res", res);
		
		log.info(this.getClass().getName() + ".wordAnalysis end !");
		
		return "/nlp/wordAnalysis";
	}
}
