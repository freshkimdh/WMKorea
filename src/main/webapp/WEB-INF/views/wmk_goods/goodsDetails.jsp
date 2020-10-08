<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- 시큐리티 전용 태그 -->



<!DOCTYPE html>
<html lang="en">

<head>
  <title>굿즈: 상세보기</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />


<%-- <style>

.out {
	width: 100%;
	text-align: center;

	border: 1px solid black;
	padding: 20px;
	margin: 15px;

}


.in {
	display: inline-block;
	width: 50%;
	border: 1px solid red;
	height: 100px;

}


</style> --%>

	<style>
   	.image-stack {
	  display: grid;
	  grid-template-columns: repeat(12, 1fr);
	  position: relative;
	}
	
/* 	   	.image-stack img {
    width: 100%;
     height: 100%;
  	} */

	
	.layer0 {
	grid-column: 1 / span 8;
	grid-row: 1; // make this image be on the same row
	  }	
	
	.layer1 {
	
	  grid-column: 11 / span 12;
      grid-row: 1; 
      padding-top: 350%; 
      z-index: 1;
	
	</style>
	
	



</head>

<body onload="init();">

<div id="root">
 	<header id="header">
		<div id="header_box">
			<%@ include file="include/header2.jsp" %>
		</div>
	</header>
	

 	<p>
	<p align="center" class="display-3" id="login_f">Goods Details</p><br>
	
		

	<div class="container">
	
		<div class="row">
				
			<div class="col-sm-6">
			
				<div class="image-stack col-sm-6" id="example"> <!-- character viewer -->
			
				<div class="layer0">	
				<img id="bg" alt="" src="goods/white_shirts_550.png">
				</div>
				
				<div class="layer1">
				<img id="faces" alt="" src="goods/profile.png" width="150">
				</div>
				
				
				</div>
		

			
					

<!--  			<img src="goods/white_shirts_550.png" class="img-fluid float-left" width="100">
			<img src="goods/black_shirts_550.png" class="img-fluid float-left" width="100">
			<img src="goods/gray_shirts_550.PNG" class="img-fluid float-left" width="100">  -->
				
	
			</div>
			
			<div class="col-sm-6">
			
			
			<h2>스트릿 반팔티셔츠 (남녀공용)</h2>
			<h5>(15,000원)</h5> <br>

			
			<label for="selDistrict">색상 선택:</label>
			<select class="form-control" id="sel1" name="selColor">
			<option>흰색 (White)</option>
			<option>검정 (Black)</option>
			<option>회색 (Gray)</option>
			</select>
			
			<br>
			
			<label for="selDistrict">사이즈 선택:</label>
			<select class="form-control" id="sel1" name="selSize">
			<option>M (100)</option>
			<option>L (105)</option>
			<option>XL (110)</option>
			</select>
			<br>
			
			<div class="number">
			<form name="form" method="get">
			수량 : <input type=hidden name="sell_price" value="15000"> 
			<input type="text" name="amount" value="1" size="3" onchange="change();">
			<input type="button" value=" + " onclick="add();">
			<input type="button" value=" - " onclick="del();"><br><br>
			
			
			
			금액 : <input type="text" name="sum" size="11" readonly>원
			</form>
			
			</div>
			
			
			
			<p>배송: 주문시 결제 (2,500원)</p>
			
			<a href="goodsOrder" class="btn btn-dark" role="button">구매하기</a>
			<a href="" class="btn btn-dark" role="button">장바구니 담기</a>
		
	
			</div>
			
		
		</div> <!-- row end -->

	</div> <!--  container end -->
	

	
	<div class="container">
	
	<hr>
		
		<br><br>
		<h3 align="center">이 곳은 제품의 상세정보가 들어갑니다.</h3>
		<br>
	
	</div>
	
	<br><br>

 	<footer id="footer">
		<div id="footer_box">
			<%@ include file="include/footer2.jsp" %>
		</div>		
	</footer>

</div>

	<script>
	
	var sell_price;
	var amount;
	
	function init () {
		sell_price = document.form.sell_price.value;
		amount = document.form.amount.value;
		document.form.sum.value = sell_price;
		change();
	}
	
	function add () {
		hm = document.form.amount;
		sum = document.form.sum;
		hm.value ++ ;
	
		sum.value = parseInt(hm.value) * sell_price;
	}
	
	function del () {
		hm = document.form.amount;
		sum = document.form.sum;
			if (hm.value > 1) {
				hm.value -- ;
				sum.value = parseInt(hm.value) * sell_price;
			}
	}
	
	function change () {
		hm = document.form.amount;
		sum = document.form.sum;
	
			if (hm.value < 0) {
				hm.value = 0;
			}
		sum.value = parseInt(hm.value) * sell_price;
	} 
	
	
	</script>

</body>
</html>
