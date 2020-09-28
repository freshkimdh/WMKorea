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
        	<a class="dropdown-item" href="/ex">굿즈</a>
      		</div>
		</li>
		
		<!-- Dropdown -->				
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
			커뮤니티
			</a>
			<div class="dropdown-menu">
        	<a class="dropdown-item" href="boardList">자유 게시판</a>
        	<a class="dropdown-item" href="#">여행지 게시판</a>
        	<a class="dropdown-item" href="${pageContext.request.contextPath}/list">테스트용 게시판</a>
      		</div>
		</li>
		
	</ul>
	
	</div>

<div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
 	<ul class="navbar-nav"> 
	<div class="btn-group btn-group-sm">
	
<sec:authorize access="isAnonymous()"> <!-- isAnonumous: 누구나 다 access 할 수있다 -->
   		<a href="loginForm" class="btn btn-secondary" type="button">Login</a>
		<a href="joinForm" class="btn btn-secondary" type="button">Join</a> 
		<a href="http://google.com" class="btn btn-secondary" type="button">My Page</a>
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


<div id="demo" class="carousel slide" data-ride="carousel">

  <!-- Indicators -->
  <ul class="carousel-indicators">
    <li data-target="#demo" data-slide-to="0" class="active"></li>
     <li data-target="#demo" data-slide-to="1"></li>
     <li data-target="#demo" data-slide-to="2"></li>
  </ul>
  
  <!-- The slideshow -->
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="img/main_carousel_1_text.jpg" alt="main_carousel_1" width="800" height="250">
    </div>
    <div class="carousel-item">
      <img src="img/main_carousel_2.jpg" alt="Chicago" width="800" height="250">
    </div>
    <div class="carousel-item">
      <img src="img/main_carousel_3.jpg" alt="New York" width="800" height="250">
    </div> 
  </div>
  
  <!-- Left and right controls -->
  <a class="carousel-control-prev" href="#demo" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div>



<p>
<div class="container">
<p align="center" class="display-3" id="disf">City Display</p>
</div><br>






<div class="container" id="district">
  <div class="row">
	
	<div class="col-sm-4">
	
	<div class="img-container" onclick="location.href='seoulList'">
	<p align="center"><a href="http://google.com"><img src="img/seoul_icon.jpg" class="img-fluid mx-auto d-block img-thumbnail" width="400px" height="300px"></a></p><p>
		<div class="overlay">
    	<span><h5>자세히 보기</h5></span>
    	</div>
	</div>
	
      <h4 align="center">서울 (Seoul)</h4>
      <p align="center">Hi Seoul SOUL OF ASIA<br>대한민국의 수도이자 광역자치단체</p>
      <p align="center"><a href="https://www.seoul.go.kr/main/index.jsp" class="btn btn-success btn-sm" role="button" align="center">공식 사이트 &raquo;</a></p>
   		
   	<br>
	</div>
    
    
   	<div class="col-sm-4">
   	<div class="img-container">
	<img src="img/gyeonggi_icon.jpg" class="img-fluid mx-auto d-block img-thumbnail" width="400px" height="300px"><p>
  			<div class="overlay">
    		<span><h5>자세히 보기</h5></span>
    		</div>
  	
  	</div>   
      
      <h4 align="center">경기 (Gyeonggi)</h4>
      <p align="center">정치·경제·문화의 미래를 만드는 곳<br>대한민국을 넘어 글로벌 스탠다드를 만드는 경기</p>
      <p align="center"><a href="https://www.gg.go.kr/" class="btn btn-success btn-sm" role="button" align="center">공식 사이트 &raquo;</a></p>
    </div>  
  	
  	
  	<div class="col-sm-4">
  	<div class="img-container">
    <img src="img/incheon_icon.jpg" class="img-fluid mx-auto d-block img-thumbnail" width="400px" height="300px"><p> <!-- img-fluid -->
      			<div class="overlay">
    		<span><h5>자세히 보기</h5></span>
    		</div>
    </div>
     
     
      <h4 align="center">인천 (Incheon)</h4>
      <p align="center">All ways INCHEON<br>살고 싶은 도시, 함께 만드는 인천</p>
      <p align="center"><a href="https://www.incheon.go.kr/index" class="btn btn-success btn-sm" role="button" align="center">공식 사이트 &raquo;</a></p>
    </div>
    
