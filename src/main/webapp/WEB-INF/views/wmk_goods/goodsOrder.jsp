<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- 시큐리티 전용 태그 -->



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
	<p align="center" class="display-3" id="login_f">Goods Order</p><br>
	
		

	<div class="container">
	
		<h3 align="center"> 김대환 님이 '스트릿 반팔티셔츠 (남녀공용)' 의 상품을 주문합니다.</h3>
		
		<br>
    
		  <table class="table">
		    <thead>
		      <tr>
		        <th><strong>배송지 정보</strong></th>
		        <th></th>

		      </tr>
		    </thead>
		    <tbody>
		    
		      <tr>
		        <td>회원 ID</td>
		        <td>abcde</td>

		      </tr>		    
		    
		    
		      <tr>
		        <td>주문자(입금자명)</td>
		        <td><input type="text" class="form-control" id="usr" name="username"></td>

		      </tr>
		      <tr>
		        <td>Mary</td>
		        <td>Moe</td>

		      </tr>
		      <tr>
		        <td>July</td>
		        <td>Dooley</td>

		      </tr>
		    </tbody>
		  </table>

	

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
