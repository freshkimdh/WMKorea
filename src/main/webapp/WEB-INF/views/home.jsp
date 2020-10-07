<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<p>mapping: img</p>

<img src="img/s1.PNG">



<p>mapping: others</p>

<img src="others/s1.PNG">


<p>mapping: img/img2</p>

<img src="img2/s1.PNG">


<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
