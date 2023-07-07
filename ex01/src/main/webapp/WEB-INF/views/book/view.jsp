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
		</div>
		<!--  Footer -->
<%@include file="./Footer.jsp" %>