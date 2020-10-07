package com.wmk.ex;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wmk.ex.service.ShopService;
import com.wmk.ex.vo.GoodsViewVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
//@RequestMapping("/shop/*")
public class ShopController {
 
	 @Inject
	 ShopService service;
    
	 // ī�װ��� ��ǰ ����Ʈ
	 @RequestMapping(value = "/shop/list", method = RequestMethod.GET)
	 public String getList(@RequestParam("c") int cateCode, @RequestParam("l") int level, Model model) throws Exception {
		  log.info("get llist");
		  
		  List<GoodsViewVO> list = null;
		  list = service.list(cateCode, level);
		 
		  model.addAttribute("list", list);
		  
		  return "/admin_goods/shop/list";
	  
	 }
	 
	 @RequestMapping(value = "/shop/view", method = RequestMethod.GET)
	 public String getView(@RequestParam("n") int gdsNum, Model model) throws Exception{
		 log.info("get view");
		 
		 GoodsViewVO view = service.goodsView(gdsNum);
		 model.addAttribute("view", view);
		 
		 return "/admin_goods/shop/view";
	 }
	 

	 
	 
	 
}