package poly.persistance.mapper;

import config.Mapper;
import poly.dto.OcrDTO;

@Mapper("OcrMapper")
public interface IOcrMapper {

	int InsertOcrInfo(OcrDTO pDTO) throws Exception;

	
	
}
