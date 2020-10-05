<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<html lang="ko">
<head>

   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
<style>
	body { font-family:'맑은 고딕', verdana; padding:0; margin:0; }
	ul { padding:0; margin:0; list-style:none;  }
 
	div#root { width:90%; margin:0 auto; }
	
	header#header { font-size:60px; padding:20px 0; }
	header#header h1 a { color:#000; font-weight:bold; }
	
	nav#nav { padding:10px; text-align:right; }
	nav#nav ul li { display:inline-block; margin-left:10px; }
 
 		section#container { padding:20px 0; border-top:2px solid #eee; border-bottom:2px solid #eee; }
	section#container::after { content:""; display:block; clear:both; }
	aside { float:left; width:200px; }
	div#container_box { float:right; width:calc(100% - 200px - 20px); }
	
	aside ul li { text-align:center; margin-bottom:10px; }
	aside ul li a { display:block; width:100%; padding:10px 0;}
	aside ul li a:hover { background:#eee; }
	
	footer#footer { background:#f9f9f9; padding:20px; }
	footer#footer ul li { display:inline-block; margin-right:10px; } 
</style>


<style>
.inputArea { margin:10px 0; }
select { width:100px; }
label { display:inline-block; width:70px; padding:5px; }
label[for='gdsDes'] { display:block; }
input { width:150px; }
textarea#gdsDes { width:400px; height:180px; }

.select_img img { margin:20px 0; }

</style>


</head>

<body>

<div id="root">
	<header id="header">
		<div id="header_box">
			<%@ include file="../include/header.jsp" %>
		</div>
	</header>

	<nav id="nav">
		<div id="nav_box">
			<%@ include file="../include/nav.jsp" %>
		</div>
	</nav>
	
	<section id="container">
		<aside>
			<%@ include file="../include/aside.jsp" %>
		</aside>
		<div id="container_box">
			<h2>상품 등록</h2>
			
			<form role="form" method="post" autocomplete="off">

				<input type="hidden" name="gdsNum" value="${goods.gdsNum}" />
				
				<div class="inputArea"> 
				 <label>1차 분류</label>
				 <select class="category1">
				  <option value="">전체</option>
				 </select>   
				 <label>2차 분류</label>
				 <select class="category2" name="cateCode">
				  <option value="">전체</option>
				 </select>
				</div>
				<div class="inputArea">
				 <label for="gdsName">상품명</label>
				 <input type="text" id="gdsName" name="gdsName" value="${goods.gdsName}"/>
				</div>
				<div class="inputArea">
				 <label for="gdsPrice">상품가격</label>
				 <input type="text" id="gdsPrice" name="gdsPrice" value="${goods.gdsPrice}"/>
				</div>
				<div class="inputArea">
				 <label for="gdsStock">상품수량</label>
				 <input type="text" id="gdsStock" name="gdsStock" value="${goods.gdsStock}"/>
				</div>
				<div class="inputArea">
				 <label for="gdsDes">상품소개</label>
				 <textarea rows="5" cols="50" id="gdsDes" name="gdsDes">${goods.gdsDes}</textarea>
				</div>
				<div class="inputArea">
				 <button type="submit" id="update_Btn" class="btn btn-primary">완료</button>
				 <button type="submit" id="back_Btn" class="btn btn-warning">취소</button>
					<script>
					 $("#back_Btn").click(function(){
					  //history.back();
					  location.href = "/ex/admin/goods/view?n=" + ${goods.gdsNum};
					 }); 
					 
					</script>		 
				</div>   
			</form>
		</div>
	</section>

	<footer id="footer">
		<div id="footer_box">
			<%@ include file="../include/footer.jsp" %>
		</div>		
	</footer>
</div>

 <script>

 var select_cateCode = '${goods.cateCode}';
 var select_cateCodeRef = '${goods.cateCodeRef}';
 var select_cateName = '${goods.cateName}';

 console.log("select_cateCode = " + select_cateCode);
 console.log("select_cateCodeRef = " + select_cateCodeRef);


 if(select_cateCodeRef != null && select_cateCodeRef != "") {
 	
 	console.log("값이 없으면");
 	
 	$(".category1").val(select_cateCodeRef);
 	$(".category2").val(select_cateCode);
 	$(".category2").children().remove();
 	$(".category2").append("<option value='"
 							+ select_cateCode + "'>" + select_cateName + "</option>");


 	
 	
 } else {
 	
 	console.log("값이 있으면");
 	
 	$(".category1").val(select_cateCode);
 	//$(".category2").val(select_cateCode);
 	$(".category2").append("<option value='"
 			+ select_cateCode + "' selected='selected'>전체</option>");
 }

</script> 





</body>
</html>
