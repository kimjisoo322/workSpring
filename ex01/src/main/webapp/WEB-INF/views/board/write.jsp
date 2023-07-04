<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	#tables{
		border-collapse: collapse;
		width: 100%;
		height: 100px;
	}
	#tables input{
		width: 100%;
		height: 100px;
	}
</style>
<title>글쓰기 - 등록</title>
<script>
	function msgAlert(){
		alert("등록되었습니다.");
	}
</script>
</head>
<body>

res : ${res }건 등록

<form action="/board/write" method="post" accept-charset="UTF-8" >

<table border="1"  id ="tables">
<tr>
<td> 제목</td>
<td><input type ="text" name ="title"  value = "제목"></input></td>
</tr>

<tr>
<td> 내용</td>
<td><input type ="text" name ="content" value = "내용"></input></td>
</tr>

<tr>
<td> 작성자</td>
<td><input type ="text" name ="writer" value = "작성자"></input></td>
</tr>

<tr>
<td colspan="2" align="center" ><input type ="submit" value ="전송" onclick="msgAlert()"></input> </td>
</tr>

</table>
</form>


</body>
</html>