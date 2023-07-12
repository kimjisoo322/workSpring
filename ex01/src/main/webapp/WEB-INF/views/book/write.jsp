<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  Header -->
<%@include file="./Header.jsp" %>


  <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">📚도서 등록📚</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                          			원하시는 도서를 등록해주세요
                        </div>
                        
                        <p></p>
              
     
                     <form role="form"  action ="/book/writeAction" method="post" accept-charset="UTF-8">
                     <!--  panel-body = padding 값 준 느낌 -->
                     <div class="panel-body">
 					 <div class="row">
  						<div class="col-md-10">
  						<div class="col-md-offset-3">
    					<div class="form-group has-success">
       				 <label class="control-label" for="inputSuccess" > 🌱 제목</label>
        			<input type="text" class="form-control " id="inputSuccess" name ="title" required>
     						 </div>
     						 </div>
    						</div>
  						</div>
  						
  						 <div class="row">
  						<div class="col-md-10">
  						<div class="col-md-offset-3">
    					<div class="form-group has-success">
       				 <label class="control-label" for="inputSuccess"> 🌱저자</label>
        			<input type="text" class="form-control " id="inputSuccess" name="author" required>
     						 </div>
     						 </div>
    						</div>
  						</div>
  						
  						
  						 <div class="row">
  						 <div class="col-md-10">
  						<div class="col-md-offset-3">
    					<div class="form-group has-success">
       				 <label class="control-label" for="inputSuccess"> 🌱출판사</label>
        			<input type="text" class="form-control " id="inputSuccess" name="publisher" required>
     						 </div>
    						</div>
    						</div>
  						</div>
  						
  			<!-- 			 <div class="row">
  						 <div class="col-md-10">
  						<div class="col-md-offset-3">
    					<div class="form-group has-success">
    					<div class="mb-3">
       				 <label class="control-label" for="formFile">파일선택</label>
        			<input type="file" class="form-control " id="formFile">
        			</div>
     						 </div>
    						</div>
    						</div>
  						</div> -->
			
			<!-- panel-body 끝 -->
  					</div>
                            
                            <!-- /.table-responsive -->
                            <div class="well">
                                <h4>Thank You For Coming</h4>
                                <p>도서 등록을 원하시면 하단의 '등록하기' 버튼을 클릭해주세요.비어있는 내용이 없는 지 다시 한번 더 확인해주세요.
                               <br>To register a book, click the 'Register' button at the bottom. Please check again to see if there are any empty contents.</p>
                                <button type="submit" class="btn btn-lg btn-success btn-block" >등록하기</button>
                              
                            </div>
                              </form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>




		<!--  Footer -->
<%@include file="./Footer.jsp" %>