package com.wmk.ex;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wmk.ex.service.AdminService;
import com.wmk.ex.util.UploadFileUtils;
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
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	//��ǰ ���� ������
	@RequestMapping(value = "/goodsIndex", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "/wmk_goods/index";
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
	public String postGoodsRegister(GoodsVO vo, MultipartFile file) throws Exception {
		
		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;

		if(file != null) {
		 fileName =  UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
		} else {
		 fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
		}

		vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		
		adminService.register(vo);
		
		return "redirect:/admin_goods/index";
	
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
		
	// ��ǰ ����
	@RequestMapping(value = "/admin_goods/goods/modify", method = RequestMethod.POST)
	public String postGoodsModify(GoodsVO vo, MultipartFile file, HttpServletRequest req) throws Exception {
		log.info("post goods modify");
		
		 // ���ο� ������ ��ϵǾ����� Ȯ��
		 if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
		  // ���� ������ ����
		  new File(uploadPath + req.getParameter("gdsImg")).delete();
		  new File(uploadPath + req.getParameter("gdsThumbImg")).delete();
		  
		  // ���� ÷���� ������ ���
		  String imgUploadPath = uploadPath + File.separator + "imgUpload";
		  String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		  String fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
		  
		  vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		  vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		  
		 } else {  // ���ο� ������ ��ϵ��� �ʾҴٸ�
		  // ���� �̹����� �״�� ���
		  vo.setGdsImg(req.getParameter("gdsImg"));
		  vo.setGdsThumbImg(req.getParameter("gdsThumbImg"));
		  
		 }

		adminService.goodsModify(vo);
		 
		return "redirect:/admin_goods/index";
	}
		
	// ��ǰ ����
	@RequestMapping(value = "/admin_goods/goods/delete", method = RequestMethod.POST)
	public String postGoodsDelete(@RequestParam("n") int gdsNum) throws Exception {
		log.info("post goods delete");

		adminService.goodsDelete(gdsNum);
		 
		return "redirect:/admin_goods/index";
	}
	
	
	
}
