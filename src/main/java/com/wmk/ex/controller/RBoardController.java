package com.wmk.ex.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wmk.ex.service.RBoardService;
import com.wmk.ex.vo.CustomUser;
import com.wmk.ex.vo.RBoardVO;
import com.wmk.ex.vo.RReplyVO;
import com.wmk.ex.vo.ReplyVO;
import com.wmk.ex.vo.UserVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller()
@AllArgsConstructor
@Log4j
public class RBoardController {
	
	private RBoardService rservice;
	
	
	@RequestMapping("/areaIndex")
	public String areaList(RBoardVO rboardVO, Model model) {
		
		log.info("area_List...");
	
		
		return "/review_board/areaIndex";
	}
	
	
	@RequestMapping("/review_boardList")
	//작성 후(write 메서드) 보낸 파라메터 값(지역명)을 받기 위해 @RequestParam 을 사용한다.
	public String reviewList(RBoardVO rboardVO, Model model, @RequestParam("rArea") String rArea) {
		
		log.info("rArea지역은=" + rArea);
		log.info("review_boardList...");
		log.info("rboardVO 왜 안나오까?" + rboardVO.toString());
		
		//만약 rArea가 ""이 아니거나 null값이 아니면, rArea의 값을 vo에 넣어준다.
		if(!"".equals(rArea) || rArea != null  ) {
			rboardVO.setrArea(rArea);
		}
		
		model.addAttribute("rList", rservice.getReviewList(rboardVO));
		model.addAttribute("rArea", rboardVO.getrArea());
		
		log.info("rList" + rservice.getReviewList(rboardVO));
		log.info("rArea" + rboardVO.getrArea());
		
		return "/review_board/userReviewList";
	}
	
	 //list ajax page
	  @RequestMapping("/review_boardList_ajax") 
	  @ResponseBody
	  public List<RBoardVO> reviewListAjax(@RequestBody RBoardVO rboardVO, Model model) {
	  
	     log.info("review_boardList_ajax..."); 
	     log.info("rArea"+ rboardVO.getrArea());
	     
	     List<RBoardVO> list = null;
	     //if category가 0이면 모든 리스트를 출력하고, 아니라면 ajax리스트를 가져와라.
	     if(rboardVO.getrCategory() == 0) {
	    	 list = rservice.getReviewList(rboardVO);
	     }else {
	    	 list = rservice.getReviewListAjax(rboardVO);
	     }
	     model.addAttribute("rList", rservice.getReviewList(rboardVO));	   //??
		
	    return list ;
	  }

	
	@GetMapping("/review_contentView") 
	public String reviewContentView(RBoardVO rboardVO, Model model,  @RequestParam("area") String area, 
									int rBoardNum, Authentication authentication) throws Exception {
		
		//if area가 ""아니면, area를 jsp에 area라는 이름으로 뿌려준다.
		if(area != "") {
			model.addAttribute("area", area);
			log.info("Area!!!!!!!!!!!!!!!!!!!!");
		}
		
		log.info("rContent_view...");
		
		model.addAttribute("rContentView", rservice.getrBoardNum(rboardVO.getrBoardNum()));
		log.info("rContentView=" + rservice.getrBoardNum(rboardVO.getrBoardNum()));
		
		List<Map<String, Object>> fileList = rservice.selectFileList(rboardVO.getrBoardNum());
		model.addAttribute("file", fileList);
		
		
		CustomUser loginInfo =  authentication != null ? (CustomUser) authentication.getPrincipal() : null;

		if(loginInfo == null) {
			// 로그인 안된사람은 좋아요 눌르지 못하니 false리턴
			model.addAttribute("isSelectLike", false);
		} else {
			// 로그인 유저가 해당 게시글 좋아요 버튼 눌렀는지 알기 위해 해당 테이블 조회
			int likeCount = rservice.getLikeCount(rboardVO.getrBoardNum(), loginInfo.getUser().getId());
			log.info("like Count	:"+likeCount);
			model.addAttribute("isSelectLike", likeCount > 0);
		}
		
	   
		return "/review_board/reviewContentView";
	}
	
	
	@RequestMapping("/review_writeView")
	public String reviewWriteView(RBoardVO rboardVO ,Model model) {
		
		log.info("review_writeView...");
		log.info("area2="+ rboardVO.getrArea());
		model.addAttribute("rArea", rboardVO.getrArea());
		
		return "/review_board/writeView";
	}
	

	@RequestMapping("/review_write")
	public String write(RBoardVO rboardVO, String area, RedirectAttributes redirect, MultipartHttpServletRequest mpRequest) throws Exception {
		
		log.info("review_write...");
		log.info("area="+ area);
		rservice.rWriteBoard(rboardVO, mpRequest);
		
		//redirect로 parameter값 전달(get방식으로 area값을 보내주기 위해 redirect를 한다.) **받는 곳에서 반드시 @RequestParam으로 매핑해주어야함!!!
		redirect.addAttribute("rArea", area);  
		
		return "redirect:review_boardList";
	}
	
