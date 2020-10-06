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
	   
	  //bContent�� �����ͼ� ckeditor�� �־��� ������ �̸��� �и��Ͽ� �迭�� ��´�.
	   String bContent = getDetail.getbContent(); 
	   
	   String[] bContArr = bContent.split("uid=|#");
	   
	   /*
		String bContent1 = bContArr[0];
		String bContent2 = bContArr[1];
	
		System.out.println( "bContent1=" + bContent1);
		System.out.println( "bContent2=" + bContent2);
		*/
	   log.info("bContArrLeng=" + bContArr.length);
	   
	   //�̹����� ���� ��(�Խñۿ� �۸� �ۼ���) �迭�� ���̰� 1�� ����.
	   if(bContArr.length > 1) {
			for(int i=0; i<=bContArr.length-1; i++){
				if(i%2 != 0) { 
					//���ϴ� ���� ���� �� ������ ����
					String realName= bContArr[i];
					String realName2;
					String realName3;
					realName2= realName.replace("&amp;", "");
					realName3= realName2.replace("fileName=", "_");
					log.info("realName3=" + realName3);
					
					//�̹��� ���� ������ ���
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
	
	
	//�˻���� 
	@RequestMapping("/boardByTitle")
	public String boardByTitle(BoardVO boardVO, Model model) {  //�����̵�(�����̵�, �̸��̴�...���) ����ϰ��� ������ ���ڷ� ����.
		
		System.out.println(boardVO.toString());  //BoardVO.java �� �ִ� toString()�� ����ϱ� ����.(test��)
		log.info("boardByTitle");
		
		service.boardByTitle(boardVO);  //������ �Է��� ���� ���񽺿��� �޾��ֱ�����.( ������ �Է��� ������ ���񽺷� �ѱ�� ����)
		System.out.println(service.boardByTitle(boardVO));
		
		model.addAttribute("boardByTitle", service.boardByTitle(boardVO));
		
		return "list";
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
            String path = "C:\\Users\\Administrator\\Documents\\workspace-sts-3.9.13.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\WMKorea\\img\\" + "ckImage/";

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
	String path = "C:\\Users\\Administrator\\Documents\\workspace-sts-3.9.13.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\WMKorea\\img\\" + "ckImage/";
   
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
	
	
	
//	//��� ����
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



