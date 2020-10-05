<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!-- form 태그에 적용하는 JSTL -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  

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




</head>
<body onload="document.f.id.focus();">

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
				
		<li class="nav-item">
			<a class="nav-link" href="#">내 캐릭터 만들기</a>
		</li>
						
		<li class="nav-item">
			<a class="nav-link" href="list">커뮤니티</a>
		</li>
		
	</ul>
	
	</div>

<div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
 	<ul class="navbar-nav"> 
	<div class="btn-group btn-group-sm">
<!-- 	<a class="btn btn-dark">반갑습니다, 김대환 님!</a> -->
<sec:authorize access="isAnonymous()"> <!-- isAnonumous: 누구나 다 access 할 수있다 -->
   		<a href="loginForm" class="btn btn-secondary" type="button">Login</a>
		<a href="joinForm" class="btn btn-secondary" type="button">Join</a> 
		<a href="loginForm" class="btn btn-secondary" type="button">My Page</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
		<a class="btn btn-dark">환영합니다, <sec:authentication property="principal.user.nickname"/> 님!</a>
   		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
   			<input type="submit" class="btn btn-secondary btn-sm" value="Logout">
   		</form:form> 
		<a href="mypage" class="btn btn-secondary" type="button">My Page</a>
</sec:authorize>
	</div>

 	</ul>
</div>

</nav>



<p>
<p align="center" class="display-3" id="login_f">My Page</p> 


<div class="container">
	<div class="row">
	
	<div class="col-sm-2"></div>
	
	<div class="col-sm-8">	

<h1>마이페이지입니다.</h1>

<p>principal: <sec:authentication property="principal"/></p>
<p>UserVO: <sec:authentication property="principal.user"/></p> <!-- principal 에 emp 다는게 목적이었다. -->
<p>유저 아이디: <sec:authentication property="principal.user.id"/></p>
<p>유저 비밀번호: <sec:authentication property="principal.user.pw"/></p>
<p>유저 이메일: <sec:authentication property="principal.user.email"/></p>
<p>유저 국적: <sec:authentication property="principal.user.nationality"/></p>

<h3>[<a href="<c:url value="/index" />">메인으로</a>]</h3>
<h3>[<a href="<c:url value="/userPwModify" />">비밀번호 수정</a>]</h3>
<h3>[<a href="<c:url value="/userModify" />">회원정보 수정</a>]</h3>
<h3>[<a href="<c:url value="/userDeleteView" />">회원탈퇴</a>]</h3>


  
  	</div>
  	
  	<div class="col-sm-2"></div>
	</div>
</div>



<br><br>


<div class="jumbotron text-center" style="margin-bottom:0">
<p>Copyright © 2020 Wemade Korea All rights reserved</p>
</div>  


</body>
</html>