	//수정화면
	@GetMapping("/review_modifyView") 
	public String reviewModifyView(RBoardVO rboardVO, Model model, String area) throws Exception {
	
		log.info("review_modifyView...");	
		log.info("rboardVO.getrArea()=" + rboardVO.getrArea());
		model.addAttribute("rModifyView", rservice.getrBoardNum(rboardVO.getrBoardNum()));
		model.addAttribute("area", area);
		
		List<Map<String, Object>> fileList = rservice.selectFileList(rboardVO.getrBoardNum());
		model.addAttribute("file", fileList);
		
		return "/review_board/modifyView";
	}
	
	//수정
	@RequestMapping("/review_modify")
	public String reviewModify(RBoardVO rboardVO, RedirectAttributes redirect, MultipartHttpServletRequest mpRequest) throws Exception {
		
		log.info("review_modify...");
		log.info("rboardVO!!!=" + rboardVO);
		
		rservice.updaterModify(rboardVO, mpRequest);
		
		log.info("수정내용 보여줘"+ rboardVO.toString());
		
		
		redirect.addAttribute("area", rboardVO.getrArea());
		redirect.addAttribute("rBoardNum", rboardVO.getrBoardNum());
		
		return "redirect:review_contentView";
	}


	@GetMapping("/review_delete") 
	public String reviewDelete(RBoardVO rboardVO, RedirectAttributes redirect) {
		
	    log.info("review_delete...");
	    log.info("rboardVO="+ rboardVO);
	    //글번호를 넣어서 db에 해당 글번호에 해당하는 한줄을 가져온다.
	    RBoardVO getDetail = rservice.getrBoardNum(rboardVO.getrBoardNum());
	    log.info("getDetail=" + rservice.getrBoardNum(rboardVO.getrBoardNum()));
	    
	    //rContent를 가져와서 ckeditor에 넣었던 값들의 이름을 분리하여 배열에 담는다.
	    //위의 getDetail 한줄 안에 있는 글내용을 선언하여 저장한다. 
	    String rContent = getDetail.getrContent();
	   //rContent의 값이 없거나 빈값이라면
	    if(rContent == null || "".equals(rContent)) {
	    	rservice.deleterBoard(rboardVO.getrBoardNum());
			rservice.removerBoard(rboardVO.getrBoardNum());
	    //rContent의 값이 존재하면	
	    }else{
	    	int imgFindIndex = rContent.indexOf("uid=");
	    	//rContent 내용중 이미지가 존재하는경우
	    	if(imgFindIndex == -1) {
	    		String[] rContArr = rContent.split("uid=|#");
		    	//이미지가 없을 때 배열의 길이가 1로 출력됨. 1보다 클 경우로 if문 작성한다.
	    	    if(rContArr.length > 1) {
	    	    	for(int i=0; i<=rContArr.length-1; i++) {
	    	    		if(i%2 != 0) {
	    	    			
	    	    			String realName= rContArr[i];
	    	    			String realName2;
	    	    			String realName3;
	    	    			realName2 = realName.replace("&amp;", "");
	    					realName3= realName2.replace("fileName=", "_");
	    					log.info("realName3=" + realName3);
	    					
	    					File existFile = new File( "C:\\Review\\" + "ckImage/" + realName3); 
	    					if(existFile.exists()){
	    						existFile.delete(); 
	    					}
	    	    		}
	    	    		
	    	    	}
	    	    	
	    	    }
	    	}
	    	rservice.deleterBoard(rboardVO.getrBoardNum());
			rservice.removerBoard(rboardVO.getrBoardNum());
	    }; 
		
		redirect.addAttribute("rArea", rboardVO.getrArea());
	    
		return "redirect:review_boardList";
	}

	//ckeditor 이미지 저장
	@RequestMapping("/img/imageUpload.do")
    public void imageUpload(HttpServletRequest request,
            HttpServletResponse response, MultipartHttpServletRequest multiFile
            , @RequestParam MultipartFile upload) throws Exception{
        
		//파일명 변경
        UUID uid = UUID.randomUUID();
        OutputStream out = null;
        PrintWriter printWriter = null;
        
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        try{
            
            String fileName = upload.getOriginalFilename();
            byte[] bytes = upload.getBytes();
            
        	
            //저장되는 이미지 경로
            String path = "C:\\Review\\" + "ckImage/";

            String ckUploadPath = path + uid + "_" + fileName;
            File folder = new File(path);
           
            //저장파일이 존재하지 않는다면
            if(!folder.exists()){
                try{
                    folder.mkdirs(); // 만들어준다. (디렉토리)
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
            
            out = new FileOutputStream(new File(ckUploadPath));
            out.write(bytes);
            out.flush();
            
            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            String fileUrl = "/ex/img/ckImgSubmit.do?uid=" + uid + "&fileName=" + fileName+ "&#"; 
        
            
            printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
            printWriter.flush(); 

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
        
	String path = "C:\\Review\\" + "ckImage/";
   
    String sDirPath = path + uid + "_" + fileName;
	
    File imgFile = new File(sDirPath);
    
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
	
	
	//댓글
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
	 
	}
	
	
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



