<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- 시큐리티 전용 태그 -->



<!DOCTYPE html>
<html lang="en">
<head>
  <title>Wemade Korea</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1"> <!-- 반응형에 반드시 필요한태그 -->
      
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
	
<!-- 	<style> /* Font responsive  */
	body {font-size: 16px;}
	#district {font-size: 1rem;}
	#disf {
	/* font-size: 4rem; */
	font-size: 4vw;}
	</style> -->
	
	<style>
	
  /* Make the image fully responsive */
  .carousel-inner img {
    width: 100%;
    height: 100%;
  }
  </style>
  
	<style>
 	#s1 {
	/* background: blue; */
	line-height:55px;
	}

 	#s1 img {
 	vertical-align:middle;
	}
	
	</style>
	
	
	<style>
	
	.img-container{
	position:relative;

	/*  display:table; */
	}
	.img-container img{
	/* display:block; */
	isplay: table-cell;
	text-align: center;
 	}
	.img-container .overlay{
	  position:absolute;
	  top:0;
	  left:0;
	  width:100%;
	  height:100%;
	  background:rgb(0,0,0);
	  opacity:0;
	  transition:opacity 500ms ease-in-out;
	}
	.img-container:hover .overlay{
	  opacity:60%;
	}
	.overlay span{
	  position:absolute;
	  top:50%;
	  left:50%;
	  transform:translate(-50%,-50%);
	  color:#fff;
	}
		
	</style>


		


</head>
</head>
<body>

<div class="container"> <!-- table -->
	<div class="row"> <!-- td -->
	
		<div class="col-sm-6" id="s1">
			<img src="img/main_logo2.png">
		</div> <!-- tr -->
		
		<div class="col-sm-6" id="s2">
			<!-- Search Bar -->
<!-- 			<p>Search Bar</p> -->
		<p>
 		<div class="input-group mb-3">
  			<input type="text" class="form-control" placeholder="Search">
  			<div class="input-group-append">
    			<button class="btn btn-danger" type="submit">Go</button>
  			</div>
		</div>
			<!-- Search Bar End -->
		</div>
	</div>
</div>

<!-- <nav class="navbar navbar-expand-md bg-light navbar-light sticky-top"> -->
<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
	
	<!-- Brand Logo -->
	<a class="navbar-brand" href="#">MENU</a>
	
	<!-- Toggler -->
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	
	<!-- Content -->
	<div class="collapse navbar-collapse" id="collapsibleNavbar">
	<ul class="navbar-nav">

		<li class="nav-item">
			<a class="nav-link" href="index">메인</a>
		</li>
				
		<li class="nav-item">
			<a class="nav-link" href="#">핫플레이스</a>
		</li>
				
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
			내 캐릭터 만들기
			</a>
			<div class="dropdown-menu">
        	<a class="dropdown-item" href="boardList">내 캐릭터 만들기</a>
        	<a class="dropdown-item" href="#">굿즈</a>
      		</div>
		</li>
		
		<!-- Dropdown -->				
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
			커뮤니티
			</a>
			<div class="dropdown-menu">
        	<a class="dropdown-item" href="boardList">자유 게시판</a>
        	<a class="dropdown-item" href="#">여행후기 게시판</a>
        	<a class="dropdown-item" href="${pageContext.request.contextPath}/list">테스트용 게시판</a>
      		</div>
		</li>
		
	</ul>
	
	</div>

<div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
 	<ul class="navbar-nav"> 
	<div class="btn-group btn-group-sm">
	
<sec:authorize access="isAnonymous()"> <!-- isAnonumous: 누구나 다 access 할 수있다 -->
   		<a href="${pageContext.request.contextPath}/loginForm" class="btn btn-secondary" type="button">Login</a>
		<a href="${pageContext.request.contextPath}/joinForm" class="btn btn-secondary" type="button">Join</a> 
		<a href="http://google.com" class="btn btn-secondary" type="button">My Page</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
		<a class="btn btn-dark">환영합니다, <sec:authentication property="principal.user.nickname"/> 님!</a>
   		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
   			<input type="submit" class="btn btn-secondary btn-sm" value="Logout">
   		</form:form> 
		<a href="${pageContext.request.contextPath}/mypage" class="btn btn-secondary" type="button">My Page</a>
</sec:authorize>

	</div>

 	</ul>
</div>

</nav>






<p>
<div class="container">
<p align="center" class="display-3" id="disf">Godds Register</p>
</div>


