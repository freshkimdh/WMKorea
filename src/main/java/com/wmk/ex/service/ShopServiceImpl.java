package com.wmk.ex.service;


import java.util.List;
import org.springframework.stereotype.Service;

import com.wmk.ex.mapper.AdminMapper;
import com.wmk.ex.mapper.ShopMapper;
import com.wmk.ex.vo.CategoryVO;
import com.wmk.ex.vo.GoodsVO;
import com.wmk.ex.vo.GoodsViewVO;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor
@Log4j 
@Service
public class ShopServiceImpl implements ShopService {
	
	
	private ShopMapper shopMapper;

	@Override
	public List<GoodsViewVO> list(int cateCode) throws Exception {
		return shopMapper.list(cateCode);
	}
	

	


}



