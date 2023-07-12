<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbar-fixed/">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!--  스타일 태그  -->
<link href="/resources/css/style.css" rel="stylesheet" >
<title>수정하기</title>

<script>
	function deletepost(bno){
		location.href = "/board/deleteAction?bno=" + bno;
	}
</script>
</head>
<body>

페이지번호 : ${param.pageNo} 
${searchField }
${searchWorld}
<%@ include file="../common/Header.jsp" %>    

<!--${board}  -->

<c:set  value="${board}" var="board"></c:set>
 <input type="text" name ='no' value ="${board.bno}" ></input>
 <input type="text" name = 'pageNo' value ="${criteria.pageNo}"></input>

<main class="container">

 <div class="bg-light p-5 rounded">
    <h2>수정하기✏</h2>
    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>
    <a  class="btn btn-secondary w-30" href="../board/list_boot" role="button">목록으로 돌아가기</a>
  </div>

<form action="/board/updateAction?bno=${board.bno}" method="post" accept-charset="UTF-8" >
<input type ="text" name= "pageNo" value=${param.pageNo }>
<input type ="text" name= "searchField" value=${param.searchField }>
<input type ="text" name= "searchWorld" value=${param.searchWorld }>

   <input type="text" name="bno" value="${board.bno }">
	<div class="mb-3">
	  <label for="title" class="form-label">📌제목</label>
	  <input type="text" class="form-control"  id="title"  name ="title" value = "${board.title }"></input>
	</div>
	
	<div class="mb-3">
	  <label for="content" class="form-label">📌내용</label>
	  <textarea class="form-control" id="content" name = "content" rows="3">${board.content }</textarea>
	</div>
	<div class="mb-3">
	  <label for="writer" class="form-label">📌작성자</label>
	  <input type="text" class="form-control" id="writer" name ="writer" value = "${board.writer }"></input>
	</div>
	
		<div class="mb-3">
	  <label for="regdate" class="form-label">📌등록일</label>
	  <input type="text" class="form-control" id="regdate"  name ="regdate" value = "${board.regdate }"></input>
	</div>
	
		<div class="mb-3">
	  <label for="updatedate" class="form-label">📌수정일</label>
	  <input type="text" class="form-control" id="updatedate"  name ="updatedate" value = "${board.updatedate }"></input>
	</div>
	
	  <div class="d-grid gap-2 d-md-flex justify-content-md-center">
	  <button type="submit" class="btn btn-outline-primary">update</button>
	  <button type="reset" class="btn btn-secondary">reset</button>
	  </div>
</form>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>      

</body>
</html>