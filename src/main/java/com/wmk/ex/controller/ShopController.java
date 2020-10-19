package com.wmk.ex.controller;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wmk.ex.service.ShopService;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.CartListVO;
import com.wmk.ex.vo.CartVO;
import com.wmk.ex.vo.CommentListVO;
import com.wmk.ex.vo.CommentVO;
import com.wmk.ex.vo.GoodsViewVO;
import com.wmk.ex.vo.MemberVO;
import com.wmk.ex.vo.OrderDetailVO;
import com.wmk.ex.vo.OrderListVO;
import com.wmk.ex.vo.OrderVO;
import com.wmk.ex.vo.UserVO;

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
		 
//		 List<CommentListVO> reply = service.commentList(gdsNum);
//		 model.addAttribute("reply", reply);
		 
		 return "/admin_goods/shop/view";
	 }
	 
	 
	 
//	// ��ǰ ��ȸ - �Ұ�(���) �ۼ�
//	 @RequestMapping(value = "/shop/view", method = RequestMethod.POST)
//	 public String registReply(CommentVO comment, HttpSession session) throws Exception {
//		 log.info("regist reply");
//		 
//		 MemberVO member = (MemberVO)session.getAttribute("member");
//		 comment.setUserId(member.getUserId());
//		 
//		 service.registReply(comment);
//	  
//	  
//	  return "redirect:/shop/view?n=" + comment.getGdsNum();
//	 }
	 
	// ��ǰ �Ұ�(���) �ۼ�
	 @ResponseBody
	 @RequestMapping(value = "/shop/view/registReply", method = RequestMethod.POST)
	 public void registReply(CommentVO comment, HttpSession session) throws Exception {
	  log.info("regist reply");
	  
	  MemberVO member = (MemberVO)session.getAttribute("member");
	  comment.setUserId(member.getUserId());
	  
	  service.registReply(comment);
	  

	 } 
	 
	 
	 // ��ǰ �Ұ�(���) ���
	 @ResponseBody
	 @RequestMapping(value = "/shop/view/replyList", method = RequestMethod.GET)
	 public List<CommentListVO> getReplyList(@RequestParam("n") int gdsNum) throws Exception {
	  log.info("get reply list");
	    
	  List<CommentListVO> reply = service.commentList(gdsNum);
	  
	  return reply;
	 } 
	 
	 
	 
	// ��ǰ �Ұ�(���) ����
	 @ResponseBody
	 @RequestMapping(value = "/shop/view/deleteReply", method = RequestMethod.POST)
	 public int getReplyList(CommentVO comment, HttpSession session) throws Exception {
	  log.info("post delete reply");

	  int result = 0;
	  
	  MemberVO member = (MemberVO)session.getAttribute("member");
	  String userId = service.idCheck(comment.getRepNum());
	    
	  if(member.getUserId().equals(userId)) {
	   
	   comment.setUserId(member.getUserId());
	   service.deleteReply(comment);
	   
	   result = 1;
	  }
	  
	  return result; 
	 }
	 
