<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
<script type="text/javascript">
	
	// 파일 조회 버튼 클릭시, getFileList() 함수 호출 
	window.addEventListener('load', function(){
		btnList.addEventListener('click', function(){
			getFileList();
		})
	})
	function getFileList(){
		// bno 요소를 선택에서 그, 값을 가져옴
		let bno = document.querySelector("#bno").value;
		console.log("bno : ", bno);
		
		fetch('/file/list/'+ bno)
		.then(response => response.json())
		.then(map => viewFileList(map));
	}
	
	function viewFileList(map){
		console.log("map : ", map);
		let content = '';
		
		if(map.list.length > 0){
		map.list.forEach(function(item, index){
			content += item.filename + '<br>';
			content += item.filename + '/' + item.savePath+ '<br>' ;
			
			
			});
		}else{
			content = '등록된 파일이 없습니다.';
		}
		divFileupload.innerHTML = content;
	}
</script>
</head>
<body>
<h3>파일 업로드</h3>


	<form action="/file/fileuploadAction" 
			method="post" enctype="multipart/form-data">
		bno : <input type ="text" name ="bno" value ="85" id="bno"> 			
		<br><br>
		<input type="file" name="files"> <br>
		<input type="file" name="files"> <br>
		<input type="file" name="files"> <br><br>
		<button type="submit">파일업로드</button>
		<!--  rttr.addAttribute("message", message); -> ✔️  ${param.message} (쿼리스트링)-->
		결과 : ${param.message}
	</form>
	 
<h3> 파일 리스트 조회 </h3>
	 <button type="submit" id="btnList" onclick="getFileList()">파일조회</button>
	 <div id="divFileupload"></div>
	 
</body>
</html>