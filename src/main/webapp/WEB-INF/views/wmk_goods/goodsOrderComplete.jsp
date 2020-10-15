<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- 시큐리티 전용 태그 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="en">

<head>
  <title>굿즈: 구매하기</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />


</head>

<body onload="init();">

<div id="root">
	<header id="header">
		<div id="header_box">
			<%@ include file="include/header2.jsp" %>
		</div>
	</header>
	

 	<p>
	<p align="center" class="display-3" id="login_f">Goods Order</p>
	
		

	<div class="container">
		<img class="mx-auto d-block" src="goods/profile.png" width="150"><br>
		<h3 align="center"> <sec:authentication property="principal.user.nickname"/> 님의 주문이 완료되었습니니다.</h3>
		<p align="center">자세한 주문내역은 마이페이지에서 조회하실 수 있습니다.</p>
		
		<br>
		
<%-- 		<h5><strong>주문 정보</strong></h5> --%>
		

		
		  <table class="table">
		  
		  <tr align="center">
		      
		        <td><strong>주문 번호</strong></td>
		        <td><strong>총 결제 금액</strong></td>

		  </tr>	

		    <tbody>	    
		    
<c:forEach items="${orderList}" var="orderList" varStatus="last">		    
		      <tr align="center">

 		        <td>${orderList.orderId}</td>
		        <td>${orderList.amount} 원</td>

		      </tr>
</c:forEach>		    
		    </tbody>
		    
		  </table>
		  
		  
		  
		  
			<br>
			
			<table class="table">

		    <tbody>
		    
		      <tr>
		        <td align="center"><strong>입금은행</strong></td>
		        <td>우리은행</td>

		      </tr>		    
		    
		    
		      <tr>
		        <td align="center"><strong>입금 계좌</strong></td>
		        <td>1002 - 939 - 864616</td>
		      </tr>
		      
		      <tr>
		        <td align="center"><strong>입금 금액</strong></td>
		        <td>15000원</td>
		      </tr>

		      <tr>
		        <td align="center"><strong>입금 기한</strong></td>
		        <td>주문일로부터 3일 이내</td>
		      </tr>

		    
		    </tbody>
		    
		  </table>
		  
		  
			<br>
			
			
<p>
-입금 선택하신 은행을 확인하시고 입금해주세요.<br>
-입금자와 주문자명이 일치하여야 합니다. <br>
-입금시 은행 별 이체 수수료가 발생될 수 있습니다. (타행, 창구 영엉십간 외 이체 등) <br>
-은행에 따라 서비스 이용불가시간에는 입금이 어려우니, 입금 전 반드시 해당 은행의 서비스 이용시간을 확인 후 이용해주세요.</p>
			

	<br>
	<p align="center">
	<a href="" class="btn btn-dark btn-lg" role="button">쇼핑 계속하기</a>
	<a href="orderList" class="btn btn-dark btn-lg" role="button">주문내역 보기</a>
	</p>
	  
	</form>
	
<!-- <table border=1 width=230 height=60>
<tr>
<td valign=top>이름</td>
<td valign=middle>학년 </td>
<td valign=bottom>제목</td>
</tr>
</table> -->



	</div> <!--  container end -->


	
	<br><br>

 	<footer id="footer">
		<div id="footer_box">
			<%@ include file="include/footer2.jsp" %>
		</div>		
	</footer>

</div>



</body>
</html>
