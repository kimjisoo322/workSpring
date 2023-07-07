<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--  Header -->
<%@include file="./Header.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">📚도서관리시스템📚</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                          			 도서목록
                        </div>
                        
                        <p></p>
                    
              <!-----------------------   검색 폼      ---------------------->

                        <form action="/book/list" name ="searchForm">
                        <input type= "text" name ="pageNo" value ="${criteria.pageNo}">
                        <input type ="text" name = "no" value ="${book.no }">
                        <p></p>
                        <div class ='form-inline text-center'>
                          <div class="form-group">
                               <select class="form-control" name ="searchField">
                                   <option value = "title"${criteria.searchField == "title"? "selected" : '' }>도서명</option>
                                   <option value = "author"${criteria.searchField == "author"? "selected" : '' }>저자명</option>
                                   <option value = "publisher"${criteria.searchField == "publisher"? "selected" :'' }>출판사명</option>
                               </select>
                         </div>
                        <div class="form-group">
                            <input class="form-control" name ="searchWorld" placeholder="Enter text" value ="${criteria.searchWorld }">
                        </div>
                        
            		<button type="submit" class="btn btn-default">검색하기</button>
                       </div>
                    
               <!-----------------------   검색 폼 끝   ---------------------->          
              
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                    	<th></th>
                                        <th>No</th>
                                        <th>제목</th>
                                        <th>저자</th>
                                        <th>출판사</th>
                                        <th>대여여부</th>
                                    </tr>
                                </thead>
                                <c:forEach items="${list}" var ="book" step="1">
									<c:if test="${not empty list }">
                                <tbody>
                                    <tr class="odd gradeX">
                                   	<td class = "center">
							 <!--  삭제용 체크 박스 -->
									<input type = "checkbox" name = "delNo" value = "${book.no}"></input>
									</td>
                                        <td><a href = "#" onclick="requestAction('/book/view', ${book.no})">${book.no }</a></td>
                                        <td>${book.title }</td>
                                        <td>${book.author }</td>
                                        <td class="center">${book.publisher }</td>
                                        <td class="center">${book.rentStr }</td>
                                    </tr>
                                   
                                </tbody>
                                </c:if>
                                </c:forEach>
                            </table>
                              </form>
                            <!--  페이징  -->
                            <%@include file="../book/pageNav.jsp" %>
                            
                       <!--      /.table-responsive
                            <div class="well">
                                <h4>DataTables Usage Information</h4>
                                <p>DataTables is a very flexible, advanced tables plugin for jQuery. In SB Admin, we are using a specialized version of DataTables built for Bootstrap 3. We have also customized the table headings to use Font Awesome icons in place of images. For complete documentation on DataTables, visit their website at <a target="_blank" href="https://datatables.net/">https://datatables.net/</a>.</p>
                                <a class="btn btn-default btn-lg btn-block" target="_blank" href="https://datatables.net/">View DataTables Documentation</a>
                            </div> -->
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
    </div>

	    
		<!--  Footer -->
<%@include file="./Footer.jsp" %>
	
	