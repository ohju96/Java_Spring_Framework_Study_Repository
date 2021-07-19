package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.GasDTO;

@Mapper("GasMapper")
public interface IGasMapper {

	//게시판 리스트
	List<GasDTO> getGasList() throws Exception;
	
}
