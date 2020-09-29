<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<style>
	.paging {
	text-align: center;
	}
	</style>
	
	
</head>
<body>
   
   <table width="500" cellpadding="0" cellspacing="0" border="1">
      <tr>
         <td>번호</td>
         <td>이름</td>
         <td>제목</td>
         <td>날짜</td>
         <td>히트</td>
         <td>썸네일</td>
      </tr>
      <!-- 검색어의 값이 null일 경우  -->
      <c:if test="${boardByTitle eq null }">
	      <c:forEach items="${list}" var="dto">
	      <tr>
	         <td>${dto.bId}</td>
	         <td>${dto.bName}</td>
	         <td>
	            <c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
	            <a href="content_view?bId=${dto.bId}">${dto.bTitle}</a></td>
	         <td>${dto.bDate}</td>
	         <td>${dto.bHit}</td>
	         <td>
	       		<img src= "/filePath/${dto.storedFileName ne null? dto.storedFileName : 'avatar.png'}" style="width: 50px; height: 50px;"></img>
	      	 </td>
	      </tr>
	      </c:forEach>
      </c:if>
      
      <!-- 검색어가 null값이 아닐 경우 -->
      <c:if test="${boardByTitle ne null }">
	      <c:forEach items="${boardByTitle}" var="boardByTitle">
	      <tr>
	         <td>${boardByTitle.bId}</td>
	         <td>${boardByTitle.bName}</td>
	         <td>
	            <c:forEach begin="1" end="${boardByTitle.bIndent}">-</c:forEach>
	            <a href="content_view?bId=${boardByTitle.bId}">${boardByTitle.bTitle}</a></td>
	         <td>${boardByTitle.bDate}</td>
	         <td>${boardByTitle.bHit}</td>
	         <td>${boardByTitle.storedFileName}</td>
	      </tr>
	      </c:forEach>
      </c:if>
      <tr>
         <td colspan="5"> <a href="write_view">글작성</a> </td>
      </tr>
   </table>
   
      <c:if test="${pageMaker.prev}">
         <a href="list${pageMaker.makeQuery(pageMaker.startPage - 1) }">«</a>
      </c:if>

      <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
         <c:out value="${pageMaker.cri.pageNum == idx?'':''}" />
         <a href="list${pageMaker.makeQuery(idx)}">${idx}</a>
      </c:forEach>
      
      <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
         <a href="list${pageMaker.makeQuery(pageMaker.endPage +1) }"> » </a>
      </c:if> <br>
      
      <!-- <button type="submit">글쓰기</button> -->
   
   
   		<!-- 검색 -->
   		<form action="boardByTitle" method="get">

		    <select name="searchCondition" id="">
				
		        <option value="bTitle">제목</option>
		       
		    </select>
			    <input type="text" name="bTitle" id="bTitle" placeholder="검색어를 입력하세요">
				<input class="btn btn-sm btn-default" type="submit" value="검색">
			
		</form>
   
</body>
</html>