<div class="container">
  <h2>제품 등록(관리자 페이지)</h2> <br>

	<form role="form" method="post" autocomplete="off">
	
	<div class="row">
	
		
		<div class="col-sm-6">
		
		<label for="category1">1차 분류:</label>
		<select class="form-control category1" id="sel1" name="selDistrict">
		<option>전체</option>
		</select>
		
		</div>
		
		<div class="col-sm-6">
		
		<label for="category1">2차 분류:</label>
		<select class="form-control category2" id="sel1" name="cateCode">
		<option value="101">전체</option>
		</select>
		
		</div>
			 
		<div class="col-sm-6">

    	<div class="form-group">
    	<label for="gdsImg">상품 이미지 (목록 노출):</label>
    	<input type="text" class="form-control" id="gdsImg" placeholder="" value="goods/t-shirts.png" name="gdsImg">
    	</div>
    	
    	</div>

		<div class="col-sm-6">
		
    	<div class="form-group">
		<label for="gdsName">상품명 (목록 노출):</label>
      	<input type="text" class="form-control" id="gdsName" placeholder="" value="T-shirts" name="gdsName">
    	</div>
    	
    	
    	</div>
    	
    	
    	<div class="col-sm-6">
    	
    	<div class="form-group">
    	<label for="gdsPrice">가격:</label>
    	<input type="text" class="form-control" id="gdsPrice" placeholder="" value="15000" name="gdsPrice">
    	</div>
    	
    	
    	</div>
    	
    	<div class="col-sm-6">
	    	<div class="form-group">
	    	<label for="gdsStock">상품 수량:</label>
	    	<input type="text" class="form-control" id="gdsStock" placeholder="" value="50" name="gdsStock">
	    	</div>
    	</div>
    	
    	<div class="col-sm-12">
    	
    	<div class="form-group">
	      <label for="gdsDes">상품 소개 (목록 노출):</label>
	      <textarea class="form-control" rows="10" id="gdsDes" name="gdsDes" placeholder="스타일과 편안함을 모두 갖춘 프리미엄 원단 100% 티셔츠">스타일과 편안함을 모두 갖춘 프리미엄 원단 100% 티셔츠</textarea>
    	</div>
    	
    	<p align="center"><button type="submit" id="register_Btn" class="btn btn-dark">등록</button> 
		<a href="goodsList" class="btn btn-dark" role="button" onclick="return confirm('목록으로 돌아가겠습니까?');">취소</a></p>
	
    	</div>
    	

	

	</div> <!-- row end -->
	
	</form>
	
</div>
	
	<!-- Json 으로 컨트롤러 호출하여 카테고리 불러오기  -->
    	
	<script>
		// 컨트롤러에서 데이터 받기
		var jsonData = JSON.parse('${category}');
	
		// 필요한 배열과 오브젝트 변수 생성
		var cate1Arr = new Array();
		var cate1Obj = new Object();
	
		// 1차 분류 셀렉트 박스에 삽입할 데이터 준비
		for(var i = 0; i < jsonData.length; i++) {
			
			if(jsonData[i].level == "1") {  // 레벨이 1인 데이터가 있다면 
				cate1Obj = new Object();  // 초기화
				
				// cate1Obj에 cateCode와 cateName를 저장
				cate1Obj.cateCode = jsonData[i].cateCode; 
				cate1Obj.cateName = jsonData[i].cateName;
				
				// cate1Obj에 저장된 값을 cate1Arr 배열에 저장
				cate1Arr.push(cate1Obj);
			}
		}
		
		// 1차 분류 셀렉트 박스에 데이터 삽입
		var cate1Select = $("select.category1")

		for(var i = 0; i < cate1Arr.length; i++) {

			// cate1Arr에 저장된 값을 cate1Select에 추가
			cate1Select.append("<option value='" + cate1Arr[i].cateCode + "'>"
								+ cate1Arr[i].cateName + "</option>");	
		}
		
		
		$(document).on("change", "select.category1", function(){

			 var cate2Arr = new Array();
			 var cate2Obj = new Object();
			 
			 // 2차 분류 셀렉트 박스에 삽입할 데이터 준비
			 for(var i = 0; i < jsonData.length; i++) {
			  
			  if(jsonData[i].level == "2") {
			   cate2Obj = new Object();  //초기화
			   cate2Obj.cateCode = jsonData[i].cateCode;
			   cate2Obj.cateName = jsonData[i].cateName;
			   cate2Obj.cateCodeRef = jsonData[i].cateCodeRef;
			   
			   cate2Arr.push(cate2Obj);
			  }
			 }
			 
			 var cate2Select = $("select.category2");
			 
			 /*
			 for(var i = 0; i < cate2Arr.length; i++) {
			   cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>"
			        + cate2Arr[i].cateName + "</option>");
			 }
			 */
			 
			 cate2Select.children().remove();

			 $("option:selected", this).each(function(){
			  
			  var selectVal = $(this).val();  
			  cate2Select.append("<option value='" + selectVal + "'>전체</option>");
			  
			  for(var i = 0; i < cate2Arr.length; i++) {
			   if(selectVal == cate2Arr[i].cateCodeRef) {
			    cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>"
			         + cate2Arr[i].cateName + "</option>");
			   }
			  }
			  
			 });
			 
			});
		
		
		
	</script>









<div class="jumbotron text-center" style="margin-bottom:0">
<p>Copyright © 2020 Wemade Korea All rights reserved</p>
</div>  


</body>
</html>