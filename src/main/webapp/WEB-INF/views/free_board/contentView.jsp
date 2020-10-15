<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- 시큐리티 전용 태그 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



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
	
	<!-- Banner style tag -->
	<style>
	body, html {
  height: 100%;
  margin: 0;
/*    font-family: Arial, Helvetica, sans-serif; */
}

.hero-image {
  background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("img/banner2.jpg");
  height: 20%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

.hero-text {
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
}
	</style>
	
	<style>
	 section.replyForm { padding:30px 0; }
	 section.replyForm div.input_area { margin:10px 0; }
	 section.replyForm textarea { font-size:16px; font-family:'맑은 고딕', verdana; padding:10px; width:500px;; height:150px; }
	 section.replyForm button { font-size:20px; padding:5px 10px; margin:10px 0; background:#fff; border:1px solid #ccc; }
	 
	 section.replyList { padding:30px 0; }
	 section.replyList ol { padding:0; margin:0; }
	 section.replyList ol li { padding:10px 0; border-bottom:2px solid #eee; }
	 section.replyList div.userInfo { }
	 section.replyList div.userInfo .userName { font-size:24px; font-weight:bold; }
	 section.replyList div.userInfo .date { color:#999; display:inline-block; margin-left:10px; }
	 section.replyList div.replyContent { padding:10px; margin:20px 0; }
	 
	 section.replyList div.replyFooter button { font-size:14px; border: 1px solid #999; background:none; margin-right:10px; }
	</style>



  <script> 
  //댓글 삭제
  function replyList() {
	 var fBoard_Num = ${contentView.fBoard_Num};
	 $.getJSON("${pageContext.request.contextPath}/free_contentView/replyList" + "?n=" + fBoard_Num, function(data){
	  var str = "";
	  
	  $(data).each(function(){
	   
	   console.log(data);
	   
	   var repDate = new Date(this.repDate);
	   repDate = repDate.toLocaleDateString("ko-US")
	   
	   str += "<div>"
	     + "<div class='userInfo'>"
	     + "<span class='id'>" + this.id + "</span>"
	     + "<span class='date'>" + repDate + "</span>"
	     + "</div>"
	     + "<div class='replyContent'>" + this.repCon + "</div>"
	     + "</div>"
	     
	     
	     + "<div class='replyFooter'>"
	     + "<button type='button' class='delete' data-repNum='" + this.repNum + "'>삭제</button>"
	     + "</div>"
	     
	     + "<hr>"
	     
	     ;           
	  });
	  
	  $("section.replyList ol").html(str);
	 });
  }
	</script>
	


</head>

	<script type="text/javascript">
		$(document).ready(function(){
			
			//댓글작성
			$(".replyWriteBtn").on("click", function(){
				  var formObj = $("form[name='replyForm']");
				  formObj.attr("action", "replyWrite");
				  formObj.submit();
			});		
		})
	</script>



<body>

<div class="container"> <!-- table -->
	<div class="row"> <!-- td -->
	
		<div class="col-sm-6" id="s1">
			<a href="index"><img src="img/main_logo2.png"></a>
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
        	<a class="dropdown-item" href="free_boardList">자유 게시판</a>
        	<a class="dropdown-item" href="areaIndex">여행후기 게시판</a>
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

<!-- This is banner -->
<!-- <div class="hero-image">
  <div class="hero-text">
<p style="font-size:60px" class="display-3">Community</p>
	<p>We Make Our World</p>
  </div>
</div>
<p><br> -->


<p>
<div class="container">
<p align="center" class="display-3" id="disf">Community</p>
</div>

<div class="container" id="free_board">
  <h2>자유게시판</h2> <br>
  
 
 	
 <!-- 게시글 내용 부분 -->
<table class="table">
    <thead>
	<tr>
		<th>글 제목: ${contentView.fTitle}</th>
	</tr>
    </thead>

    <tbody>
      <tr>
        <td>
        <p><span class="badge badge-pill badge-secondary">No</span> ${contentView.fBoard_Num}
        <span class="badge badge-pill badge-secondary">조회수</span> ${contentView.fHit}
        </p>
        
		<div class="card">
			<div class="card-body">
	        	<div class="row">
        			<div class="col-md-2">
        	       		<img src="img/avatar2.jpg" class="img img-rounded img-fluid"/>
        	    	</div>
        	    
        	     	<div class="col-md-10">
        	     		<strong>작성자: ${contentView.fId}</strong> <br>
						<p>글 내용: ${contentView.fContent}</p>
						<br><p align="right">
						<p class="text-dark" align="right"><fmt:formatDate value="${contentView.fDate}" pattern="yyyy-MM-dd"/></p>	
					</div>
	       	 	</div>	
			</div>	
		</div>
        
        </td>
      </tr>
    </tbody>
</table>
  
  <!-- <hr> -->
 
<p align="right">
<a href="free_modifyView?fBoard_Num=${contentView.fBoard_Num}" class="btn btn-outline-dark btn-sm" role="button" >수정</a>
<a href="free_delete?fBoard_Num=${contentView.fBoard_Num}" class="btn btn-outline-dark btn-sm" role="button">삭제</a>
</p>
 
<hr> <br>


<button type="button" class="boardDelete" data-fBoard_Num="${contentView.fBoard_Num}">삭제</button>



<!-- 댓글 작성 -->

<div id="reply">






 <section class="replyForm">
  <form:form role="form" method="post">
  	<input type="hidden" name="fBoard_Num" id="fBoard_Num" value="${contentView.fBoard_Num}">
  	
   <div class="input_area">
   <label for="Content">댓글 내용:</label>
      <textarea class="form-control" rows="5" id="repCon" name="repCon"></textarea>
   </div>
   
   <div class="input_area">
   <sec:authorize access="isAnonymous()">
   		<p align="right"><button type="button" class="btn btn-outline-dark btn-sm" id="reply_btn" onclick="return confirm('로그인시 작성 가능합니다.');">댓글 작성</button></p>
   </sec:authorize>
   
   <sec:authorize access="isAuthenticated()">	
		<p align="right"><button type="button" class="btn btn-outline-dark btn-sm" id="reply_btn">댓글 작성</button></p>
	</sec:authorize>
   
   
   		
    	<script>
		 $("#reply_btn").click(function(){
		  
		  var formObj = $(".replyForm form[role='form']");
		  var fBoard_Num = $("#fBoard_Num").val();
		  var repCon = $("#repCon").val()
		  
		  var data = {
			fBoard_Num : fBoard_Num,
		    repCon : repCon
		    };
		  
		  $.ajax({
		   url : "${pageContext.request.contextPath}/free_contentView/registReply",
		   type : "post",
		   data : data,
		   success : function(){
			   console.log(data);
		    replyList();
		    $("#repCon").val("");
		   }
		  });
		 });
		</script>
    
   </div>
   
  </form:form>
 </section>

<section class="replyList">
 <ol>
  <!--댓글 리스트  -->
   
 </ol>    
  
  
	<script> 
		replyList() 
	</script>
	
	<script>
	//댓글 삭제
		  $(document).on("click", ".delete", function(){
		  
		  var deleteConfirm = confirm("정말로 삭제하시겠습니까?");
			 
		  if(deleteConfirm){
		  
		  var data = {repNum : $(this).attr("data-repNum")};
		  
		  $.ajax({
			  url : "${pageContext.request.contextPath}/free_contentView/deleteReply",
			  type : "post",
			  data : data,
			  success : function(result){
			   
			   console.log(result);
				  
			   if(result == 1) {
			    replyList();
			   }
			   
			   if(result == 0) {
			    alert("작성자 본인만 할 수 있습니다.");    
			   }
			  },
			  error : function(){
			   alert("로그인하셔야합니다.")
			  }
			});
		}
	});
	</script>
	
	
	<script>
		//게시판 삭제
		
		$(document).on("click", ".boardDelete", function(){
		  
		  var deleteConfirm = confirm("정말로 삭제하시겠습니까?");
		
		  if(deleteConfirm){
			  
			  var data = {fBoard_Num : $(this).attr("data-fBoard_Num")};
			  
			  
			  $.ajax({
				  url : "${pageContext.request.contextPath}/free_delete",
				  type : "get",
				  data : data,
				  success : function(result){
				   
				   console.log("result: " + result);
					
				   console.log("data: " + data);
				   
				   if(result == 1) {
					  window.location.href = "${pageContext.request.contextPath}/free_boardList";
				   }
				   
				   if(result == 0) {
				    alert("작성자 본인만 삭제 할 수 있습니다.");    
				   }
				  },
				  error : function(){
				   alert("로그인하셔야합니다.")
				  }
				});
		 	 }
		});
		
		</script>
	
	





</section>
</div>



<hr>


















<%--<br> 

<p align="right">
<sec:authorize access="isAnonymous()">
<a class="btn btn-dark" role="button" onclick="return confirm('로그인시 작성 가능합니다.');">글 작성</a>
</sec:authorize>


<sec:authorize access="isAuthenticated()">
<a href="free_writeView" class="btn btn-dark" role="button">글 작성</a>
</sec:authorize>

<a href="free_boardList" class="btn btn-dark" role="button">목록</a>
</p>
<br>

  
          
    <table class="table table-hover">
	    <thead class="thead-light" align="center">
	      <tr>
	        <th>No</th>
	        <th>작성자</th>
	        <th>제목</th>
	        <th>조회수</th>
	        <th>등록일</th>
	      </tr>
	    </thead>
    <tbody>
		<c:forEach items="${list}" var="list">
		      <tr>
		        <td align="center">${list.fBoard_Num}</td>
		        <td align="center">잡담</td>
		        <td ><c:forEach begin="1" end="${list.fIndent}">Re:</c:forEach>
		        <td align="center">${list.fId}</td>
		        <td align="center"><a class="text-dark" href="free_contentView?fBoard_Num=${list.fBoard_Num}">${list.fTitle}</a></td>
		        <td align="center">${list.fHit}</td>
		        <td align="center"><fmt:formatDate value="${list.fDate}" pattern="yyyy-MM-dd"/></td>
		      </tr>
		</c:forEach>
    </tbody>
  </table>

<sec:authorize access="isAnonymous()">
	<p align="right"><a class="btn btn-dark" role="button" onclick="return confirm('로그인시 작성 가능합니다.');">글 작성</a></p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">	
	<p align="right"><a href="free_writeView" class="btn btn-dark" role="button">글 작성</a></p>
</sec:authorize>

</div>



<div class="container" id="board_pagination">

	<ul class="pagination justify-content-center">
    	
    	<c:if test="${pageMaker.prev}">
    		<li class="page-item"><a class="page-link text-dark" href="free_boardList${pageMaker.makeQuery(pageMaker.startPage - 1) }">Previous</a></li>
    	</c:if>
    	
    	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
    		<c:out value="${pageMaker.cri.pageNum == idx?'':''}" />
    		<li class="page-item"><a class="page-link text-dark" href="free_boardList${pageMaker.makeQuery(idx)}">${idx}</a></li>
    	</c:forEach>
    	
    	<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    		<li class="page-item"><a class="page-link text-dark" href="free_boardList${pageMaker.makeQuery(pageMaker.endPage +1) }">Next</a></li> <br>
    	</c:if>
 
	</ul>
  
</div>

<div class="container" id="board_search_bar">

	<div class="row">
		
		<div class="col-sm-4">
		</div>

		<div class="col-sm-4">
<!-- Board Search Bar -->
 			<div class="input-group mb-3">
  				<input type="text" class="form-control" placeholder="">
  				<div class="input-group-append">
    				<button class="btn btn-danger btn-dark" type="submit">검색</button>
  				</div>
			</div>
<!-- Board Search Bar End -->
		</div>
	
		<div class="col-sm-4">
		</div>
		
	</div>

</div>

<br> --%>

<div class="jumbotron text-center" style="margin-bottom:0">
<p>Copyright © 2020 Wemade Korea All rights reserved</p>
</div>  


</body>
</html>