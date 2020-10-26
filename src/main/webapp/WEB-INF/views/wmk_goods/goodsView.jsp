<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- 시큐리티 전용 태그 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>





 
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css"> <!--  Library for Dragabble effect  -->
	
	
	<script src="//code.jquery.com/jquery-1.12.4.js"></script> <!--  Library for Dragabble effect  -->
	<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script> <!--  Library for Dragabble effect  -->
	
	<script src="js/jquery-3.3.1.min.js"></script> <!-- Library for text input -->
	
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script> <!-- div 캡처 -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js"></script> <!-- div 캡처 영역 저장 -->
	  
  


	<style>
   	.image-stack {
	  display: grid;
	  grid-template-columns: repeat(12, 1fr);
	  position: relative;
	}
	
	
	.layer0 {
	grid-column: 1 / span 8;
	grid-row: 1; // make this image be on the same row
	  }	
	
	.layer1 {
	
	  grid-column: 11 / span 12;
      grid-row: 1; 
      padding-top: 350%; 
      z-index: 1;
	}
	
	.layer2 {
	
	  grid-column: 11 / span 12;
      grid-row: 1; 
      padding-top: 730%; 

	
	}
	
	
	
	
	</style>
	
	<script>
	
		$(function() {
			
			$("#btn_prompt").click(function(){
				
				var result = prompt("텍스트를 입력하세요");
				
				if(result != "") {
					
					$("#font_text").text(result);
					
				}else{
					
					$("#font_text").text("텍스트를 입력하세요");
				}
				
				
				
			});
			
		});
		
	
	</script>
	


<body>




<div id="root">
 	<p>
	<p align="center" class="display-3" id="login_f">Goods View</p><br>
	
		

	<div class="container">
	
		<button class="btn btn-secondary" onclick='sample();'>저장하기</button> 
		
			<script type="text/javascript">
		    function sample() {
		        
		        var background = document.getElementById('example').style.background;
		        if(background == "") {
		            document.getElementById('example').style.background = "#fff"; 
		        }
		            
		        html2canvas(document.getElementById('example'), {
		            useCORS: true, // 다른사이트의리소스가있을때활성화(그러나...Access-Control-Allow-Origin 필요)
		            onrendered: function(canvas) {
		                canvas.toBlob(function(blob) {
		                    saveAs(blob, 'download.png');
		                });
		                
		                // $("#test").html('<img src=' + canvas.toDataURL("image/png") + '>');
		            }
		        });
		    }
		    </script>



		<div class="row">
		
				
			<div class="col-sm-6" id="example">
			
			<div class="image-stack col-sm-6">
			
				<div class="layer0">	
				<img id="bg" alt="" src="${pageContext.request.contextPath}/${view.gdsImg}" width="500">
				</div>
				
				<div class="layer1">

					<div id="draggable"><img id="faces" alt="" src="goods/avatar_null.png" width="150"></div>
				

				</div>
				
				
				<div class="layer2">	
					<div id="draggable2">
					<font id="font_text" size="5em" color="black" style=" font: Impact;">
					WemadeKorea
					</font>
					</div>
				</div>
				
					
			</div>
		
<!--  			<img src="goods/white_shirts_550.png" class="img-fluid float-left" width="100">
			<img src="goods/black_shirts_550.png" class="img-fluid float-left" width="100">
			<img src="goods/gray_shirts_550.PNG" class="img-fluid float-left" width="100">  -->
				
	
			</div>
			
			<div class="col-sm-6">
			
		<form:form role="form" method="post">
		 	
		 	<input type="hidden" id="gdsNum" name="gdsNum" value="${view.gdsNum}" />
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 

  		</form:form>
			
			
			<h2>${view.gdsName}</h2>
