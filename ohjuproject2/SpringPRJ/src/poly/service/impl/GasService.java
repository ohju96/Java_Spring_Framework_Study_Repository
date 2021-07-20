package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.GasDTO;
import poly.persistance.mapper.IGasMapper;
import poly.service.IGasService;

@Service("GasService")
public class GasService implements IGasService{
	
	@Resource(name="GasMapper")
	private IGasMapper GasMapper;

	@Override
	public int gaslogin(GasDTO gDTO) throws Exception {
		
		int res = 0;
		
		GasDTO aDTO = new GasDTO();
		aDTO = GasMapper.gaslogin(gDTO);
		
		return res;
	}

}
