package com.wmk.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wmk.ex.service.AdminService;
import com.wmk.ex.service.ShopService;
import com.wmk.ex.vo.CartVO;
import com.wmk.ex.vo.CategoryVO;
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
	
	//상품 메인 페이지
	@RequestMapping(value = "/goodsIndex", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "/wmk_goods/goods_index";
	}
	
	
	
	// 관리자 페이지
	@RequestMapping(value = "/admin_goods/index", method = RequestMethod.GET)
	public void getIndex() throws Exception {
	 log.info("get signup");
	 

	}
	
	//상품 등록
	@RequestMapping(value = "/admin_goods/goods/register", method = RequestMethod.GET)
	public void getGoodsRegister(Model model) throws Exception {
	 log.info("get goods register");
	 
	 List<CategoryVO> category = null;
	 category = adminService.category();
	 model.addAttribute("category", JSONArray.fromObject(category));
	}
	
	
	//상품 등록
	@RequestMapping(value = "/admin_goods/goods/register", method = RequestMethod.POST)
	public String postGoodsRegister(GoodsVO vo) throws Exception {
		
		adminService.register(vo);
		
		return "redirect:/admin_goods/list";
	
	}
	
	//상품 목록
	@RequestMapping(value = "/admin_goods/goods/list", method = RequestMethod.GET)
	public void getGoodList(Model model) throws Exception {
		log.info("get goods list");
		
		List<GoodsViewVO> list = adminService.goodslist();
		
		model.addAttribute("list", list);
	
	}
	
	
	// 상품 조회
	@RequestMapping(value = "/admin_goods/goods/view", method = RequestMethod.GET)
	public void getGoodsview(@RequestParam("n") int gdsNum, Model model) throws Exception {
		log.info("get goods view");
		 
		GoodsViewVO goods = adminService.goodsView(gdsNum);
		 
		model.addAttribute("goods", goods);
	}
		
	//상품 수정
	@RequestMapping(value = "/admin_goods/goods/modify", method = RequestMethod.GET)
	public void getGoodsRegister(@RequestParam("n") int gdsNum, Model model) throws Exception {
			
		log.info("get goods modify");
			
		GoodsViewVO goods = adminService.goodsView(gdsNum);
		model.addAttribute("goods", goods);
			
			
		List<CategoryVO> category = null;
		category = adminService.category();
		model.addAttribute("category", JSONArray.fromObject(category));
		
	}
		
	// 상품 수정
	@RequestMapping(value = "/admin_goods/goods/modify", method = RequestMethod.POST)
	public String postGoodsModify(GoodsVO vo) throws Exception {
		log.info("post goods modify");

		adminService.goodsModify(vo);
		 
		return "redirect:/admin_goods/index";
	}
		
	// 상품 삭제
	@RequestMapping(value = "/admin_goods/goods/delete", method = RequestMethod.POST)
	public String postGoodsDelete(@RequestParam("n") int gdsNum) throws Exception {
		log.info("post goods delete");

		adminService.goodsDelete(gdsNum);
		 
		return "redirect:/admin_goods/index";
	}
	
	
	//굿즈 페이지 시작 (정경채)
	
	
	// 상품 등록
	@RequestMapping(value = "/goods_register", method = RequestMethod.GET)
	public String getGoodsRegister2(Model model) throws Exception {
		log.info("get goods register");

		List<CategoryVO> category = null;
		category = adminService.category();
		model.addAttribute("category", JSONArray.fromObject(category));
		
		return "/wmk_goods/goods_register";

	}
	
	// 상품 등록(post)
	@RequestMapping(value = "/goods_register", method = RequestMethod.POST)
	public String postGoodsRegister2(GoodsVO vo) throws Exception {
		
		adminService.register(vo);

		return "redirect:/goodsList";

	}
	
	 
	// 상품 리스트  
	@GetMapping("/goodsList") 
	public String goodsList(Model model) throws Exception {
		
		log.info("goodList");
		
		
		 List<GoodsViewVO> list = adminService.goodslist();
		 
		 model.addAttribute("list", list);
		
		
		return "/wmk_goods/goodsList";

	}
	
	//상품 상세보기
	 @RequestMapping(value = "/goodsView", method = RequestMethod.GET)
	 public String goodsView(@RequestParam("n") int gdsNum, Model model) throws Exception{
		 log.info("get view");
		 
		 GoodsViewVO view = adminService.goodsView(gdsNum);
		 model.addAttribute("view", view);
		 
//			List<CategoryVO> category = null;
//			category = adminService.category();
//			model.addAttribute("category", JSONArray.fromObject(category));
		 
		 
		 
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
	
/*	@GetMapping("/goodsOrder") 
	public String goodsOrder(Model model) {
		
		log.info("goodsOrder");
		return "/wmk_goods/goodsOrder";

	} */
	
/*	@GetMapping("/goodsOrderComplete") 
	public String goodsOrderComplete(Model model) {
		
		log.info("/goodsOrderComplete");
		return "/wmk_goods/goodsOrderComplete";

	} */
	
	
	
	
}
