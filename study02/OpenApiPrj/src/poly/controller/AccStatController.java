package poly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.AccStatDTO;
import poly.service.impl.AccStatService;
import poly.util.CmmUtil;

@Controller("AccStatController")
public class AccStatController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "AccStatService")
	private AccStatService accStatService;
	
	/*교통사고 정보 조회를 위한 Open API
	 *ResponseBody 어노테이션은 jsp로 값을 전달하지 않고, 바로 결과를 제공할 때 사용한다.
	 *일반적으로Map 객체를 통해 return을 하면 Json형태로 변환되서 출력한다.
	 *ResponseBody를 사용하면, ModelMap 객체를 사용할 필요가 없다.
	 *따라서 getAccStatInfo 함수의 파라미터에서 ModelMap을 제외시킨다.
	*/
	
	@RequestMapping(value = "accStat/getAccStatInfo")
	@ResponseBody
	public Map<String, Object> getAccStatInfo(HttpServletRequest request, HttpServletResponse response)
	throws Exception{

		log.info(this.getClass().getName() + ".getFoodInfoFromWEB start !");
		
		// 처리 결과를 전달하기 위한 변수
		Map<String, Object> rMap = new HashMap<String, Object>();
		
		// 교통사고 년월
		String yyyymm = CmmUtil.nvl(request.getParameter("yyyymm"));
		
		//사고 구분
		String a_code = CmmUtil.nvl(request.getParameter("a_code"));
		
		// 교통 사고 정보를 저장하기 위한 파라미터 저장하기
		AccStatDTO pDTO = new AccStatDTO();
		
		pDTO.setYyyymm(yyyymm);
		pDTO.setA_code(a_code);
		
		// 교통사고 조회
		List<AccStatDTO> rList = accStatService.getAccStatInfo(pDTO);
		
		if(rList == null) {
			rList = new ArrayList<AccStatDTO>();
		}
		
		rMap.put("reqYYYYMM", yyyymm); //호출한 파라미터 : yyyymm
		rMap.put("reqAcode", a_code); //호출한 파라미터 : a_code
		rMap.put("recordCnt", rList.size()); // 조회된 교통사고 정보 건수
		rMap.put("res", rList);	// 조회된 교통사고 정보
		
		log.info(this.getClass().getName() + ".getFoodInfoFromWEB end !");
		
		return rMap;
	}
}
