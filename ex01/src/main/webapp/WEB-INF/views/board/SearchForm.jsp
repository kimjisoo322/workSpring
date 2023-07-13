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
    
<title>Insert title here</title>

<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbar-fixed/">

 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<!--  스타일 태그  -->
<link href="/resources/css/style.css" rel="stylesheet" >

<title> 검색 조건  _ searchForm</title>
</head>
<body>

<form class="row g-3 justify-content-center" action="/board/list_boot" method = "get" name="searchForm">

<input type ="text" name ="bno" value ="${board.bno }" hidden>  
<input type ="text" name ="pageNo" value ="${criteria.pageNo }" hidden>
 
  <div class="col-sm-2">
    <select name = "searchField" class="form-select" aria-label="Default select example">
  <option value="title"${criteria.searchField eq "title" ? "selected" : ""}>제목</option>
  <option value="content"${criteria.searchField eq "content" ? "selected" : ""}>내용</option>
  <option value="writer"${criteria.searchField eq "writer" ? "selected" : ""}>작성자</option>
</select>
  </div>
  
  <div class="col-sm-3">
    <label for="searchWorld" class="visually-hidden"></label>
    <input type="text" class="form-control" id="searchWorld" name ="searchWorld" placeholder="검색어" value=${criteria.searchWorld }>
  </div>
  
  <div class="col-auto">
    <button type="submit" class="btn btn-primary mb-3">SEARCH</button>
  </div>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>      
</body>
</html>