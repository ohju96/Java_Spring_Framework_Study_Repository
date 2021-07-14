package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.OhDTO;

@Mapper("OhMapper")
public interface IOhMapper {

	//게시판 리스트
	List<OhDTO> getOhList() throws Exception;

	OhDTO checklogin(OhDTO oDTO) throws Exception;

	OhDTO checkID(OhDTO oDTO) throws Exception;

	void checkregister(OhDTO oDTO) throws Exception;

	OhDTO updatepwd(OhDTO oDTO) throws Exception;

	
}
