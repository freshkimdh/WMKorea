<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!-- view 부분이다 -->
<!DOCTYPE html>

<html>
   <head>
      <meta charset="UTF-8">
      <title>Content view</title>
      <script type="text/javascript" src="resources/ckeditor/ckeditor.js"></script>
   </head>
   
   <body>
     <table width="500" cellpadding="0" cellspacing="0" border="1" align="center">
      <form action="modify" method="get">      
         <input type="hidden" name="bId" value="${content_view.bId}">
         <tr>
            <td> 번호 </td>
            <td> ${content_view.bId} </td>
         </tr>
         <tr>
            <td> 히트 </td>
            <td> ${content_view.bHit} </td>
         </tr>
         <tr>
            <td> 이름 </td>
            <td> <input type="text" name="bName" value="${content_view.bName}"></td>
         </tr>
         <tr>
            <td> 제목 </td>
            <td> <input type="text" name="bTitle" value="${content_view.bTitle}"></td>
         </tr>
         <tr>
            <td> 내용 </td>
            <td> 
            	<textarea id = "bContent" name = "bContent">${content_view.bContent}</textarea>

					 <script>
					 	CKEDITOR.replace('bContent',{filebrowserUploadUrl:'${pageContext.request.contextPath}/img/imageUpload.do'});
					 </script>
            	
            </td>
         </tr>
         <tr>
         	<td> 이미지 </td>
         	<td class= "form-group"> 
         		<c:forEach var="file" items="${file}" >
	  			 
	  			 <img src="/filePath/${file.STORED_FILE_NAME}"  style="width: 100%; height: auto;"></img>
	  			
			</c:forEach>
			
         
         </tr>
         <tr >
            <td colspan="2"> <input type="submit" value="수정"> &nbsp;&nbsp; 
            <a href="list">목록보기</a> &nbsp;&nbsp; 
            <a href="delete?bId=${content_view.bId}">삭제</a> &nbsp;&nbsp; 
            <a href="reply_view?bId=${content_view.bId}">답변</a></td> <!-- //몇번에 답변을 달것인가? -->
         </tr>
      </form>
   </table>
   
   <!--댓글 목록  -->
       <tr>
	    <div id="reply">
		  <ol class="replyList">
		    <c:forEach items="${replyList}" var="replyList">
		      <li>
		        <p>
			        작성자 : ${replyList.writer}<br />
			       작성 날짜 : ${replyList.regDate}
		        </p>
		
		        <p>${replyList.content}</p>
		      </li>
		    </c:forEach>   
		  </ol>
		</div>
		</tr> 
		
	<!--댓글 작성 -->
	<table width="500" cellpadding="0" cellspacing="0" border="1" align="center">
		<form action="replyWrite" method="get">
		<input type="hidden" id="bId" name="bId" value="${content_view.bId}" />
			<tr>
				<td> 댓글 작성자 </td>
				<td> <input type="text" name="Writer" size = "50"> </td>
			</tr>
			<tr>
				<td> 댓글 내용 </td>
				<td> <textarea name="Content" rows="10" ></textarea> </td>
			</tr>
			
			<tr>
				<td colspan="2"> 
					<input type="submit" value="작성"> &nbsp;&nbsp;
				</td>
			</tr>
		</form>
	</table>
   
   
   </body>
</html>
