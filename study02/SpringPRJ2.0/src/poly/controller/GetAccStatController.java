package poly.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.AccStatDTO;
import poly.service.IGetAccStatService;
import poly.util.CmmUtil;

//컨트롤러 마다 이름을 정해줘야 한다.
@Controller("GetAccStatController")
public class GetAccStatController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "GetAccStatService")
	private IGetAccStatService getAccStatService;
	
	//JSON 결과를 받아오기 위한 함수
	@RequestMapping(value = "AccStat/getAccStatForJSON")
	public String getAccStatForJSON(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	throws Exception {
		
		log.info(this.getClass().getName() + ".getAccStatForJSON start !");
		
		//OpenAPI url
		String url = "http://localhost:9010/accStat/getAccStatInfo.do?1=1";
		//OpenAPI용 파라미터 받아오기
		String yyyymm = CmmUtil.nvl(request.getParameter("yyyymm")); //사고 년월
		String a_code = CmmUtil.nvl(request.getParameter("a_code")); // 사고 구분
		
		//yyyymm에 값이 있다면
		if(yyyymm.length() > 0) {
			url += "&yyyymm=" + yyyymm;
		}
		
		// a_code에 값이 있다면
		if(a_code.length() > 0) {
			url += "&a_code=" + a_code;
		}
		
		log.info("url : " + url);
		
		//OpenAPI 호출을 위한 파라미터 저장하기
		AccStatDTO pDTO = new AccStatDTO();
		pDTO.setUrl(url);
		
		// JSON으로부터 받은 결과를 자바에서 처리가능한 데이터 구조로 변경
		Map<String, Object> rMap = getAccStatService.getAccStatForJSON(pDTO);
		
		//JSON으로부터 받은 결과를 자바에서 처리 가능한 데이터 구조로 변경한 변수를 JSP에 전달
		model.addAttribute("rMap", rMap);
		
		log.info(this.getClass().getName() + ".getAccStatForJSON end !");
		
		return "/AccStat/getAccStatForJSON";
	}
	
	//JSON 결과를 받아오기 위한 함수
	@RequestMapping(value = "AccStat/getAccStatNightForJSON")
	@ResponseBody
	public Map<String, Object> getAccStatNightForJSON(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		log.info(this.getClass().getName() + ".getAccStatNightForJSON start !");
		
		// OpenAPI url
		String url = "http://localhost:9010/accStat/getAccStatInfo.do?1=1";
		
		// OpenAPI용 파라미터 받아오기
		String yyyymm = CmmUtil.nvl(request.getParameter("yyyymm")); //사고 년월
		String a_code = CmmUtil.nvl(request.getParameter("a_code")); // 사고 구분
		
		//yyyymm에 값이 있다면
		if(yyyymm.length() > 0) {
			url += "&yyyymm=" + yyyymm;
		}
		
		// a_code에 값이 있다면
		if(a_code.length() > 0) {
			url += "&a_code=" + a_code;
		}
		
		log.info("url : " + url);
		
		//OpenAPI 호출을 위한 파라미터 저장하기
		AccStatDTO pDTO = new AccStatDTO();
		pDTO.setUrl(url);
		
		//JSON으로부터 받은 결과를 자바에서 처리가능한 데이터 구조로 변경
		Map<String, Object> rMap = getAccStatService.getAccStatNightForJSON(pDTO);
		
		if(rMap == null) {
			rMap = new HashMap<String, Object>();
		}
		
		log.info(this.getClass().getName() + ".getAccStatNightForJSON end!");
		
		return rMap;
	}
	
	
	@RequestMapping(value = "AccStat/accStatForAjax")
	public String accStatForAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model)
		throws Exception {
		log.info(this.getClass().getName() + ".accStatForAjax start !");
		
		log.info(this.getClass().getName() + ".accStatForAjax end !");
		
		return "/AccStat/accStatForAjax";
	}
}
