<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  Header -->
<%@include file="./Header.jsp" %>

<!-- 한건 조회 : ${book } -->
	<div id ="page-wrapper">
	<div class ="row">
	             <div class="col-lg-4">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <i class="fa-solid fa-bookmark">   ${book.no }  ${book.author } </i>
                        </div>
                        <div class="panel-body">
                        	${book.title }
                        
                        </div>
                        <div class="panel-footer">
                            ${book.publisher }
                        </div>
                        
                    </div>
                </div>
			</div>
					<!--  댓글 리스트  -->
	<br>
	<h3>cHaT📧</h3>
	<div class="input-group">
	  <span class="input-group-text">댓글</span>
	  <!-- 수정★ -->
	  <input type ="hidden" name= "pageReply" value="${param.pageNo }" id = "pageReply" >
	  <input type="text" aria-label="First name" class="form-control" id = "replyer" value="작성자">
	  <input type="text" aria-label="First name" class="form-control" id = "reply" placeholder="댓글을 입력해주세요" >
	  <input type="button" id="btnReplyWrite"  value= "등록" aria-label="Last name" class="input-group-text">
	</div>
		<div id = "replyDiv">
		
		</div>
		</div>
	
	
		<!--  Footer -->
	
<%@include file="./Footer.jsp" %>