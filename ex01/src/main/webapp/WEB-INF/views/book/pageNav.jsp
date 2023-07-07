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


<script>
	function go(page){
		document.searchForm.pageNo.value=page;
		document.searchForm.submit();
	}
	
	function deletePost(){
		delNoList = document.querySelectorAll("[name=delNo]:checked");
		let delNo ="";
		
		delNoList.forEach((e)=>{
			delNo += e.value +',';
		});
		
		delNo = delNo.substr(0, delNo.lengh-1);
	}
</script>
<title>페이지블럭</title>


</head>
<body>
<%-- 페이지번호 : ${criteria.pageNo }
 --%>
  <div class ='form-inline text-center'>
<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-end">
  
    <li class="page-item ${pageDto.prev?'':'disabled'}">
      <a class="page-link" <c:if test ="${pageDto.prev }">onclick ="go(${pageDto.startNo-1 })"</c:if> href="#">Previous</a>
    </li>
    
    <c:forEach begin="${pageDto.startNo}" end="${pageDto.endNo}" var ="i">
    <li class="page-item"><a class="page-link ${i eq criteria.pageNo? 'active' : ''}"  href="#" onclick='go(${i})' >${i}</a></li>
     </c:forEach>
     
    <li class="page-item ${pageDto.next?'':'disabled'}">
      <a class="page-link"  onclick='go(${pageDto.endNo+1})' href="#">Next</a>
    </li>
  </ul>

   <div class ='form-inline pull-right'>
                <button type="button" class="btn btn-outline btn-danger" onclick="deletePost()">도서 삭제하기</button>
              	  </div>
</nav>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>      
</body>
</html>