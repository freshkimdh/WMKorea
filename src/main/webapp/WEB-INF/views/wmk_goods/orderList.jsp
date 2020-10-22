<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!-- 시큐리티 전용 태그 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html lang="en">

<head>


</head>

<body>
	


 	<p>
	<p align="center" class="display-3" id="login_f">My Orders</p>

	<div class="container">
	<img class="mx-auto d-block" src="goods/profile.png" width="150"><br>
	<h3 align="center"> <sec:authentication property="principal.user.nickname"/> 님의 주문 내역입니다.</h3>
	<p align="center">주문번호를 누르면 주문내역을 확인할 수 있습니다.</p>
	
	</div>
	<br>
	
	
<div class="container">
        
  <table class="table table-hover">
    <thead align="center">
      <tr>
        <th>주문번호</th>
        <th>총 결제금액</th>
        <th>주문 상태</th>
      </tr>
    </thead>
    <tbody align="center">
<c:forEach items="${orderList}" var="orderList" varStatus="last">	
      <tr>
        <td><a href="${pageContext.request.contextPath}/orderView?n=${orderList.orderId}">${orderList.orderId}</a></td>
        <td>${orderList.amount} 원</td>
        <td><button type="button" class="btn btn-outline-dark btn-sm">배송 준비중</button></td>
      </tr>
</c:forEach>
    </tbody>
  </table>
  <br>
</div>
	
	

</body>
</html>
