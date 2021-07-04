package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.persistance.mapper.IOhMapper;
import poly.service.IOhService;

@Service("OhService")
public class OhService implements IOhService{
	
	@Resource(name="OhMapper")
	private IOhMapper ohMapper;

}
