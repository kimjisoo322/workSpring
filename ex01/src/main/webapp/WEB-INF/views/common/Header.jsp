<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<title>Insert title here</title>
</head>
<!-- fontawesome 키트 붙이기  -->
<script src="https://kit.fontawesome.com/59843f4445.js" crossorigin="anonymous"></script>

<script type ="text/javascript">
  /*
  			메세지 모달창으로 띄우기 
  */
	let msg = '${message}';

	window.addEventListener('load', function(){
			if(msg != ''){
				// 메세지 출력
			 document.querySelector(".modal-body").innerHTML = msg;
				// 버튼 출력 제어
			 document.querySelector("#btnModalSave").style.display = 'none';
				let myModal = new bootstrap.Modal('#myModal', {
				keyboard: false
		})
				// 모달보여주기
			myModal.show();
		}
			const myModalEl = document.getElementById('myModal')
			myModalEl.addEventListener('hidden.bs.modal', event => {

			});
			
	 	});
	
</script>
<body>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">🍰</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav me-auto mb-2 mb-md-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        
        
        <!--  아이디가 비어있으면 LOGIN, 비어있지 않으면 LOGOUT -->
        <c:if test="${empty userId}" var = "res">
        
        <li class="nav-item">
          <a class="nav-link" href="/login">Login</a>
        </li>
		</c:if>
        
        <c:if test="${not res}">
        <li class="nav-item">
          <a class="nav-link" href="/logout">Logout</a>
        </li>        
        </c:if>
        
      </ul>
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>

<!-- Modal  알림 등록 -->
<div id="myModal" class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">알림</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
         <button type="button" class="btn btn-primary" id ="btnModalSave">확인</button>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>      
</body>
</html>