//		@GetMapping("/reply_view") 
//		public String reply_view(BoardVO boardVO, Model model) {
//		
//			log.info("reply_view");
//			model.addAttribute("reply_view", service.get(boardVO.getbId()));
//			
//			return "reply_view";
//		}
	 
	 
	 //��ٱ��� ��� (������ ��ť��Ƽ ���ǰ� �ҷ�����): chaddy
	//�����ϱ� ��ư�� ������ �ش� �޼��� ������ ��ٱ��Ͽ� ��� �ֹ��ϱ� �������� �̵� (������ ��ť��Ƽ ���ǰ� �ҷ�����): chaddy
	 @ResponseBody
	 @RequestMapping(value = "/addCart", method = RequestMethod.POST)
	 public void addCart(CartVO cart, HttpSession session) throws Exception {
		 
 
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		 String username = ((UserDetails)principal).getUsername();
		 cart.setUserId(username);
		 service.addCart(cart);
		 
		 
	 }
	 
		/*
		 * //�����ϱ� ��ư ������� ��ٱ��Ͽ� ��� �ֹ��ϱ� �������� �̵� (������ ��ť��Ƽ ���ǰ� �ҷ�����): chaddy
		 * 
		 * @ResponseBody
		 * 
		 * @RequestMapping(value = "/addCart2", method = RequestMethod.POST) public void
		 * addCart2(CartVO cart, HttpSession session) throws Exception {
		 * 
		 * 
		 * Object principal =
		 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 * 
		 * String username = ((UserDetails)principal).getUsername();
		 * cart.setUserId(username); service.addCart(cart);
		 * 
		 * 
		 * }
		 */
	 
	 
	 //��ٱ��� ����
	 @RequestMapping(value = "/cartList", method = RequestMethod.GET)
	 public String getCartList(HttpSession session, Model model) throws Exception {
		 log.info("get cart list");
		 
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 String username = ((UserDetails)principal).getUsername();

	  
		 List<CartListVO> cartList = service.cartList(username);
	  
		 model.addAttribute("cartList", cartList);
		 
		 return "/wmk_goods/cartList";
	  
	 }
	 
	 
	 
	 
	 
	 
	// ��ٱ��� ����
	 @ResponseBody
	 @RequestMapping(value = "/shop/cartList/deleteCart", method = RequestMethod.POST)
	 public int deleteCart(HttpSession session,
	      @RequestParam(value = "chbox[]") List<String> chArr, CartVO cart) throws Exception {
		 log.info("delete cart");
	  	  
	  
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		 String username = ((UserDetails)principal).getUsername();
	   
		  int result = 0;
		  int cartNum = 0;
		  	  
		  if(username != null) {
		   cart.setUserId(username);
		   
		   for(String i : chArr) {   
		    cartNum = Integer.parseInt(i);
		    cart.setCartNum(cartNum);
		    service.deleteCart(cart);
		   }   
		   result = 1;	
		  }  
		  return result;  
	 }
	 
	 
		@GetMapping("/goodsOrder") 
		public String goodsOrder(Model model) throws Exception {
			
			
			 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 String username = ((UserDetails)principal).getUsername();

		  
			 List<CartListVO> cartList = service.cartList(username);
		  
			 model.addAttribute("cartList", cartList);
			
			log.info("goodsOrder");
			
			return "/wmk_goods/goodsOrder";

		}
		
		
		// goodsOrder�� �ִ� ��ǰ �ֹ��ϱ�
		 @RequestMapping(value = "/goodsOrder", method = RequestMethod.POST)
		 public String goodsOrder(HttpSession session, OrderVO order, OrderDetailVO orderDetail) throws Exception {
			 
			 log.info("order");
			 
			 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 
			 String username = ((UserDetails)principal).getUsername();
			 		  
			  
			  Calendar cal = Calendar.getInstance();
			  int year = cal.get(Calendar.YEAR);
			  String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
			  String ymd = ym +  new DecimalFormat("00").format(cal.get(Calendar.DATE));
			  String subNum = "";
			  
			  
			  for(int i = 1; i <= 6; i ++) {
			   subNum += (int)(Math.random() * 10);
			  }
			  
			  
			  String orderId = ymd + "_" + subNum;
			  
			  order.setOrderId(orderId);
			  order.setUserId(username);
			  
			  service.orderInfo(order);
			  
			  orderDetail.setOrderId(orderId);   
			  service.orderInfo_Details(orderDetail);
			  
			  service.cartAllDelete(username);
			  
			  return "redirect:/goodsOrderComplete";
			  
		  
				/* return "/wmk_goods/goodsOrder"; */
		  
		 }
		 	 
	 	 
	 
	// ��ٱ��Ͽ� �ִ� ��ǰ �ֹ��ϱ�
	 @RequestMapping(value = "/cartList", method = RequestMethod.POST)
	 public String order(HttpSession session, OrderVO order, OrderDetailVO orderDetail) throws Exception {
		 
		 log.info("order");
		 
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		 String username = ((UserDetails)principal).getUsername();
		 		  
		  
		  Calendar cal = Calendar.getInstance();
		  int year = cal.get(Calendar.YEAR);
		  String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		  String ymd = ym +  new DecimalFormat("00").format(cal.get(Calendar.DATE));
		  String subNum = "";
		  
		  
		  for(int i = 1; i <= 6; i ++) {
		   subNum += (int)(Math.random() * 10);
		  }
		  
		  
		  String orderId = ymd + "_" + subNum;
		  
		  order.setOrderId(orderId);
		  order.setUserId(username);
		  
		  service.orderInfo(order);
		  
		  orderDetail.setOrderId(orderId);   
		  service.orderInfo_Details(orderDetail);
		  
		  service.cartAllDelete(username);
		  
		  return "redirect:/orderList";
		  
	  
			/* return "/wmk_goods/goodsOrder"; */
	  
	 }
	 
	 
	
	 @RequestMapping(value = "/goodsOrderComplete", method = RequestMethod.GET)
	 public String goodsOrderComplete(HttpSession session, OrderVO order, Model model) throws Exception {
		
		 log.info("/goodsOrderComplete");
	  
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		 String username = ((UserDetails)principal).getUsername();
	  
	  order.setUserId(username);
	  
	  List<OrderVO> orderList = service.orderList(order);
	  
	  model.addAttribute("orderList", orderList);
	  
	  return "/wmk_goods/goodsOrderComplete";
	  
	 }
	
	
	
	
	 
	// Ư�� ������ �ֹ� ��Ϻ���
	 @RequestMapping(value = "/orderList", method = RequestMethod.GET)
	 public String getOrderList(HttpSession session, OrderVO order, Model model) throws Exception {
		
		 log.info("get order list");
	  
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		 String username = ((UserDetails)principal).getUsername();
	  
	  order.setUserId(username);
	  
	  List<OrderVO> orderList = service.orderList(order);
	  
	  model.addAttribute("orderList", orderList);
	  
	  return "/wmk_goods/orderList";
	  
	 }
	 
	// ū �ֹ��� �� ��� ����
	 @RequestMapping(value = "/orderView", method = RequestMethod.GET)
	 public String getOrderList(HttpSession session, @RequestParam("n") String orderId, OrderVO order, Model model) throws Exception {
		 log.info("get order view");
	  
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		 String username = ((UserDetails)principal).getUsername();
	  
	  order.setUserId(username);
	  order.setOrderId(orderId);
	  
	  List<OrderListVO> orderView = service.orderView(order);
	  
	  model.addAttribute("orderView", orderView);
	  
	  return "/wmk_goods/orderView";
	 }
	 
	 


	 
	 
	 
}