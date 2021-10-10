package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.MailDTO;
import poly.dto.UserInfoDTO;
import poly.persistance.mapper.UserInfoMapper;
import poly.service.IMailService;
import poly.service.IUserInfoService;
import poly.util.CmmUtil;
import poly.util.DateUtil;
import poly.util.EncryptUtil;

//Service 단을 만들고 이름을 넣어줘야 한다.
@Service("UserInfoService")
public class UserInfoService implements IUserInfoService {
	
	@Resource(name="UserInfoMapper")
	private UserInfoMapper userInfoMapper;
	
	@Resource(name = "MailService")
	private IMailService mailService;
	
	@Override
	public int insertUserInfo(UserInfoDTO pDTO) throws Exception {

		int res = 0;
		
		if (pDTO==null) {
			pDTO = new UserInfoDTO();
		}
		
		UserInfoDTO rDTO = userInfoMapper.getUserExists(pDTO);
		
		if (rDTO == null) {
			rDTO = new UserInfoDTO();
		}
		
		if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
			
			res = 2;
		} else {
			int success = userInfoMapper.InsertUserInfo(pDTO);
	
				res = 1;
				
				if (success > 0) {
					
					//메일 발송 로직 추가
					MailDTO mDTO = new MailDTO();
					
					mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));
					
					mDTO.setTitle("회원가입을 축하드립니다.");
					
					mDTO.setContents(CmmUtil.nvl(pDTO.getUser_name()) + "님의 회원가입을 진심으로 축하드립니다.");
					
					mailService.doSendMail(mDTO);
					
				
			} else {
				res = 0;
			}
		}
		
		return res;
	}

	@Override
	public int getUserLoginCheck(UserInfoDTO pDTO) throws Exception {

		// 로그인 성공 : 1, 실패 : 0
		int res = 0;
		
		// 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 mapper 호출하기
		UserInfoDTO rDTO = userInfoMapper.getUserLoginCheck(pDTO);
		
		if(rDTO ==null) {
			rDTO = new UserInfoDTO();
			
		}
		
		// #################################################################
		//						로그인 성공 여부 체크 시작 !!
		// #################################################################
		
		if(CmmUtil.nvl(rDTO.getUser_id()).length()>0) {
			res = 1;
			
			MailDTO mDTO = new MailDTO();
			
			mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(rDTO.getEmail())));
			
			mDTO.setTitle("로그인 알림 !");
			
			mDTO.setContents(DateUtil.getDateTime("yyyy.MM.dd 24h:mm:ss") + "에" + CmmUtil.nvl(rDTO.getUser_name()) + "님이 로그인하였습니다.");
			
			mailService.doSendMail(mDTO);
		}
		
		
		return res;
	}

}