<%-- 			<h5>(${view.gdsPrice}원)</h5> <br> --%>
	
			<h5><fmt:formatNumber pattern="###,###,###" value="${view.gdsPrice}" />원</h5><br>
 
			
			<label for="selDistrict">색상 선택:</label>
			<select class="form-control" id="gdsColor" name="gdsColor">
			<option>흰색 (White)</option>
			<option>검정 (Black)</option>
			<option>회색 (Gray)</option>
			</select>
			
			<br>
			
			<label for="selDistrict">사이즈 선택:</label>
			<select class="form-control" id="gdsSize" name="gdsSize">
			<option>M (100)</option>
			<option>L (105)</option>
			<option>XL (110)</option>
			</select>
			<br>
			
			텍스트 입력:
			<input type="text" class="form-control" id="gdsText" name="gdsText" placeholder="선택사항 (무료)">
			<p><p>
			<button class="btn btn-secondary btn-sm" id="btn_prompt">입력 보기</button>
			<a href="" class="btn btn-secondary btn-sm" role="button" onclick="return confirm('서비스 지원 예정');">글꼴 스타일</a></p>
			<p>

			
				<p class="cartStock">
				 <span>구입 수량</span>
				 <button type="button" class="btn btn-secondary btn-sm plus">+</button>
				 <input type="number" class="numBox" min="1" max="${view.gdsStock}" value="1" readonly="readonly"/>
				 <button type="button" class="btn btn-secondary btn-sm minus">-</button>
				 
				 <script>
					  $(".plus").click(function(){
					   var num = $(".numBox").val();
					   var plusNum = Number(num) + 1;
					   
						   if(plusNum >= ${view.gdsStock}) {
						    $(".numBox").val(num);
						   } else {
						    $(".numBox").val(plusNum);          
						   }
					  });
					  
					  $(".minus").click(function(){
					   var num = $(".numBox").val();
					   var minusNum = Number(num) - 1;
					   
						   if(minusNum <= 0) {
						    $(".numBox").val(num);
						   } else {
						    $(".numBox").val(minusNum);          
						   }
					  });
				 </script>
				 
				</p>

			
			
			
			<p>배송비: 회원 무료</p>
			
		<sec:authorize access="isAnonymous()"> <!-- isAnonumous: 누구나 다 access 할 수있다 -->	
		
			<a href="" class="btn btn-dark" role="button" onclick="return confirm('회원가입시 이용가능합니다.');">구매하기</a>
			<a href="" class="btn btn-dark" role="button" onclick="return confirm('회원가입시 이용가능합니다.');">장바구니 담기</a>
			
		</sec:authorize>
			
		<sec:authorize access="isAuthenticated()">			
			
			<a href="goodsOrder" class="btn btn-dark addCart_btn2" role="button">구매하기</a>
				<script>
				  $(".addCart_btn2").click(function(){
				   var gdsNum = $("#gdsNum").val();
				   var gdsColor = $("#gdsColor").val();
				   var gdsSize = $("#gdsSize").val();
				   var gdsText = $("#gdsText").val(); 
				   var cartStock = $(".numBox").val();
				   
				      
				   var data = {
				     gdsNum : gdsNum,
				     gdsColor : gdsColor,
				     gdsSize : gdsSize,
				     gdsText : gdsText,
				     cartStock : cartStock
				     };
				   			   
				   
				   $.ajax({
				    url : "${pageContext.request.contextPath}/addCart",
				    type : "post",
				    data : data,
				    success : function(result){

							
						$(".numBox").val("1");
						location.href = "${pageContext.request.contextPath}/goodsOrder";
					    
						},
						
				    error : function(){
				     alert("카트 담기 실패");
				    }
				   }); /* ajax end */
				   		   
				   
				  }); /* click function end */
				 </script>
			

				 <button type="button" class="btn btn-dark addCart_btn">카트에 담기</button>
				 
				 <script>
				  $(".addCart_btn").click(function(){
				   var gdsNum = $("#gdsNum").val();
				   var gdsColor = $("#gdsColor").val();
				   var gdsSize = $("#gdsSize").val();
				   var gdsText = $("#gdsText").val(); 
				   var cartStock = $(".numBox").val();
				   
				      
				   var data = {
				     gdsNum : gdsNum,
				     gdsColor : gdsColor,
				     gdsSize : gdsSize,
				     gdsText : gdsText,
				     cartStock : cartStock
				     };
				   			   
				   
				   $.ajax({
				    url : "${pageContext.request.contextPath}/addCart",
				    type : "post",
				    data : data,
				    success : function(result){

						if(confirm('장바구니에 상품을 담았습니다.\n장바구니로 이동하겠습니까?')){
							
						$(".numBox").val("1");
						location.href = "${pageContext.request.contextPath}/cartList";
					    
						} else {
					    return false;
					    }		     
					     
					},
				    
				    error : function(){
				     alert("카트 담기 실패");
				    }
				   }); /* ajax end */
				   		   
				   
				  }); /* click function end */
				 </script>
	
				
		</sec:authorize>
		
	
			</div>
			
		
		</div> <!-- row end -->

	</div> <!--  container end -->
	

	
	<div class="container">
	
	<hr>
		
		<br><br>
		<h3 align="center">이 곳은 제품의 상세정보가 들어갑니다.</h3>
		<sec:authorize access="isAuthenticated()">
			<a href="admin/goodsDelete?gdsNum=${view.gdsNum}" class="btn btn-dark" role="button">상품 삭제</a>
		</sec:authorize>	
		<br>
	
	</div>
	
	<br><br>


</div>


	<script>
	$( "#draggable" ).draggable();
	$( "#draggable2" ).draggable();
	</script>
	

</body>
