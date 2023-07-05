<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
    
    <title>게시판 목록</title>

<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbar-fixed/">

 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<!--  스타일 태그  -->
<link href="/resources/css/style.css" rel="stylesheet" >

<script type ="text/javascript">
  /*
  			메세지 모달창으로 띄우기 
  */
	let msg = '${message}';
	 	window.onload = function(){
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
	}
</script>

    <!-- Custom styles for this template -->
    <link href="navbar-top-fixed.css" rel="stylesheet">
  </head>
<body>

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

<%@ include file="../common/Header.jsp" %>    

<main class="container">
  <div class="bg-light p-5 rounded">
    <h2>🔅게시판🔅</h2>
    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>
    <a class="btn btn-lg btn-primary" href="../board/write" role="button">글쓰기</a>
  </div>

  <p></p>

<div class="list-group w-auto">
	        <div class="panel-body">
            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>내용</th>
                        <th>작성자</th>
                        <th>등록일</th>
                        <th>수정일</th>
                    </tr>
                </thead>
   <!--  게시판 목록 출력하기  -->
  <c:forEach items="${list}" var = "board" step="1">
		<c:if test="${empty list}">
			 <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
    		<img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
    		<div class="d-flex gap-2 w-100 justify-content-between">
      <div>
       		 <h6 class="mb-0"> </h6>
       		 <p class="mb-0 opacity-75">등록된 게시물이 없습니다.</p>
      </div>
      		<small class="opacity-50 text-nowrap">now</small>
   		 </div>
  	</a>
		</c:if>
		<c:if test="${not empty list}">
                <tbody>
                    <tr class="odd gradeX">
                        <td><a href ='/board/view?bno=${board.bno} '>${board.bno}</a></td>
                        <td>${board.title }</td>
                        <td>${board.content }</td>
                        <td class="center">${board.writer }</td>
                        <td class="center">${board.regdate }</td>
                         <td class="center">${board.updatedate }</td>
                    </tr>
                   
                </tbody>
                </c:if>
                </c:forEach>
            </table>
   
        </div>
        </main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>      
</body>
</html>
 <%--  <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
    <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
    <div class="d-flex gap-2 w-100 justify-content-between">
      <div>
        <h6 class="mb-0">번호</h6>
        <p class="mb-0 opacity-75">${board.bno }</p>
      </div>
      <small class="opacity-50 text-nowrap">now</small>
    </div>
  </a>
  
    <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
    <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
    <div class="d-flex gap-2 w-100 justify-content-between">
      <div>
        <h6 class="mb-0">제목</h6>
        <p class="mb-0 opacity-75">${board.title }</p>
      </div>
      <small class="opacity-50 text-nowrap">now</small>
    </div>
  </a>
  
    <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
    <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
    <div class="d-flex gap-2 w-100 justify-content-between">
      <div>
        <h6 class="mb-0">내용</h6>
        <p class="mb-0 opacity-75">${board.content}</p>
      </div>
      <small class="opacity-50 text-nowrap">now</small>
    </div>
  </a>
  
  <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
    <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
    <div class="d-flex gap-2 w-100 justify-content-between">
      <div>
        <h6 class="mb-0">작성자</h6>
        <p class="mb-0 opacity-75">${board.writer }</p>
      </div>
      <small class="opacity-50 text-nowrap">now</small>
    </div>
  </a>
  
   <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
    <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
    <div class="d-flex gap-2 w-100 justify-content-between">
      <div>
        <h6 class="mb-0">등록일</h6>
        <p class="mb-0 opacity-75">${board.regdate }</p>
      </div>
      <small class="opacity-50 text-nowrap">now</small>
    </div>
  </a>
  
  <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
    <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
    <div class="d-flex gap-2 w-100 justify-content-between">
      <div>
        <h6 class="mb-0">수정일</h6>
        <p class="mb-0 opacity-75">${board.updatedate }</p>
      </div>
      <small class="opacity-50 text-nowrap">now</small>
    </div>
  </a> 
    </c:if>
</c:forEach>	
  --%>


