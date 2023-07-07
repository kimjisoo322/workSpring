<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
    
<title>Insert title here</title>

<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbar-fixed/">

 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<!--  스타일 태그  -->
<link href="/resources/css/style.css" rel="stylesheet" >

<script>
// 페이지 번호를 받아서 페이지를 호출 해주는 함수 
	function go(page){
		document.searchForm.pageNo.value=page;
		document.searchForm.submit();
	}
</script>
</head>
<body>
<!--  페이지 블럭 생성  -->
<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-end">

    <li class="page-item ${ pageDto.prev ? '':'disabled'}">
      <a class="page-link" onclick='go(${pageDto.startNo-1})'>Previous</a>
    </li>

 	<c:forEach begin = "${pageDto.startNo }" end ="${pageDto.endNo}" var ="i">
    <li class="page-item"><a class="page-link ${i eq criteria.pageNo? 'active':'' }" onclick='go(${i})'>${i}</a></li>
    </c:forEach>

    <li class="page-item ${ pageDto.next? '' : 'disabled'}">
      <a class="page-link"  onclick='go(${pageDto.endNo+1})'>Next</a>
    </li>
  </ul>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>      
</body>
</html>