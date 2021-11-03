package poly.dto;
// DB 정보를 받아온다.
public class MailDTO {
	
	private String toMail; // 받는 사람
	private String title; // 제목
	private String contents; // 내용
	
	public String getToMail() {
		return toMail;
	}
	public void setToMail(String toMail) {
		this.toMail = toMail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	

}
