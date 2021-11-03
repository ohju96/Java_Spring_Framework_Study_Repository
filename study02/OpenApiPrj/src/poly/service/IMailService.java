package poly.service;

import poly.dto.MailDTO;

// 인터페이스 메일 서비스
public interface IMailService {
	
	int doSendMail(MailDTO pDTO);

}
