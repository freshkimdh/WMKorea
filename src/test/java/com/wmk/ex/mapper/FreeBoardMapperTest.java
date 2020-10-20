package com.wmk.ex.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.wmk.ex.vo.FreeBoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class FreeBoardMapperTest {

	@Setter(onMethod_ = @Autowired) 
	private FreeBoardMapper freeBoardMapper; 
	
	@Test
	public void testFreeBoardMapper() {
		log.info(freeBoardMapper);
	} 
	
	@Test
	public void testFeeBoardMapperList() throws Exception {
		List<FreeBoardVO> list = freeBoardMapper.getList();
		
		for(FreeBoardVO freeBoardVO : list) {
			log.info(freeBoardVO.getfContent());
			log.info(freeBoardVO.getfId());
			log.info(freeBoardVO.getfGroup());
		}		
	} 
}
