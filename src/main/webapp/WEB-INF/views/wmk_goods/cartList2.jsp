<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<html lang="ko">
<head>

   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
<style>
#container_box table td { width:100px; }
</style>

<style>
 /*
 section#content ul li { display:inline-block; margin:10px; }
 section#content div.goodsThumb img { width:200px; height:200px; }
 section#content div.goodsName { padding:10px 0; text-align:center; }
 section#content div.goodsName a { color:#000; }
 */
 section#content ul li { margin:10px 0; }
 section#content ul li img { width:250px; height:250px; }
 section#content ul li::after { content:""; display:block; clear:both; }
 section#content div.thumb { float:left; width:250px; }
 section#content div.gdsInfo { float:right; width:calc(100% - 270px); }
 section#content div.gdsInfo { font-size:20px; line-height:2; }
 section#content div.gdsInfo span { display:inline-block; width:100px; font-weight:bold; margin-right:10px; }
 section#content div.gdsInfo .delete { text-align:right; }
 section#content div.gdsInfo .delete button { font-size:22px;
            padding:5px 10px; border:1px solid #eee; background:#eee;}
 
</style>


<!-- 장바구니 체크 부분 스타일 -->
<style>
.allCheck { float:left; width:200px; }
.allCheck input { width:16px; height:16px; }
.allCheck label { margin-left:10px; }
.delBtn { float:right; width:300px; text-align:right; }
.delBtn button { font-size:18px; padding:5px 10px; border:1px solid #eee; background:#eee;}

.checkBox { float:left; width:30px; }
.checkBox input { width:16px; height:16px; }

</style>

</head>

<body>

<div class="container">

<sec:authorize access="isAuthenticated()">
		<a>환영합니다, <sec:authentication property="principal.user.nickname"/> 님의 장바구니 입니다.</a>
</sec:authorize> <br>
<h2>나의 장바구니</h2> <br>

</div>

<div class="container" id="container_box">
 <ul>
 
 	<!--  장바구니 전체체크 -->
	 <li>
	  <div class="allCheck">
	   <input type="checkbox" name="allCheck" id="allCheck" /><label for="allCheck">모두 선택</label> 
	  </div>
	  
	  <!--  장바구니에서 상품 선택 후 삭제 -->
	  <div class="delBtn">
	  <a href="goodsList" class="btn btn-dark" role="button">쇼핑 계속하기</a>
	   <button type="button" class="selectDelete_btn" align="right">선택 삭제</button> 
	   
		<script>
			 $(".selectDelete_btn").click(function(){
			  var confirm_val = confirm("정말 삭제하시겠습니까?");
			  
			  if(confirm_val) {
			   var checkArr = new Array();
			   
			   $("input[class='chBox']:checked").each(function(){
			    checkArr.push($(this).attr("data-cartNum"));
			   });
			    
			   $.ajax({
			    url : "${pageContext.request.contextPath}/shop/cartList/deleteCart",
			    type : "post",
			    data : { chbox : checkArr },
			    success : function(result){
			    	
			    if(result == 1 ){	
			     location.href = "${pageContext.request.contextPath}/cartList";
			    } else {
			    	/* alert("삭제 실패"); */
			    	location.href = "${pageContext.request.contextPath}/cartList";
			    }

			     
			    }
			   });
			  } 
			 });
		</script>
	   
	  </div>
	  
	<script>
	$("#allCheck").click(function(){
	 var chk = $("#allCheck").prop("checked");
	 if(chk) {
	  $(".chBox").prop("checked", true);
	 } else {
	  $(".chBox").prop("checked", false);
	 }
	});
	</script>
	  
	 </li>
 
  <hr>
  
  <c:forEach items="${cartList}" var="cartList">
  <li>
  
  <!-- 장바구니 단일 체크 -->
	<div class="checkBox">
   <input type="checkbox" name="chBox" class="chBox" data-cartNum="${cartList.cartNum}" />
   
   <script>
	 $(".chBox").click(function(){
	  $("#allCheck").prop("checked", false);
	 });
	</script>
  </div>
  
  
   <div class="thumb">
    <img src="${pageContext.request.contextPath}/${cartList.gdsImg}" width="150"/>
   </div>
   <div class="gdsInfo">
    <p>
     <span>상품명 : </span>${cartList.gdsName}<br />
     <span>개당 가격 : </span><fmt:formatNumber pattern="###,###,###" value="${cartList.gdsPrice}" /> 원 <br />
     <span>선택수량 : </span>${cartList.cartStock}<br />
     <span>최종 가격 : </span><fmt:formatNumber pattern="###,###,###" value="${cartList.gdsPrice * cartList.cartStock}" /> 원
    </p>
    
    <div class="delete">
     <button type="button" class="delete_${cartList.cartNum}_btn" data-cartNum="${cartList.cartNum}">삭제</button> <br>
		
		<script>
		  $(".delete_${cartList.cartNum}_btn").click(function(){
		   var confirm_val = confirm("정말 삭제하시겠습니까?");
		   
		   if(confirm_val) {
		    var checkArr = new Array();
		    
		    checkArr.push($(this).attr("data-cartNum"));
		               
		    $.ajax({
		     url : "${pageContext.request.contextPath}/shop/cartList/deleteCart",
		     type : "post",
		     data : { chbox : checkArr },
		     success : function(result){
		    	 
			    if(result == 1 ){	
				     location.href = "${pageContext.request.contextPath}/cartList";
				    } else {
				    	/* alert("삭제 실패"); */
				    	location.href = "${pageContext.request.contextPath}/cartList";
				    }
		      
		     }
		    });
		   } 
		  });
		 </script>
     
    </div>
        
    
   </div>
   <hr>
   <br>
   
  </li>
  </c:forEach>
 </ul>
</div>
</body>
</html>


