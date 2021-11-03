package poly.service;

import java.util.Map;

import poly.dto.AccStatDTO;

public interface IGetAccStatService {

	//open API 서버로부터 전달받는 JSON 데이터
	// 교통사고건수 가져오기
	Map<String, Object> getAccStatForJSON(AccStatDTO pDTO) throws Exception;
	
	//OPenAPI 서버로부터 전달받는 JSON 데이터
	// 야간 교통사고건수 가져오기
	Map<String, Object> getAccStatNightForJSON(AccStatDTO pDTO) throws Exception;
}
