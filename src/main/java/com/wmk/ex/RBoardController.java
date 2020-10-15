package com.wmk.ex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.service.RBoardService;
import com.wmk.ex.vo.CustomUser;
import com.wmk.ex.vo.RBoardVO;
import com.wmk.ex.vo.RReplyVO;
import com.wmk.ex.vo.ReplyVO;
import com.wmk.ex.vo.UserVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class RBoardController {
	
	private RBoardService rservice;
	
	//������ ���
	@RequestMapping("/areaIndex")
	public String areaList(RBoardVO rboardVO, Model model) {
		
		log.info("area_List...");
	
		
		return "/review_board/areaIndex";
	}
	
	//�Խ��� ���
	@RequestMapping("/review_boardList")
	public String reviewList(RBoardVO rboardVO, Model model, String rArea) {
		System.out.println("rArea����" + rArea);
		log.info("review_boardList...");
		model.addAttribute("rList", rservice.getReviewList(rboardVO));
		model.addAttribute("rArea", rboardVO.getrArea());
		System.out.println("rArea"+ rboardVO.getrArea());
		
		return "/review_board/userReviewList";
	}
	
	  //�Խ��� ��� ajax
	  @RequestMapping("/review_boardList_ajax") 
	  @ResponseBody
	  public List<RBoardVO> reviewListAjax(@RequestBody RBoardVO rboardVO, Model model) {
	  
	     log.info("review_boardList_ajax..."); 
	    // System.out.println("cate" + rboardVO.getrCategory());
	     System.out.println("rArea"+ rboardVO.getrArea());
		// model.addAttribute("area", area);
	     
	     List<RBoardVO> list = null;
	     if(rboardVO.getrCategory() == 0) {
	    	 list = rservice.getReviewList(rboardVO);
	     }else {
	    	 list = rservice.getReviewListAjax(rboardVO);
	     }
			 
		return list ;
	  }

	//�Խ��� ����
	@GetMapping("/review_contentView") 
	public String reviewContentView(RBoardVO rboardVO, Model model, String area, int rBoardNum,  Authentication authentication) throws Exception {
		CustomUser loginInfo =  authentication != null ? (CustomUser) authentication.getPrincipal() : null;
		
		if(area != "") {
			model.addAttribute("area", area);
			log.info("Area!!!!!!!!!!!!!!!!!!!!");
		}
		model.addAttribute("list", rservice.getReviewList(rboardVO));
		if(loginInfo == null) {
			// 로그인 안된사람은 좋아요 눌르지 못하니 false리턴
			model.addAttribute("isSelectLike", false);
		} else {
			// 로그인 유저가 해당 게시글 좋아요 버튼 눌렀는지 알기 위해 해당 테이블 조회
			int likeCount = rservice.getLikeCount(rboardVO.getrBoardNum(), loginInfo.getUser().getId());
			log.info(likeCount);
			model.addAttribute("isSelectLike", likeCount > 0);
			
		}
		log.info("content_view...");
		model.addAttribute("rContentView", rservice.getrBoardNum(rboardVO.getrBoardNum()));
		log.info("CONTENTVIEW	:rservice.getrBoardNum(rboardVO.getrBoardNum())..??????");
		
		log.info("LIST		:rservice.getReviewList(rboardVO)))..??????");
		
	   
	   
		return "/review_board/reviewContentView";
	}
	
	//�Խ��� �ۼ� view
	@RequestMapping("/review_writeView")
	public String reviewWriteView(RBoardVO rboardVO ,Model model) {
		
		log.info("review_writeView...");
		System.out.println("area2="+ rboardVO.getrArea());
		model.addAttribute("rArea", rboardVO.getrArea());
		
		return "/review_board/writeView";
	}
	
	//�Խ��� �ۼ� 
	@RequestMapping("/review_write")
	public String write(RBoardVO rboardVO, String area) throws Exception {
//		public String write(RBoardVO rboardVO, MultipartHttpServletRequest mpRequest) throws Exception {
		log.info("review_write...");
		System.out.println("area="+ area);
		System.out.println("����="+ rboardVO.toString());
		//rservice.rWriteBoard(rboardVO, mpRequest);
		rservice.rWriteBoard(rboardVO);
		

		return "review_boardList?rArea="+ area;
	}
	
	//�Խ��� ���� ȭ��
	@GetMapping("/review_modifyView") 
	public String reviewModifyView(RBoardVO rboardVO, Model model) {
	
		log.info("review_modifyView...");	

		model.addAttribute("rModifyView", rservice.getrBoardNum(rboardVO.getrBoardNum()));
		
		return "/review_board/modifyView";
	}
	
	//�Խ��� ����
	@RequestMapping("/review_modify")
	public String reviewModify(RBoardVO rboardVO) {
		
		log.info("review_modify...");
		rservice.updaterModify(rboardVO);
		
		return "redirect:review_boardList";
	}

	//�Խ��� ����
	@GetMapping("/review_delete") 
	public String reviewDelete(RBoardVO rboardVO) {
		
	    log.info("review_delete...");
	    
	    RBoardVO getDetail = rservice.getrBoardNum(rboardVO.getrBoardNum());
	    log.info("getDetail=" + rservice.getrBoardNum(rboardVO.getrBoardNum()));
	    
	    //rContent�� �����ͼ� ckeditor�� �־��� ������ �̸��� �и��Ͽ� �迭�� ��´�.
	    String rContent = getDetail.getrContent();
	    
	    String[] rContArr = rContent.split("uid=|#");
	    
	    log.info("rContentArrLeng=" + + rContArr.length);
	    
	    //�̹����� ���� �� �迭�� ���̰� 1�̾����Ƿ�, 1���� Ŭ ���� if�� �ۼ��Ѵ�.
	    if(rContArr.length > 1) {
	    	for(int i=0; i<=rContArr.length-1; i++) {
	    		if(i%2 != 0) {
	    			//���ϴ� ���� ���� �� ������ ����
	    			String realName= rContArr[i];
	    			String realName2;
	    			String realName3;
	    			realName2 = realName.replace("&amp;", "");
					realName3= realName2.replace("fileName=", "_");
					log.info("realName3=" + realName3);
					
					//�̹��� ���� ������ ���
					File existFile = new File( "C:\\Review\\" + "ckImage/" + realName3); 
					if(existFile.exists()){
						existFile.delete(); 
					}
	    		}
	    		
	    	}
	    	
	    }
	    rservice.deleterBoard(rboardVO.getrBoardNum());
	    
	    return "redirect:review_boardList";
	}

	//ckeditor �̹��� ���ε�
	@RequestMapping("/img/imageUpload.do")
    public void imageUpload(HttpServletRequest request,
            HttpServletResponse response, MultipartHttpServletRequest multiFile
            , @RequestParam MultipartFile upload) throws Exception{
        
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
            String path = "C:\\Review\\" + "ckImage/";

            String ckUploadPath = path + uid + "_" + fileName;
            File folder = new File(path);
           
            
            
            //�ش� ���丮 Ȯ��(���丮�� ���� ��� �������ش�.)
            if(!folder.exists()){
                try{
                    folder.mkdirs(); // ���丮 ����
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
            
            out = new FileOutputStream(new File(ckUploadPath));
            out.write(bytes);
            out.flush(); // outputStram�� ����� �����͸� �����ϰ� �ʱ�ȭ
            
            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            String fileUrl = "/ex/img/ckImgSubmit.do?uid=" + uid + "&fileName=" + fileName+ "&#";  // �ۼ�ȭ��
        
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
	
	@RequestMapping("/img/ckImgSubmit.do")
    public void ckSubmit(@RequestParam(value="uid") String uid
                            , @RequestParam(value="fileName") String fileName
                            , HttpServletRequest request, HttpServletResponse response)
                            		throws ServletException, IOException{
        
	//������ ����� �̹��� ���
	String path = "C:\\Review\\" + "ckImage/";
   
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
	
	
	//��� �ۼ�
	@ResponseBody
	@RequestMapping(value = "/review_contentView/registReply", method = RequestMethod.POST)
	public void registReply(RReplyVO reply) throws Exception {
	 log.info("regist reply");
	 
	 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	 
	 if (principal instanceof UserDetails) {
	   String username = ((UserDetails)principal).getUsername();
	   log.info(username);
	   reply.setId(username);
	   
	 } else { 
	   String username = principal.toString();
	   
	   
	 }
	 
	 
	 rservice.registReply(reply);
	 
	 log.info("����");
	 
	 //return "redirect:/review_contentView?rBoardNum=" + reply.getrBoardNum() + "&area=";
	}
	
	
	// ��ǰ �Ұ�(���) ���
	@ResponseBody
	@RequestMapping("/review_contentView/replyList")
	public List<RReplyVO> getReplyList(@RequestParam("n") int rBoardNum) throws Exception {
	 log.info("get reply list...");
	   
	 List<RReplyVO> reply = rservice.replyList(rBoardNum);
	 
	 return reply;
	} 
	
	@ResponseBody
	@RequestMapping("/review_contentView/deleteReply")
	public String getReplyList(RReplyVO reply) throws Exception {
	 log.info("post delete reply");

	 int result = 0;
	 
	 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	 String username = ((UserDetails)principal).getUsername();
	 String userId = rservice.replyUserIdCheck(reply.getRepNum());
	 log.info(userId);
	 
	 if(username.equals(userId)) {
	  
		 log.info("����");
			 
		 reply.setId(username); 
		 rservice.deleteReply(reply);
		  
		 result = 1;
		  
		 log.info("if �� �ȿ�: " + result);
	 }
	 
	 log.info("if �� �ۿ�: " + result);
	 
	 return String.valueOf(result);
	 
	}
	
	
	
	
	
	
	
	
}



