package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import poly.dto.OhDTO;
import poly.persistance.mapper.IOhMapper;
import poly.service.IOhService;

@Service("OhService")
public class OhService implements IOhService{
	
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

}
