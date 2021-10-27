package poly.service.impl;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import poly.dto.OcrDTO;
import poly.service.IOcrService;
import poly.util.CmmUtil;

@Service("OcrService")
public class OcrService implements IOcrService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	// 이미지 파일로부터 문자 읽어 오기

	@Override
	public OcrDTO getReadforImageText(OcrDTO pDTO) throws Exception {
		
		log.info(this.getClass().getName() + ".getFoodInfoFromWEB start !");
		
		File imageFile = new File(CmmUtil.nvl(pDTO.getFilePath()) + "//" + CmmUtil.nvl(pDTO.getFileName()));
		
		//Ocr 기술 사용을 위한 테서렉트 플랫폼 객체 생성
		ITesseract instance = new Tesseract();
		
		// OCR 분석에 필요한 기준 데이터, 저장 경로는 물리 경로를 사용한다.
		instance.setDatapath("C:\\tess-data");
		
		instance.setLanguage("eng");
		
		// 이미지 파일로부터 텍스트 읽기
		String result = instance.doOCR(imageFile);
		
		// 읽은 글자를 DTO에 저장하기
		pDTO.setTextFromImage(result);
		
		log.info("result : " + result);
		
		log.info(this.getClass().getName() + ".getFoodInfoFromWEB start !");
		
		return pDTO;
	}

}
