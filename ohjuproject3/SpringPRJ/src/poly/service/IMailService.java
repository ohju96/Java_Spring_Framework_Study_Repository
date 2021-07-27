package poly.service;

import poly.dto.MailDTO;

public interface IMailService {

	// Service에서 최종 에러를 처리할 것이라 예외처리를 따로 하지 않는다.
	int doSendMail(MailDTO pDTO);

}