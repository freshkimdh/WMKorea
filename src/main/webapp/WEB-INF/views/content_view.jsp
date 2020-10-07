<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!-- view 부분이다 -->
<!DOCTYPE html>

<html>
   <head>
      <meta charset="UTF-8">
      <title>Content view</title>
      
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script type="text/javascript">
/*    $.ajax({
      url : '서비스 주소'
         , data : '서비스 처리에 필요한 인자값'
         , type : 'HTTP방식' (POST/GET 등)
         , dataType : 'return 받을 데이터 타입' (json, text 등)
         , success : function('결과값'){
         // 서비스 성공 시 처리 할 내용
         }, error : function('결과값'){
         // 서비스 실패 시 처리 할 내용
         }
      }); */
      
      
      function reply() {
         // var url = "${pageContext.request.contextPath}/replyTest.json";
           //url = "http://192.168.6.10:8282/board/rest/after.json";
           
           
      	var form = {
         bId : $('#bId').val(),
         Writer: $('#Writer').val(), //아래 id로 명시된 값을 가져 옴
         Content: $('#Content').val() 
      	}
      	
       //ajax 기본 문법
          $.ajax({
              url: 'comment',
              type: 'POST', //무조건 대문자 넣어줘야 한다. 
              //dataType: 'json', //통신에서 내가 데이터를 받을꺼, 받는 타입이 들어감
              data: JSON.stringify(form),        
              contentType: 'application/json', //리턴 받을때 JSON 형식으로 받겠다고 선언
              
              success: function(data){ //배열안에 객체가 리스트 갯수만큼 쭉 있는것이다. //배열이니깐 배열로 돌린다.
            	  
            	 
              
              
              var writer = $("#Writer").val();
              var content = $("#Content").val();
              
              html += '<tr>';
        	  html += '<td>'+ writer + '</td>';
        	  html += '<td>'+ content + '</td>';
        	  html +='<tr>'; 
              
              $("#list-table").append(html) 
           
             
              
              
/*          	  $.each(data, function() {
         	     	$('#list-table').append(
         	     			"<p>" + this.writer + "</p>" +
         	     			"<p>" + this.content + "</p>"
         	     			);
         	     	
         	     });$("#list-table").append(html); */
              
              
      /*         $("<tr>" , {
                  html : "<td>" + "댓글 작성자" + "</td>"+  // 컬럼명들
                        "<td>" + "댓글 내용" + "</td>"            
               }).appendTo("#list-table") 
	              
                                  
	             	var writer = $("#Writer").val();
	              	var content = $("#Content").val();
	              	
	              	
		            
		            	  htmls += '<tr>';
		            	  htmls += '<td>'+ writer + '</td>';
		            	  htmls += '<td>'+ content + '</td>';
		            	  htmls +='<tr>'; 
		         
              
	            	                 
	              $("#list-table").append(htmls);  */
              
              }//success end

         });//ajax end
   	} // reply() method end
      
   </script>
   
      
      
      
      
   </head>
   
   <body>
     <table width="500" cellpadding="0" cellspacing="0" border="1" align="center">
      <form action="goToModify" method="get">      
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
            	${content_view.bContent} 
            	<textarea style="display: none;"  name="bContent" >${content_view.bContent}</textarea>
            </td>
         </tr>
         <tr >
            <td colspan="2"> <input type="submit" value="수정"> &nbsp;&nbsp; 
            <a href="list">목록보기</a> &nbsp;&nbsp; 
            <a href="delete?bId=${content_view.bId}">삭제</a> &nbsp;&nbsp; 
            <a href="reply_view?bId=${content_view.bId}">답변</a></td> <!-- //몇번에 답변을 달것인가? -->
         </tr>
      </form>
   </table>
   <br>
   <br>
   <br>
   
   
   <!--댓글 목록  -->
   
   <table id="list-table" width="500" cellpadding="0" cellspacing="0" border="1">
  
	  
	  	    <c:forEach items="${replyList}" var="replyList">
		   	
		   	<p>        
		       작성자: ${replyList.writer}<br/>
			작성내용: ${replyList.content}
			</p>
			<p>작성날짜: ${replyList.regDate}</p>
			
			<br/>

		  	</c:forEach>     
	</table> 

	<!--댓글 목록  -->
<!-- 	<table border="1" width="800" height="200" align="center" id="list-table">
	
		<thead>
			<tr bgcolor='gray'>		
				<th>댓글 작성자</th>
				<th>댓글 내용</th>
			</tr>
		</thead>
		
		<tbody id="list-body" align="center">
			<tr>
				<td>작성자</td>
				<td>내용</td>
			</tr>
		
		</tbody>
	</table> -->
	
	
	
	<!--댓글 작성 -->
	<table width="500" cellpadding="0" cellspacing="0" border="1" align="center">
		
		<input type="hidden" id="bId" name="bId" value="${content_view.bId}" />
			<tr>
				<td> 댓글 작성자 </td>
				<td> <input type="text" id="Writer" name="Writer" size = "50"> </td>
			</tr>
			<tr>
				<td> 댓글 내용 </td>
				<td> <textarea name="Content" id="Content" rows="10" ></textarea> </td>
			</tr>
			
			<tr>
				<td colspan="2"> 
					<a href="javascript:reply();">작성</a>
				</td>
			</tr>
		
	</table>
	

   
   
   
   
   
   
   </body>
</html>
