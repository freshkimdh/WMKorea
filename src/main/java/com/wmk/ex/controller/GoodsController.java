package com.wmk.ex.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wmk.ex.service.AdminService;
import com.wmk.ex.service.ShopService;
import com.wmk.ex.vo.CategoryVO;
import com.wmk.ex.vo.FreeBoardVO;
import com.wmk.ex.vo.GoodsVO;
import com.wmk.ex.vo.GoodsViewVO;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;

@Log4j
@Controller
public class GoodsController {
	
	@Inject
	AdminService adminService;
	
	@Inject
	ShopService shopService;
	
	//��ǰ ���� ������
	@RequestMapping(value = "/goodsIndex", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "/wmk_goods/goods_index";
	}
	
	
	
	// ������ ������
	@RequestMapping(value = "/admin_goods/index", method = RequestMethod.GET)
	public void getIndex() throws Exception {
	 log.info("get signup");
	 

	}
	
	//��ǰ ���
	@RequestMapping(value = "/admin_goods/goods/register", method = RequestMethod.GET)
	public void getGoodsRegister(Model model) throws Exception {
	 log.info("get goods register");
	 
	 List<CategoryVO> category = null;
	 category = adminService.category();
	 model.addAttribute("category", JSONArray.fromObject(category));
	}
	
	
	//��ǰ ���
	@RequestMapping(value = "/admin_goods/goods/register", method = RequestMethod.POST)
	public String postGoodsRegister(GoodsVO vo) throws Exception {
		
		adminService.register(vo);
		
		return "redirect:/admin_goods/goods/list";
	
	}
	
	//��ǰ ���
	@RequestMapping(value = "/admin_goods/goods/list", method = RequestMethod.GET)
	public void getGoodList(Model model) throws Exception {
		log.info("get goods list");
		
		List<GoodsViewVO> list = adminService.goodslist();
		
		model.addAttribute("list", list);
	
	}
	
	
	// ��ǰ ��ȸ
	@RequestMapping(value = "/admin_goods/goods/view", method = RequestMethod.GET)
	public void getGoodsview(@RequestParam("n") int gdsNum, Model model) throws Exception {
		log.info("get goods view");
		 
		GoodsViewVO goods = adminService.goodsView(gdsNum);
		 
		model.addAttribute("goods", goods);
	}
		
	//��ǰ ����
	@RequestMapping(value = "/admin_goods/goods/modify", method = RequestMethod.GET)
	public void getGoodsRegister(@RequestParam("n") int gdsNum, Model model) throws Exception {
			
		log.info("get goods modify");
			
		GoodsViewVO goods = adminService.goodsView(gdsNum);
		model.addAttribute("goods", goods);
			
			
		List<CategoryVO> category = null;
		category = adminService.category();
		model.addAttribute("category", JSONArray.fromObject(category));
		
	}		
	
	
	// ��ǰ ���
	@RequestMapping(value = "/admin/goods_register", method = RequestMethod.GET)
	public String getGoodsRegister2(Model model) throws Exception {
		log.info("get goods register");

		List<CategoryVO> category = null;
		category = adminService.category();
		model.addAttribute("category", JSONArray.fromObject(category));
		
		return "/wmk_goods/goods_register";

	}
	
	// ��ǰ ���(post)
	@RequestMapping(value = "/admin/goods_register", method = RequestMethod.POST)
	public String postGoodsRegister2(GoodsVO vo) throws Exception {
		
		adminService.register(vo);

		return "redirect:/goodsList";

	}
	
	 
	// ��ǰ ����Ʈ  
	@GetMapping("/goodsList") 
	public String goodsList(Model model) throws Exception {
		
		log.info("goodList");
		
		
		 List<GoodsViewVO> list = adminService.goodslist();
		 
		 model.addAttribute("list", list);
		
		
		return "/wmk_goods/goodsList";

	}
	
	//��ǰ �󼼺���
	 @RequestMapping(value = "/goodsView", method = RequestMethod.GET)
	 public String goodsView(@RequestParam("n") int gdsNum, Model model) throws Exception{
		 log.info("get view");
		 
		 GoodsViewVO view = adminService.goodsView(gdsNum);
		 model.addAttribute("view", view);
		 
		 return "/wmk_goods/goodsView";
	 }
	 
	
	@GetMapping("/goodsDetails") 
	public String goodsDetails(Model model) {
		
		log.info("goodsDetails");
		return "/wmk_goods/goodsDetails";

	}
	
	@GetMapping("/goodsDetails2") 
	public String goodsDetails2(Model model) {
		
		log.info("goodsDetails");
		return "/wmk_goods/goodsDetails2";

	}
	
	//상품 삭제
	@GetMapping("/admin/goodsDelete") 
	public String goodsDelete(GoodsViewVO goodsViewVO) throws Exception {
		
		log.info("goodsDelete...");
		adminService.goodsDelete(goodsViewVO);
		   
	    return "redirect:goodsList";
	
	}
	
	
	
}