<!-- <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Second Line >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->    

    	<div class="col-sm-4">
    <div class="img-container">
	<img src="img/chung_icon.jpg" class="img-fluid mx-auto d-block img-thumbnail" width="400px" height="300px"><p>
       	<div class="overlay">
    	<span><h5>자세히 보기</h5></span>
    	</div>     
      
    </div>  
      
      <h4 align="center">충청 (Chungcheong)</h4>
      <p align="center">한반도의 지방으로, 서해를 끼고 있는 중남부 지역</p>
      <p align="center"><a href="https://www.seoul.go.kr/main/index.jsp" class="btn btn-success btn-sm" role="button" align="center">공식 사이트 &raquo;</a></p>
   	<br>
	</div>
    
    
   	<div class="col-sm-4">
   	<div class="img-container">
	<img src="img/gangwon_icon.jpg" class="img-fluid mx-auto d-block img-thumbnail" width="400px" height="300px"><p>
       	<div class="overlay">
    	<span><h5>자세히 보기</h5></span>
    	</div>      
    </div>  
      
      
      <h4 align="center">강원 (Gangwon)</h4>
      <p align="center">새밝의 예나라 정든 내 고장<br>아침 해 먼저 받은 우리 강원도</p>
      <p align="center"><a href="http://www.provin.gangwon.kr/gw/portal" class="btn btn-success btn-sm" role="button" align="center">공식 사이트 &raquo;</a></p>
      <br>
    </div>  
  	
  	
  	<div class="col-sm-4">
   	<div class="img-container">  	
    <img src="img/daejeon_icon.jpg" class="img-fluid mx-auto d-block img-thumbnail" width="400px" height="300px"><p> <!-- img-fluid -->
       	<div class="overlay">
    	<span><h5>자세히 보기</h5></span>
    	</div> 
     </div> 
      <h4 align="center">대전 (Daejeon)</h4>
      <p align="center">대덕연구단지, 국제과학비즈니스벨트가 조성된 한국 최대의 과학·연구도시</p>
      <p align="center"><a href="https://www.daejeon.go.kr/intro_main.jsp" class="btn btn-success btn-sm" role="button" align="center">공식 사이트 &raquo;</a></p>
      <br>
    </div>
    
<!-- <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Third Line >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->    

	<div class="col-sm-4">
	<img src="img/seoul_icon.jpg" class="img-fluid mx-auto d-block img-thumbnail" width="300px" height="300px"><p>
      <h4 align="center">서울 (Seoul)</h4>
      <p align="center">Hi Seoul SOUL OF ASIA<br>대한민국의 수도이자 광역자치단체</p>
      <p align="center"><a href="https://www.seoul.go.kr/main/index.jsp" class="btn btn-success btn-sm" role="button" align="center">공식 사이트 &raquo;</a></p>
   	<br>
	</div>
    
    
   	<div class="col-sm-4">
	<img src="img/gyeonggi_icon.jpg" class="img-fluid mx-auto d-block img-thumbnail" width="300px" height="300px"><p>
      <h4 align="center">경기 (Gyeonggi)</h4>
      <p align="center">정치·경제·문화의 미래를 만드는 곳<br>대한민국을 넘어 글로벌 스탠다드를 만드는 경기</p>
      <p align="center"><a href="https://www.gg.go.kr/" class="btn btn-success btn-sm" role="button" align="center">공식 사이트 &raquo;</a></p>
    </div>  
  	
  	
  	<div class="col-sm-4">
    <img src="img/incheon_icon.jpg" class="img-fluid mx-auto d-block img-thumbnail" width="300px" height="300px"><p> <!-- img-fluid -->
      <h4 align="center">인천 (Incheon)</h4>
      <p align="center">All ways INCHEON<br>살고 싶은 도시, 함께 만드는 인천</p>
      <p align="center"><a href="https://www.incheon.go.kr/index" class="btn btn-success btn-sm" role="button" align="center">공식 사이트 &raquo;</a></p>
    </div>
    
    
<!-- <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fourth Line >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> -->    

    	<div class="col-sm-4">
	<img src="img/chung_icon.jpg" class="img-fluid mx-auto d-block rounded-circle" width="300px" height="300px"><p>
      <h4 align="center">충청 (Chungcheong)</h4>
      <p align="center">한반도의 지방으로, 서해를 끼고 있는 중남부 지역</p>
      <p align="center"><a href="https://www.seoul.go.kr/main/index.jsp" class="btn btn-success btn-sm" role="button" align="center">공식 사이트 &raquo;</a></p>
   	<br>
	</div>
    
    
   	<div class="col-sm-4">
	<img src="img/gangwon_icon.jpg" class="img-fluid mx-auto d-block rounded-circle" width="300px" height="300px"><p>
      <h4 align="center">강원 (Gangwon)</h4>
      <p align="center">새밝의 예나라 정든 내 고장<br>아침 해 먼저 받은 우리 강원도</p>
      <p align="center"><a href="http://www.provin.gangwon.kr/gw/portal" class="btn btn-success btn-sm" role="button" align="center">공식 사이트 &raquo;</a></p>
      <br>
    </div>  
  	
  	
  	<div class="col-sm-4">
    <img src="img/daejeon_icon.jpg" class="img-fluid mx-auto d-block rounded-circle" width="300px" height="300px"><p> <!-- img-fluid -->
      <h4 align="center">대전 (Daejeon)</h4>
      <p align="center">대덕연구단지, 국제과학비즈니스벨트가 조성된 한국 최대의 과학·연구도시</p>
      <p align="center"><a href="https://www.daejeon.go.kr/intro_main.jsp" class="btn btn-success btn-sm" role="button" align="center">공식 사이트 &raquo;</a></p>
      <br>
    </div>
    
  	
  	
  
  

       
  </div>
</div>
<!-- <p>
<div class="container">
  <div class="row">
    <div class="col-sm-4">
      <h3>인천</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
    </div>
    <div class="col-sm-4">
      <h3>서울</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
    </div>
    <div class="col-sm-4">
      <h3>대전</h3>        
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
    </div>
  </div>
</div> -->
<br><br>
<br><br><br>


<div class="jumbotron text-center" style="margin-bottom:0">
<p>Copyright © 2020 Wemade Korea All rights reserved</p>
</div>  


</body>
</html>