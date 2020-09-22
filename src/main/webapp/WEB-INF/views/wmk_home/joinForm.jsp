<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
  		<a href="loginForm" class="btn btn-secondary" type="button">Login</a>
		<a href="joinForm" class="btn btn-secondary" type="button">Join</a>
		<a href="http://google.com" class="btn btn-secondary" type="button">My Page</a>
	</div>

 	</ul>
</div>

</nav>



<p>
<p align="center" class="display-3" id="login_f">Join</p>


<div class="container">
	<div class="row">
<div class="col-sm-2">

</div>

	
<div class="col-sm-8">


<form:form name="join_member" action="addUser" method="POST">
  
  	<div class="form-group">
  		<label for="id">아이디 (ID):</label>
  		<input type="text" class="form-control" id="id" name="id">
	</div>
	<div class="form-group">
  		<label for="pw">비밀번호 (Password):</label>
  		<input type="password" class="form-control" id="pw" name="pw">
	</div>
	
	 <div class="form-group">
  		<label for="nickname">닉네임 (Signature Name):</label>
  		<input type="text" class="form-control" id="nickname" name="nickname">
	</div>
	
	
     <div class="form-group">
      <label for="email">이메일 (Email):</label>
      <input type="email" class="form-control" id="email" placeholder="" name="email">
    </div>
    
<!--     <tr>
          <th>이메일</th>
          <td>
            <input type='text' name="email">@
            <input type='text' name="email_dns">
              <select name="emailaddr">
                 <option value="">직접입력</option>
                 <option value="daum.net">daum.net</option>
                 <option value="empal.com">empal.com</option>
                 <option value="gmail.com">gmail.com</option>
                 <option value="hanmail.net">hanmail.net</option>
                 <option value="msn.com">msn.com</option>
                 <option value="naver.com">naver.com</option>
                 <option value="nate.com">nate.com</option>
              </select>
            </td>
         </tr>
<br><br> -->
    
  
    <div class="form-group">
      <label for="nationality">국적 (Nationality):</label>
      <select class="form-control" id="nationality" name="nationality">
        <option>한국 (Republic of Korea)</option>
        <option>일본 (Japan)</option>
        <option>중국 (China)</option>
        <option>미국 (United States)</option>
        <option>기타 (Others)</option>
      </select>
      

    </div>
    <button type="submit" class="btn btn-danger">Submit</button>
    
 </form:form>
 
</div>
</div>
</div>

<div class="col-sm-2">

</div>

<br><br>


<div class="jumbotron text-center" style="margin-bottom:0">
<p>Copyright © 2020 Wemade Korea All rights reserved</p>
</div>  


</body>
</html>