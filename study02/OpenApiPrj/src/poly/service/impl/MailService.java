package poly.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.MailDTO;
import poly.service.IMailService;
import poly.util.CmmUtil;

@Service("MailService") // 이 클래스를 서비스로 사용한다고 스프링 프레임워크에 알림과 동시에 이 클래스 이름을 MailService로 지정한다.
public class MailService implements IMailService {
	
	private Logger log = Logger.getLogger(this.getClass()); // 로그를 찍기 위한 메소드
	
	//구글 이메일로 바꾸고 싶으면 호스트를 gmail로 수정해 주면 된다.
	// 아이디에 이차 암호가 걸려 있다면 비밀번호 혹은 아이디 오류가 뜰 수도 있다.
	final String host = "smtp.naver.com";
	final String user = "아이디";
	final String password = "비밀번호";
	// 위에는 본인의 메일 아이디 @ 포함, 암호를 입력하면 되고 깃 허브에 올릴 때 꼭 지워서 올려야한다. 아니면 해킹당한다.
	
	@Override
	public int doSendMail(MailDTO pDTO) {

		log.info(this.getClass().getName() + ".doSendMail Start !");
		
		int res = 1;
		
		if(pDTO == null) {
			pDTO = new MailDTO();
		}
		
		String toMail = CmmUtil.nvl(pDTO.getToMail());
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		
		//서비스 로직
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		// try , catch 문을 통한 예외 처리
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
			
			message.setSubject(CmmUtil.nvl(pDTO.getTitle()));
			
			message.setText(CmmUtil.nvl(pDTO.getContents()));
			
			Transport.send(message);
		} catch (MessagingException e) {
			res = 0;
			log.info("[ERROR]" + this.getClass().getName() + ".dosendMail :" + e);
		} catch (Exception e) {
			res = 0;
			log.info("[ERROR]" + this.getClass().getName() + ".doSendMail :" + e);
		}
		
		//로그를 찍어야 어디까지 실행 됐는지 알 수 있다.
		log.info(this.getClass().getName() + ".doSendMail end ! ");
		
		// res를 리턴해 준다.
		return res;
	}

}
