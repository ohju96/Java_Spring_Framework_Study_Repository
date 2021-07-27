package poly.service.impl;

import org.springframework.stereotype.Service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import poly.dto.MailDTO;
import poly.service.IMailService;
import poly.util.CmmUtil;

@Service("MailService")
public class MailService implements IMailService {
	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	final String host = "smtp.naver.com";
	final String user = "아이디";
	final String password = "암호";
	
	@Override
	public int doSendMail(MailDTO pDTO) {
		//추후에 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.
		log.info(this.getClass().getName() + ".doSendMail start !");
		// 메일 발송 성공 여부(1 = 성공 / 0 = 실패)
		int res = 1;
		//전달 받은 DTO로부터 데이터 가져오기 , DTO 객체가 메모리에 올라ㅏ지 않아 null이 발생할 수 있기 때문에 에러 방지 차원으로 if문을 사용한다.
		if(pDTO == null) {
			pDTO = new MailDTO();
		}
		
		String toMail = CmmUtil.nvl(pDTO.getToMail()); // 받는 사람

		Properties props = new Properties();
		props.put("mail.stmp.host", host); // javax 외부 라이브러리에 메일 보내는 사람의 정보 설정
		props.put("mail.stmp.auth", "true"); //javax 외부 라이브러리에 메일 보내는 사람 인증 여부 설정

		//네이버 smtp 서버 인증 처리 로직
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
			
			message.setSubject(CmmUtil.nvl(pDTO.getTitle()));
			message.setText(CmmUtil.nvl(pDTO.getContents()));
			
			Transport.send(message);
		} catch (MessagingException e) {
			res = 0;
			log.info("[ERROR]" + this.getClass().getName() + ".doSendmail : " + e);
		} catch (Exception e) {
			res = 0;
			log.info("[ERROR]" + this.getClass().getName() + ".doSendmail : " + e);
		}
		
		log.info(this.getClass().getName() + ".doSendMail end!");
		return res;
	}

}