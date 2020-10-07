package com.wmk.ex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.service.AdminService;
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
	public String postGoodsRegister(GoodsVO vo) throws Exception {
		System.out.println("���� ����=" + vo.toString());
		adminService.register(vo);
		
		
		return "redirect:/admin_goods/index";
	
	}
	
	//��ǰ ���
	@RequestMapping(value = "/admin_goods/goods/list", method = RequestMethod.GET)
	public void getGoodList(Model model) throws Exception {
		log.info("get goods list");
		
		List<GoodsVO> list = adminService.goodslist();
		
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
	public String postGoodsModify(GoodsVO vo) throws Exception {
		log.info("post goods modify");

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
	
	//ckeditor �̹��� ���ε�
	@RequestMapping(value = "/goods/img/imageUpload.do")
    public void goodsImageUpload(HttpServletRequest request,
            HttpServletResponse response, MultipartHttpServletRequest multiFile
            , @RequestParam MultipartFile upload) throws Exception{
		
		System.out.println("���� Ÿ?");
        // ���� ���� ����
        UUID uid = UUID.randomUUID();
        OutputStream out = null;
        PrintWriter printWriter = null;
        
        //���ڵ�
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        try{
            
            //���� �̸� ��������
            String fileName = upload.getOriginalFilename(); //���ε��� �������� ���� ������ ���Ѵ�.
            byte[] bytes = upload.getBytes(); //���ε��� ���� �����͸� ���Ѵ�.
            
        	
            //�̹��� ��� ����
            String path = "C:\\ckeditor_img\\" + "ckImage/";

            String ckUploadPath = path + uid + "_" + fileName;
            File folder = new File(path);
           
            
            
            //�ش� ���丮 Ȯ��(���丮�� ���� ��� �������ش�.)
            if(!folder.exists()){
                try{
                    folder.mkdirs(); // ���� ����
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
            
            out = new FileOutputStream(new File(ckUploadPath));
            out.write(bytes);
            out.flush(); // outputStram�� ����� �����͸� �����ϰ� �ʱ�ȭ
            
            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            String fileUrl = "/ex/goods/img/ckImgSubmit.do?uid=" + uid + "&fileName=" + fileName+ "&#";  // �ۼ�ȭ��
        
            // ���ε�� �޽��� ���
          printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
          printWriter.flush(); //���� ���ۿ� ����Ǿ� �ִ� ������ Ŭ���̾�Ʈ�� �����ϰ� ���۸� ����. (JSP)

        }catch(IOException e){
            e.printStackTrace();
        } finally {
          try {
           if(out != null) { out.close(); }
           if(printWriter != null) { printWriter.close(); }
          } catch(IOException e) { e.printStackTrace(); }
         }
        
        return;
    }
	

	@RequestMapping(value= "/goods/img/ckImgSubmit.do")
    public void goodsCkSubmit(@RequestParam(value="uid") String uid
                            , @RequestParam(value="fileName") String fileName
                            , HttpServletRequest request, HttpServletResponse response)
                            		throws ServletException, IOException{
        
		System.out.println("����");
		//������ ����� �̹��� ���
		String path = "C:\\ckeditor_img\\" + "ckImage/";
   
		String sDirPath = path + uid + "_" + fileName;
	
		File imgFile = new File(sDirPath);
    
    //���� �̹��� ã�� ���ϴ� ��� ����ó���� �� �̹��� ������ �����Ѵ�.
	    if(imgFile.isFile()){
	        byte[] buf = new byte[1024];
	        int readByte = 0;
	        int length = 0;
	        byte[] imgBuf = null;
	        
	        FileInputStream fileInputStream = null;
	        ByteArrayOutputStream outputStream = null;
	        ServletOutputStream out = null;
	        
	        try{
	            fileInputStream = new FileInputStream(imgFile);
	            outputStream = new ByteArrayOutputStream();
	            out = response.getOutputStream();
	            
	            while((readByte = fileInputStream.read(buf)) != -1){
	                outputStream.write(buf, 0, readByte);
	            }
	            
	            imgBuf = outputStream.toByteArray();
	            length = imgBuf.length;
	            out.write(imgBuf, 0, length);
	            out.flush();
	            
	        }catch(IOException e){
	            log.info(e);
	        }finally {
	            outputStream.close();
	            fileInputStream.close();
	            out.close();
	        }
	    }


	}
		
	
	
}
