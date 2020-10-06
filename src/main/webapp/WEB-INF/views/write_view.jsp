<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>write view</title>

<script type="text/javascript" src="resources/ckeditor/ckeditor.js"></script>

</head>
<body>

	<table width="800" cellpadding="0" cellspacing="0" border="1">
		<form action="write" method="post" enctype="multipart/form-data">
			<tr>
				<td> 이름 </td>
				<td> <input type="text" name="bName" size = "50"> </td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="bTitle" size = "50"> </td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> 
				<textarea id = "bContent" name = "bContent"></textarea>

					 <script>
					 	CKEDITOR.replace('bContent',{filebrowserUploadUrl:'${pageContext.request.contextPath}/img/imageUpload.do'});
					 </script>
				</td>
			</tr>
			<tr>
				<td colspan="2"> 
					<input type="submit" value="입력"> &nbsp;&nbsp;
					<a href="list">목록보기</a>
				</td>
			</tr>
		</form>
	</table>
	
</body>
</html>
