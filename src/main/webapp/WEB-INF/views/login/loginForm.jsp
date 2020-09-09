<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--이 from 태그를 사용하면 알아서 csrf를 보낸다  -->
<!--만약에 없으면 아래 주석 해체해서 넣어줘야 한다.  -->
<!--  -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>로그인 페이지</title>
</head>

<body onload="document.f.id.focus();">

<h3>아이디와 비밀번호를 입력해주세요.</h3>

<c:url value="/login" var="loginUrl" />
<p>${loginUrl}</p> <!--절대경로 표시  -->
<form:form name="f" action="${loginUrl}" method="POST">
    <c:if test="${param.error != null}">
        <p>아이디와 비밀번호가 잘못되었습니다.</p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>로그아웃 하였습니다.</p>
    </c:if>
    <p>
        <label for="username">아이디</label>
        <input type="text" id="id" name="id" />
    </p>
    <p>
        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password"/>
    </p>
    
    
    <%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
    <!--"_csrf." session 영역에 저장 되어 있는 것이다.  -->
    <button type="submit" class="btn">로그인</button>
</form:form>

</body>
</html>
