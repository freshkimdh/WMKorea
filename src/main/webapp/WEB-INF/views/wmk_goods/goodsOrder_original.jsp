<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- 시큐리티 전용 태그 -->



<!DOCTYPE html>
<html lang="en">

<head>
  <title>굿즈: 구매하기</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


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
		<h3 align="center"> 김대환 님이 '스트릿 반팔티셔츠 (남녀공용)' 의 상품을 주문합니다.</h3>
		
		<hr>
		
		<br>
		
		<h5><strong>배송지 정보</strong></h5>
		
		<form action="">
		
		  <table class="table">

		    <tbody>
		    
		      <tr>
		        <td>회원 ID</td>
		        <td>abcde</td>

		      </tr>		    
		    
		    
		      <tr>
		        <td>주문자 (입금자명)</td>
		        <td><input type="text" class="form-control" id="name" name="name"></td>

		      </tr>
		      <tr>
		        <td>연락처</td>
		        <td><input type="text" class="form-control" id="phone" name="phone"></td>

		      </tr>
		      <tr>
		        <td>주소</td>
		        <td><input type="text" class="form-control" id="adress" name="adress"></td>

		      </tr>
		      
				<tr>
		        <td>배송메모</td>
		        <td><input type="text" class="form-control" id="adress" name="adress" placeholder="예: 부재시 경비실에 맡겨주세요."></td>

		      </tr>
		      
		    </tbody>
		  </table>
		  
		  
			<br>
			
			<h5><strong>주문상품 정보</strong></h5>
		  <table class="table">

		    <tbody>
		    
		      <tr>
		        <td align="center">상품 정보</td>
		        <td>상품 금액</td>
		        <td>할인 금액</td>
		        <td>배송비</td>
		        <td>주문 금액</td>
		        

		      </tr>		    
		    
		    
		      <tr>
		      	<td>
		      	
		      	<div class="container">
		      		<div class="row">
		      		<div class="col-sm-3">
		      		<img src="goods/white_shirts_550.png" class="mx-auto d-block" width="100">
		      		</div>
		      		
					<div class="col-sm-7">
		      		<h5>스트릿 반팔티셔츠 (남녀공용)</h5>
		      		색상: 흰색  / 사이즈: M / 수량: 1개
		      		</div>
		      	
		      		</div>
		      	</div>
		      	
		      	</td>
		      
		        <td>15000 원</td>
		        <td>0 원</td>
		        <td>무료 배송</td>
		        <td>15000 원</td> 
		      </tr>
		    </tbody>
		    
		  </table>
		  
			<br>
			
		<h5><strong>결제방법 선택</strong></h5>
		  <table class="table">

		    <tbody>
		    
		      <tr>
		        <td>결제수단 선택</td>
		        
		        <td>
			    <div class="form-check-inline">
			      <label class="form-check-label" for="radio1">
			        <input type="radio" class="form-check-input" id="radio1" name="optradio" value="option1" checked>계좌이체
			      </label>
			    </div>
			    <div class="form-check-inline">
			      <label class="form-check-label" for="radio2">
			        <input type="radio" class="form-check-input" id="radio2" name="optradio" value="option2">무통장 입금
			      </label>
			    </div>
			    <div class="form-check-inline">
			      <label class="form-check-label">
			        <input type="radio" class="form-check-input" disabled>핸드폰 결제
			      </label>
			    </div>
		        </td>
		        
		      </tr>
		      
		      <tr>
		      	<td>계좌 정보</td>
		      	<td>1002 - 939 - 864616 (우리은행, 정경채)</td>
		      
		      </tr>		    
		      
		    </tbody>
		    
		  </table>
	<br>
	<p align="center">
	<a href="goodsOrderComplete" class="btn btn-dark btn-lg" role="button">주문하기</a>
	<a href="goodsIndex2" class="btn btn-dark btn-lg" role="button">취소</a>
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
