package com.wmk.ex.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.wmk.ex.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Component("fileUtils")   // 사용된 클래스를 자동으로 빈에 등록해주는 에노테이션
public class FileUtils {
	
	//파일 저장
	private static final String filePath = "C:\\Users\\Administrator\\Documents\\workspace-sts-3.9.13.RELEASE\\WMKPracticeSun\\src\\main\\webapp\\resources\\img\\"; // 파일이 저장될 위치
	
	
	public List<Map<String, Object>> parseInsertFileInfo(BoardVO boardVO, 
			MultipartHttpServletRequest mpRequest) throws Exception{
		
		
		/*
			Iterator은 데이터들의 집합체 에서 컬렉션으로부터 정보를 얻어올 수 있는 인터페이스입니다.
			List나 배열은 순차적으로 데이터의 접근이 가능하지만, Map등의 클래스들은 순차적으로 접근할 수가 없습니다.
			Iterator을 이용하여 Map에 있는 데이터들을 while문을 이용하여 순차적으로 접근합니다.
		*/
		
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile;
		String originalFileName;
		String originalFileExtension;
		String storedFileName;
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> listMap = null;
		
		int bId = boardVO.getbId();
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				listMap.put("bId", bId);
				listMap.put("ORIGINAL_FILE_NAME", originalFileName);
				
				log.info("originalFileName=" + originalFileName);
				
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
			}

		}
		return list;
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
