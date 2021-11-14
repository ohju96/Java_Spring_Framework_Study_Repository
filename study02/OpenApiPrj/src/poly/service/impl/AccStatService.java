package poly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.AccStatDTO;
import poly.persistance.mapper.IAccStatMapper;
import poly.service.IAccStatService;

//서비스를 알려주고 네이밍을 붙여준다.
@Service("AccStatService")
public class AccStatService implements IAccStatService {

	@Resource(name ="AccStatMapper")
	private IAccStatMapper accStatMapper;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	//교통사고 정보 가져오기
	@Override
	public List<AccStatDTO> getAccStatInfo(AccStatDTO pDTO) throws Exception {

		log.info(this.getClass().getName() + ".geAccStatInfo start !");
		
		//교통 사고 조회
		List<AccStatDTO> rList = accStatMapper.getAccStatInfo(pDTO);
		
		//참조형 변수는 무조건 오류 방지를 위해 널처리를 한다.
		if(rList ==null) {
			rList = new ArrayList<AccStatDTO>();
		}
		
		log.info(this.getClass().getName() + ".getAccStatInfo end !");
		
		return rList;
	}

}
