package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.persistance.mapper.IGasMapper;
import poly.service.IGasService;

@Service("GasService")
public class GasService implements IGasService{
	
	@Resource(name="GasMapper")
	private IGasMapper gasMapper;

}
