<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!-- view 부분이다 -->
<!DOCTYPE html>

<html>
   <head>
      <meta charset="UTF-8">
      <title>Content view</title>
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
            <td> <textarea rows="10" name="bContent" >${content_view.bContent}</textarea></td>
         </tr>
         <tr >
            <td colspan="2"> <input type="submit" value="수정"> &nbsp;&nbsp; 
            <a href="list">목록보기</a> &nbsp;&nbsp; 
            <a href="delete?bId=${content_view.bId}">삭제</a> &nbsp;&nbsp; 
            <a href="reply_view?bId=${content_view.bId}">답변</a></td> <!-- //몇번에 답변을 달것인가? -->
         </tr>
      </form>
   </table>
   
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
   
   
   </body>
</html>
