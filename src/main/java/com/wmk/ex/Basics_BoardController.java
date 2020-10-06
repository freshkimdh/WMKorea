package com.wmk.ex;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.page.PageDTO;
import com.wmk.ex.service.BoardService;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class Basics_BoardController {
	
	private BoardService service;
	
	 @GetMapping("/list") 
	 public void list(Criteria cri, Model model) {	
		 
		 log.info("list");
		 log.info(cri);
		 model.addAttribute("list", service.getList(cri));	
	
		 int total = service.getTotal(cri);
		 log.info("total" + total);
		 
		 model.addAttribute("pageMaker", new PageDTO(cri,total));	
	 }
	
	 
	@GetMapping("/content_view") 
	public String content_view(BoardVO boardVO, Model model)throws Exception {
		
	   log.info("content_view");
	   model.addAttribute("content_view", service.get(boardVO.getbId()));
	   
	   List<ReplyVO> replyList = service.readReply(boardVO.getbId());
	   model.addAttribute("replyList", replyList);
	   
	   List<Map<String, Object>> fileList = service.selectFileList(boardVO.getbId());
	   model.addAttribute("file", fileList);
	   
	   return "content_view";
	}
	
	
	@GetMapping("/delete") 
	public String delete(BoardVO boardVO) {
		
	   log.info("delete");
	   
	   BoardVO getDetail = service.get(boardVO.getbId());
	   log.info(service.get(boardVO.getbId()));
	   
	  //bContent를 가져와서 ckeditor에 넣었던 값들의 이름을 분리하여 배열에 담는다.
	   String bContent = getDetail.getbContent(); 
	   
	   String[] bContArr = bContent.split("uid=|#");
	   
	   /*
		String bContent1 = bContArr[0];
		String bContent2 = bContArr[1];
	
		System.out.println( "bContent1=" + bContent1);
		System.out.println( "bContent2=" + bContent2);
		*/
	   log.info("bContArrLeng=" + bContArr.length);
	   
	   //이미지가 없을 때(게시글에 글만 작성시) 배열의 길이가 1이 나옴.
	   if(bContArr.length > 1) {
			for(int i=0; i<=bContArr.length-1; i++){
				if(i%2 != 0) { 
					//원하는 문자 삭제 및 변경을 위함
					String realName= bContArr[i];
					String realName2;
					String realName3;
					realName2= realName.replace("&amp;", "");
					realName3= realName2.replace("fileName=", "_");
					log.info("realName3=" + realName3);
					
					//이미지 삭제 가능한 방법
					File existFile = new File("C:\\Users\\Administrator\\Documents\\workspace-sts-3.9.13.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\WMKorea\\img\\" + "ckImage\\" + realName3 ); 
					if(existFile.exists()){
						existFile.delete(); 
					}
				}
				
			}
		}
	    service.remove(boardVO.getbId());

	   
	    return "redirect:list";
	}
	
	
	@GetMapping("/write_view") 
	public String write_view() {
		
	   log.info("write_view");
	   
	   return "write_view";
	}
	
	
	@RequestMapping("/write")
	public String write(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception {
		
		log.info("write");
		service.writeBoard(boardVO, mpRequest);

		return "redirect:list";
	}
	
	
	
	@GetMapping("/reply_view") 
	public String reply_view(BoardVO boardVO, Model model) {
	
		log.info("reply_view");
		model.addAttribute("reply_view", service.get(boardVO.getbId()));
		
		return "reply_view";
	}
	
	
	@RequestMapping("/reply")
	public String reply(BoardVO boardVO, Model model) throws Exception {
		
		log.info("reply");
		service.writeReply(boardVO);
		
		return "redirect:list";
	}
	
	@RequestMapping("/goToModify")
	public String goToModify(BoardVO boardVO, Model model) throws Exception{
		
		log.info("modify");
		
		 model.addAttribute("content_view", service.get(boardVO.getbId()));
		
		 List<ReplyVO> replyList = service.readReply(boardVO.getbId());
		 model.addAttribute("replyList", replyList);
		   
		 List<Map<String, Object>> fileList = service.selectFileList(boardVO.getbId());
		 model.addAttribute("file", fileList);
		
		return "modify";
	}
	
	
	@RequestMapping("/modify")
	public String modify(BoardVO boardVO, Model model) {
		
		log.info("modify");
		
		service.modify(boardVO);
		
		return "redirect:list";
	}
	
	
	//검색기능 
	@RequestMapping("/boardByTitle")
	public String boardByTitle(BoardVO boardVO, Model model) {  //무엇이든(제목이든, 이름이던...등등) 출력하고자 봉투를 인자로 넣음.
		
		System.out.println(boardVO.toString());  //BoardVO.java 에 있는 toString()을 출력하기 위해.(test용)
		log.info("boardByTitle");
		
		service.boardByTitle(boardVO);  //유저가 입력한 것을 서비스에서 받아주기위함.( 유저가 입력한 제목을 서비스로 넘기기 위함)
		System.out.println(service.boardByTitle(boardVO));
		
		model.addAttribute("boardByTitle", service.boardByTitle(boardVO));
		
		return "list";
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
            String path = "C:\\Users\\Administrator\\Documents\\workspace-sts-3.9.13.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\WMKorea\\img\\" + "ckImage/";

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
	String path = "C:\\Users\\Administrator\\Documents\\workspace-sts-3.9.13.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\WMKorea\\img\\" + "ckImage/";
   
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
	
	
	
//	//댓글 삭제
//	@RequestMapping("/replyDelete")
//	public String replyDelete(ReplyVO vo)  {
//		
//		log.info("replyDelete...");
//		
//		service.deleteReply(vo);
//		
//		return "redirect:contentView";
//	}

	
}



