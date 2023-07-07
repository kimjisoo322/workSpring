<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbar-fixed/">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!--  스타일 태그  -->
<link href="/resources/css/style.css" rel="stylesheet" >
<script type="text/javascript">

	function requestAction(url) {
		writeForm.action=url;
		writeForm.submit();
	}
</script>
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
<title>글쓰기 - 등록</title></head>
<body>
<%@ include file="../common/Header.jsp" %>    

<main class="container">
 <div class="bg-light p-5 rounded">
    <h2>🔅글쓰기🔅</h2>
    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>
    <a  class="btn btn-secondary w-30" href="../board/list_boot" role="button">목록으로 돌아가기</a>
  </div>

 <div class="list-group w-auto">
<form action="/board/writeAction" method="post" accept-charset="UTF-8" name = "writeForm" >

	<div class="mb-3">
	  <label for="title" class="form-label">🌱제목</label>
	  <input type="text" class="form-control" id="title"  name ="title" >
	</div>
	
	<div class="mb-3">
	  <label for="content" class="form-label">🌱내용</label>
	  <textarea class="form-control" id="content" name = "content" rows="3"></textarea>
	</div>
	<div class="mb-3">
	  <label for="writer" class="form-label">🌱작성자</label>
	  <input type="text" class="form-control" id="writer" name ="writer"  >
	</div>
	
	  <div class="d-grid gap-2 d-md-flex justify-content-md-center">
	<!-- bno가 있으면 수정하기 버튼을 보여주고 아니면 글쓰기  -->
	
	  <c:if test= "${not empty board.bno}" var = "res">
		 <input type = "text" name ="bno" value = "${board.bno}" ></input>
			<button type="submit" class="btn btn-success" onclick="requestAction('/board/updateAction')">update</button>
	  </c:if>
	  <c:if test="${not res}">
	  	<button type="submit" class="btn btn-warning">input</button>
	  </c:if>
	 <button type="reset" class="btn btn-secondary">reset</button>

	  </div>
</form>
</div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>      
</body>
</html>