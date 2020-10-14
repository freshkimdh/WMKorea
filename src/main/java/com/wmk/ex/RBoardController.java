package com.wmk.ex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.ibatis.annotations.Param;
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
import com.wmk.ex.vo.RBoardVO;
import com.wmk.ex.vo.RReplyVO;
import com.wmk.ex.vo.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class RBoardController {
	
	private RBoardService rservice;
	
	//전지역 목록
	@RequestMapping("/areaIndex")
	public String areaList(RBoardVO rboardVO, Model model) {
		
		log.info("area_List...");
	
		
		return "/review_board/areaIndex";
	}
	
	//게시판 목록
	@RequestMapping("/review_boardList")
	public String reviewList(RBoardVO rboardVO, Model model, String rArea) {
		System.out.println("rArea답답답" + rArea);
		log.info("review_boardList...");
		model.addAttribute("rList", rservice.getReviewList(rboardVO));
		model.addAttribute("rArea", rboardVO.getrArea());
		System.out.println("rArea"+ rboardVO.getrArea());
		
		return "/review_board/userReviewList";
	}
	
	  //게시판 목록 ajax
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

	//게시판 내용
	@GetMapping("/review_contentView") 
	public String reviewContentView(RBoardVO rboardVO, Model model, String area, int rBoardNum) throws Exception {
		
	   log.info("review_contentView...");
	   System.out.println("rboardVO= " + rboardVO);
	   System.out.println("오잉" + rservice.getrBoardNum(rboardVO.getrBoardNum()));
	   model.addAttribute("rContentView", rservice.getrBoardNum(rboardVO.getrBoardNum()));
	   if(area != "") {
		   model.addAttribute("area", area);
	   }
	   
	   List<RReplyVO> reply = rservice.replyList(rBoardNum);
	   model.addAttribute("reply", reply);
	   
	   return "/review_board/reviewContentView";
	}
	
	//게시판 작성 view
	@RequestMapping("/review_writeView")
	public String reviewWriteView(RBoardVO rboardVO ,Model model) {
		
		log.info("review_writeView...");
		System.out.println("area2="+ rboardVO.getrArea());
		model.addAttribute("rArea", rboardVO.getrArea());
		
		return "/review_board/writeView";
	}
	
	//게시판 작성 
	@RequestMapping("/review_write")
	public String write(RBoardVO rboardVO, String area) throws Exception {
//		public String write(RBoardVO rboardVO, MultipartHttpServletRequest mpRequest) throws Exception {
		log.info("review_write...");
		System.out.println("area="+ area);
		System.out.println("답은="+ rboardVO.toString());
		//rservice.rWriteBoard(rboardVO, mpRequest);
		rservice.rWriteBoard(rboardVO);
		

		return "review_boardList?rArea="+ area;
	}
	
	//게시판 수정 화면
	@GetMapping("/review_modifyView") 
	public String reviewModifyView(RBoardVO rboardVO, Model model) {
	
		log.info("review_modifyView...");	

		model.addAttribute("rModifyView", rservice.getrBoardNum(rboardVO.getrBoardNum()));
		
		return "/review_board/modifyView";
	}
	
	//게시판 수정
	@RequestMapping("/review_modify")
	public String reviewModify(RBoardVO rboardVO) {
		
		log.info("review_modify...");
		rservice.updaterModify(rboardVO);
		
		return "redirect:review_boardList";
	}

	//게시판 삭제
	@GetMapping("/review_delete") 
	public String reviewDelete(RBoardVO rboardVO) {
		
	    log.info("review_delete...");
	    
	    RBoardVO getDetail = rservice.getrBoardNum(rboardVO.getrBoardNum());
	    log.info("getDetail=" + rservice.getrBoardNum(rboardVO.getrBoardNum()));
	    
	    //rContent를 가져와서 ckeditor에 넣었던 값들의 이름을 분리하여 배열에 담는다.
	    String rContent = getDetail.getrContent();
	    
	    String[] rContArr = rContent.split("uid=|#");
	    
	    log.info("rContentArrLeng=" + + rContArr.length);
	    
	    //이미지가 없을 때 배열의 길이가 1이었으므로, 1보다 클 경우로 if문 작성한다.
	    if(rContArr.length > 1) {
	    	for(int i=0; i<=rContArr.length-1; i++) {
	    		if(i%2 != 0) {
	    			//원하는 문자 삭제 및 변경을 위함
	    			String realName= rContArr[i];
	    			String realName2;
	    			String realName3;
	    			realName2 = realName.replace("&amp;", "");
					realName3= realName2.replace("fileName=", "_");
					log.info("realName3=" + realName3);
					
					//이미지 삭제 가능한 방법
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

	//ckeditor 이미지 업로드
	@RequestMapping("/img/imageUpload.do")
    public void imageUpload(HttpServletRequest request,
            HttpServletResponse response, MultipartHttpServletRequest multiFile
            , @RequestParam MultipartFile upload) throws Exception{
        
		// 랜덤 문자 생성
        UUID uid = UUID.randomUUID();
        OutputStream out = null;
        PrintWriter printWriter = null;
        
        //인코딩
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        try{
            
            //파일 이름 가져오기
            String fileName = upload.getOriginalFilename(); //업로드한 오리지널 파일 네임을 구한다.
            byte[] bytes = upload.getBytes(); //업로드한 파일 데이터를 구한다.
            
        	
            //이미지 경로 생성
            String path = "C:\\Review\\" + "ckImage/";

            String ckUploadPath = path + uid + "_" + fileName;
            File folder = new File(path);
           
            
            
            //해당 디렉토리 확인(디렉토리가 없을 경우 생성해준다.)
            if(!folder.exists()){
                try{
                    folder.mkdirs(); // 디렉토리 생성
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
            
            out = new FileOutputStream(new File(ckUploadPath));
            out.write(bytes);
            out.flush(); // outputStram에 저장된 데이터를 전송하고 초기화
            
            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            String fileUrl = "/ex/img/ckImgSubmit.do?uid=" + uid + "&fileName=" + fileName+ "&#";  // 작성화면
        
            // 업로드시 메시지 출력
            printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
            printWriter.flush(); //현재 버퍼에 저장되어 있는 내용을 클라이언트로 전송하고 버퍼를 비운다. (JSP)

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
        
	//서버에 저장된 이미지 경로
	String path = "C:\\Review\\" + "ckImage/";
   
    String sDirPath = path + uid + "_" + fileName;
	
    File imgFile = new File(sDirPath);
    
    //사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
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
	
	
	//댓글 작성
	@RequestMapping(value = "/review_contentView", method = RequestMethod.POST)
	public String registReply(RReplyVO reply) throws Exception {
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
	 
	 log.info("성공");
	 
	 return "redirect:/review_contentView?rBoardNum=" + reply.getrBoardNum() + "&area=";
	}
	
	
	
	
	
	
	
	
	
}



