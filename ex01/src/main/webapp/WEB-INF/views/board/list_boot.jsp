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

    <!-- Custom styles for this template -->
   <!--  <link href="navbar-top-fixed.css" rel="stylesheet"> -->
    
 <script type="text/javascript">
				  // url, bno 두개를 받음 
 	function requestAction(url, bno){
					  // 내가 번호를 클릭하면 onclick 함수 실행 -> searchForm의 action=내가 준 url로 바뀜 
 		searchForm.action=url;
 		searchForm.bno.value=bno;
 		searchForm.submit();
 	}
 </script>
  </head>
<body>

<%@ include file="../common/Header.jsp" %>    

<main class="container">
    ${member.id} 님 환영합니다🎉
  <div class="bg-light p-5 rounded">
    <h2>🔅게시판🔅</h2>
    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>
      <a class="btn btn-secondary w-30" href="../board/write" role="button">글쓰기</a>
  </div>

  <p></p>
  
<%@ include file="../board/SearchForm.jsp" %>  

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
		
		<c:if test="${not empty list}">
                <tbody>
                    <tr class="odd gradeX">
                        <td><a onclick= "requestAction('/board/view', ${board.bno})" href ='#'>${board.bno}</a></td>
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
    	
    	<%@ include file ="../board/pageNav.jsp" %>
    	
        </main>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>      
</body>
</html>



