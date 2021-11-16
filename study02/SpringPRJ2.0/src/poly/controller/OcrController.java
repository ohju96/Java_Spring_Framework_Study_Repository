package poly.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.dto.OcrDTO;
import poly.service.IOcrService;
import poly.util.CmmUtil;
import poly.util.DateUtil;
import poly.util.FileUtil;

@Controller("OcrController")
public class OcrController {
	private Logger log = Logger.getLogger(this.getClass());
	// 리소스를 통해 OcrService를 사용할 수 있게 해 준다.
	@Resource(name = "OcrService")
	private IOcrService ocrService;
	
	final private String FILE_UPLOAD_SAVE_PATH = "c:/upload";
	
	@RequestMapping(value = "ocr/imageFileUpload")
	public String Index() {
		log.info(this.getClass().getName() + ".imageFileUpload !");
		return "/ocr/ImageFileUpload";
	}

	//파일 업로드 및 이미지 인식
	@RequestMapping(value = "ocr/getReadforImageText")
	public String getReadforImageText(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam(value = "fileUpload") MultipartFile mf) throws Exception {
		
		log.info(this.getClass().getName() + ".getReadforImageText start !");
		
		//ocr 실행 결과
		String res = "";
		
		//업로드 하는 실제 파일명
		// 다운로드 기능 구현시, 임의로 정의된 파일명을 원래대로 만들어주기 위한 목적
		String originalFileName = mf.getOriginalFilename();
		
		//파일 확장자 가져오기
		String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length()).toLowerCase();
		
		//이미지 파일만 실행되도록 한다.
		if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {
			
			//웹서버에 저장되는 파일 이름
			// 업로드하는 파일 이름에 한글, 특수 문자들이 저장될 수 있기 때문에 강제로 영어와 숫자로 구성된 파일명을 변경해서 저장한다.
			// 리눅스나 유닉스 등 운영체제는 다국어 지원에 취약하기 때문이다.
			String saveFileName = DateUtil.getDateTime("24hhmmss") + "." + ext;
			
			//웹 서버에 업로드한 파일 저장하는 물리적 경로
			String saveFilePath = FileUtil.mkdirForDate(FILE_UPLOAD_SAVE_PATH);
			
			String fullFileInfo = saveFilePath + "/" + saveFileName;
			
			log.info("ext : "  + ext);
			log.info("saveFileName : " + saveFileName);
			log.info("saveFilePath : " + saveFilePath);
			log.info("fullFileInfo : " + fullFileInfo);
			
			//업로드 되는 파일을 서버에 저장
			mf.transferTo(new File(fullFileInfo));
			
			OcrDTO pDTO = new OcrDTO();
			
			pDTO.setFileName(saveFileName);//저장되는 파일명
			pDTO.setFilePath(saveFilePath);//저장되는 경로
			pDTO.setExt(ext);//저장되는 경로
			pDTO.setOrg_file_name(originalFileName);//저장되는 경로
			pDTO.setReg_id("admin");
			OcrDTO rDTO = ocrService.getReadforImageText(pDTO);
			
			if(rDTO == null) {
				rDTO = new OcrDTO();
			}
			
			res = CmmUtil.nvl(rDTO.getTextFromImage());
		}
		
		//크롤링 결과를 넣어주기
		model.addAttribute("res", res);
		
		log.info(this.getClass().getName() + ".getReadforImageText end !");
		
		return "/ocr/TextFromImage";
	}
}
