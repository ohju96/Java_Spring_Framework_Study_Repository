package poly.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.OhDTO;
import poly.persistance.mapper.IOhMapper;
import poly.service.IOhService;

@Service("OhService")
public class OhService implements IOhService{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="OhMapper")
	private IOhMapper OhMapper;
	
// ===================================  로그인  ======================================================================

	@Override
	public int checklogin(OhDTO oDTO) throws Exception {
		
		int res = 0;
		
		OhDTO hDTO = new OhDTO();
		hDTO = OhMapper.checklogin(oDTO);
		
		if(hDTO != null) {
			res = 1;
		}
		return res;
	}

// ===================================  회원가입  =====================================================================

	@Override
	public int checkregister(OhDTO oDTO) throws Exception {

		int res = 0;
		
		OhDTO hDTO = new OhDTO();
		
		hDTO = OhMapper.checkID(oDTO);
		
		if(hDTO == null) {
			log.info("회원가입시작");
			OhMapper.checkregister(oDTO);
			res = 1;
			log.info("회원가입완료");
		}else {
			log.info("이미 가입된 아이디입니다.");
		}
		
		return res;
	}
	
//==================================== 비밀번호변경 ========================================================================

	@Override
	public int updatepwd(OhDTO oDTO) throws Exception {
		
		int res = 0;
		
		OhDTO hDTO = new OhDTO();
		
		hDTO = OhMapper.checkID(oDTO);
		
		log.info(hDTO);
		
		if(hDTO != null) {
			log.info("비밀번호변경시작");
			OhMapper.updatepwd(oDTO);
			log.info("OhService : " + oDTO);
			res = 1;
			log.info("비밀번호변경 완료");
		}else {
			log.info("비밀번호변경 전");
		}
		
		
		return res;
	}
	

}
