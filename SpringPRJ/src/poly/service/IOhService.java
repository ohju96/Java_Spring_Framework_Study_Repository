package poly.service;

import poly.dto.OhDTO;

public interface IOhService {

	int checklogin(OhDTO oDTO) throws Exception;

	int checkregister(OhDTO oDTO) throws Exception;
	
}
