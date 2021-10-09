package poly.service;

import poly.dto.UserInfoDTO;

public interface IUserInfoService {
	
	//회원가입 정보
	int insertUserInfo(UserInfoDTO pDTO) throws Exception;
	
	//로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
	int getUserLoginCheck(UserInfoDTO pDTO) throws Exception;

}
