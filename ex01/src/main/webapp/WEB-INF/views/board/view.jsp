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

<!-- CSS 스타일 태그  -->
<link href="/resources/css/style.css" rel="stylesheet" >

<!--  JS 댓글(스크립트)  -->
<script src="/resources/js/realReply.js"></script>

<!--  파일 등록하는 스크립트  -->
<title>상세보기</title>
<script type="text/javascript">

		// 페이지를 다 그리고 나서 버튼을 선택하면 함수가 실행된다 
		// 여러개를 적용해줄 수 있는 addEventListener (현재ver)
		// window.onload는 한번만 적용 가능 (예전ver)
		window.addEventListener('load', function(){
			
		// 로그인한 아이디와 게시글의 작성자가 일치하면, 수정 - 삭제 버튼에 이벤트 적용
		if(${userId eq board.writer}){
			
				//☆ 수정페이지 이동 > 수정 처리
		btnEdit.addEventListener('click', function(){
			viewForm.action='/board/edit';
			viewForm.submit();
		});
				//☆ 삭제 처리 > 목록 페이지 이동
		btnDelete.addEventListener('click', function(){
			viewForm.action='/board/deleteAction';
			viewForm.submit();
		});
	}	
				//☆ 목록 페이지 이동
		btnList.addEventListener('click', function(){
			viewForm.action='/board/list_boot';
			viewForm.submit();
		});
				
				//☆ 댓글 목록 조회 후 출력  ( 댓글 보여주기 )
		getReplyList();
				
				//☆ 답글 등록 버튼 (등록 버튼을 클릭하면 답글 등록 실행)
		btnReplyWrite.addEventListener('click', function(){
			replyWrite();
		});
				
		// ★파일 목록 보여주기 list 
		getFileList();	
		
			// ★파일 목록 출력 함수 
		function getFileList(){
			// bno 요소를 선택에서 그, 값을 가져옴
			let bno = '${board.bno}';
			console.log("bno : ", bno);
			
			fetch('/file/list/'+ bno)
			.then(response => response.json())
			.then(map => viewFileList(map));
		}
			
		// ★파일 보여주기 함수
		function viewFileList(map){
			console.log("map : ", map);
			let content = '';
			
			if(map.list.length > 0){
				content += 
					 
					'<div class="mb-3" id="divFileupload"></div>'
					+
					 '<div class="mb-3"> '
					+  ' <label for="attachFile" class="form-label">🌱첨부파일 목록</label> '
					+  '	<div class = "form-control" id="attachFile"> '
					
			map.list.forEach(function(item, index){
				// URL 인코딩 
				let savePath = encodeURIComponent(item.savePath);
				
				console.log("item.savePath" , item.savePath);
				content += "<a href ='/file/download?fileName="
						+ savePath+"'>" 
					+ item.filename 
					+ '</a>'
				+ '<br>' ;
				})
				content +=
		 			 '	</div> '
		 			+ '</div>  ';
			}else{
				content = '등록된 파일이 없습니다.';
			}
			divFileupload.innerHTML = content;
		}
});
</script>
</head>
<body>
<!--
페이지번호 : ${param.pageNo} 
${searchField }
${searchWorld}
  -->
<%@ include file="../common/Header.jsp" %>    


<!--${board}  -->

<c:set  value="${board}" var="board"></c:set>

<main class="container">

 <div class="bg-light p-5 rounded">
    <h2>🔅상세보기🔅</h2>
    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>
    <a  class="btn btn-secondary w-30" id="btnList" href="#" role="button">목록으로 돌아가기</a>
  </div> 

<form method="get" name="viewForm" accept-charset="UTF-8" >
	
	<!-- 파라메터 -->
	<input type ="text" name= "pageNo" value="${param.pageNo }" id = "pageNo" >
	<input type ="text" name= "searchField" value="${param.searchField }" >
	<input type ="text" name= "searchWorld" value="${param.searchWorld }" >
   	<input type="text" name="bno" value="${board.bno}" id = "bno" >
	
	<div class="mb-3">
	  <label for="title" class="form-label">🌱제목</label>
	  <input type="text" class="form-control"  id="title"  name ="title" value = "${board.title }"  readonly></input>
	</div>
	
	<div class="mb-3">
	  <label for="content" class="form-label">🌱내용</label>
	  <textarea class="form-control" id="content" name = "content" rows="3" readonly>${board.content }</textarea>
	</div>
	<div class="mb-3">
	  <label for="writer" class="form-label">🌱작성자</label>
	  <input type="text" class="form-control" id="writer" name ="writer" value = "${board.writer }" readonly></input>
	</div>
	
	<!--  파일 목록을 뿌려주는 공간 -->
	 <div class="mb-3" id="divFileupload">
	 </div>
	
		<div class="mb-3">
	  <label for="regdate" class="form-label">🌱등록일</label>
	  <input type="text" class="form-control" id="regdate"  name ="regdate" value = "${board.regdate }" readonly></input>
	</div>
	
		<div class="mb-3">
	  <label for="updatedate" class="form-label">🌱수정일</label>
	  <input type="text" class="form-control" id="updatedate"  name ="updatedate" value = "${board.updatedate }" readonly></input>
	</div>
			
	<!--  로그인한 사용자의 아이디와 게시글의 작성자가 일치하면 버튼을 노출 -->
		<c:if test="${userId eq board.writer }">
	  		<div class="d-grid gap-2 d-md-flex justify-content-md-center">
	  		<button type="button" class="btn btn-outline-primary" id="btnEdit">update</button>
	  		<button type="button" class="btn btn-outline-warning" id="btnDelete">delete</button>
	  </div>
		
		</c:if>
		
</form>

<!--  댓글 리스트  -->
<br>
<h3>cHaT📧  </h3>
	<!--  댓글 작성자를 로그인한 사용자의 아이디로 입력 -->
	<input type ="text" name= "replyer" value="${userId }" id = "pageReply" >
	<div class="input-group">
  <span class="input-group-text">댓글</span>
  <!-- 수정★ -->
  <input type ="hidden" name= "pageReply" value="${param.pageNo }" id = "pageReply" >
  <input type="text" aria-label="First name" class="form-control" name= "replyer" value="${userId }" id = "pageReply" >
<!--   <input type="text" aria-label="First name" class="form-control" id = "replyer" value="작성자"> -->
  <input type="text" aria-label="First name" class="form-control" id = "reply" placeholder="댓글을 입력해주세요" >
  <input type="button" id="btnReplyWrite"  value= "등록" aria-label="Last name" class="input-group-text">
</div>
	<div id = "replyDiv">
	</div>
</main>

<br>
	<!--  footer -->
<%@include file="./footer.jsp" %>