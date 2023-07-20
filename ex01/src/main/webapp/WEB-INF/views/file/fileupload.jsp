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
		
		// 파일 업로드 (fetch) 
		btnFileupload.addEventListener('click', function(){
			// ajax로 폼전송을 가능하게 해주는 formdata 객체 -> form전송이 필요한 경우는 이미지를 ajax로 업로드 할 때 필요! 
			let formData = new FormData(fileuploadForm);
			
			// 이름하고 값을 직접 넣어줄 수도 있음! 
			formData.append('name', 'momo');
			
			// 파일이 전송되기 전, 확장자 타입을 체크해야하기 때문에 내가 보낸 파일이 전송 가능한 크기, 확장자인지 formData값을 확인 
			console.log("formData : " + formData);
			
			// formData가 가지고 있는 값 확인하는 방법 * pair[0] = name의 값 / pair[1] = object
			for(var pair of formData.entries()){
			//	console.log(pair);
			//	console.log(pair[0] + ':' + pair[1]);
				if(typeof(pair[1]) == 'object'){
					
					let fileName = pair[1].name;
					let fileSize = pair[1].size;
					
					// 파일 확장자, 크기 체크 
					/*
						1) 서버에 전송 가능한 형식인지 확인 
						2) 최대 전송가능한 용량을 초과하지 않는지! 
					*/
					if(!checkExtension(fileName, fileSize)){
						return false;
					}
					console.log("name = " , fileName);
					console.log("size = " , fileSize)
				}
			}
			fetch('/file/fileuploadActionFetch'
					, {
				method : 'post'
				, body : formData
			})
			.then(response => response.json())
			.then(map => fileuploadRes(map));
		});
		
	})
	/*  정규표현식 ( 파일 확장자, 크기 체크) : 특정 규칙을 가지는 문자열을 검색 또는 치환할 때 사용*/ 
	function checkExtension(fileName, fileSize){
		let maxSize = 1024*1024*1;

		// 앞에 어떤 것이 오더라도 .exe, .sh, .zip, .alz 끝나는 문자열  $(~로끝나거나 를 의미.)
		let  regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		
		// 파일사이즈가 파일 최대사이즈 크기보다 크거나 같을 경우 
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과!!");
			return false;
		}
		/* 문자열에 정규식 패턴을 만족하는 값이 있으면 true, 없으면 false 반환 */
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		// 모두 exe|sh|zip|alz 다 통과하면 true (예를 들어 jpg의 경우는 파일 저장성공)
		return true;
	}
	
	// 파일 업로드 (fetch) 관련 함수 
	function fileuploadRes(map){
		if(map.result == 'success'){
			divFileuploadRes.innerHTML = map.message;
			// 파일 첨부가 성공이면 게시글 등록 
		}else{
			// 파일 첨부가 실패하면 메세지 띄우기 
			alert("첨부파일 업로드 중 오류 발생");
		}
	}
	
	function getFileList(){
		// bno 요소를 선택에서 그, 값을 가져옴
		let bno = document.querySelector("#bno").value;
		console.log("bno : ", bno);
		
		fetch('/file/list/'+ bno)
		.then(response => response.json())
		.then(map => viewFileList(map));
	}
	
	// 파일 삭제 콜백 함수  ( data 속성을 값을 가져오는 것 )
    function attachFileDelete(e){
	
		(e.dataset.aaa)?'true':'false';
		console.log(e.dataset.bno, e.dataset.uuid, e.dataset.aaa);
		console.log(e);
	
		let bno = e.dataset.bno;
		let uuid = e.dataset.uuid;

		console.log( "bno:",bno);
		console.log("uuid:",uuid);

		//fetch(`/file/delete/\${bno}/\${uuid}`)
		fetch('/file/delete/'+ bno +'/'+uuid)
		.then(response => response.json())
		.then(map =>delRes(map));
	}
	
	
	// 파일 삭제 결과 처리 함수
	function delRes(map){
		if(map.result == 'REST_SUCCESS'){
			console.log(map.message);
			
			// 삭제 성공
			divFiledelete.innerHTML = map.message;
			getFileList();
		}else{
			//console.log("result " , map.result);
			// 삭제 실패			
			alert("삭제 중 오류발생");
		}
	}
	
	function viewFileList(map){
		console.log("map : ", map);
		let content = '';
		
		if(map.list.length > 0){
		map.list.forEach(function(item, index){
			// URL 인코딩 
			let savePath = encodeURIComponent(item.savePath);
			
			console.log("item.savePath" , item.savePath);
			content += "<a href ='/file/download?fileName="
					+ savePath+"'>" 
				+ item.filename 
				+ '</a>'
			+ '<i class="fa-solid fa-square-xmark" data-bno="'+item.bno+'"data-uuid="'+item.uuid+'" onclick="attachFileDelete(this)"></i>' 
			+ '<br>' ;
			});
		}else{
			content = '등록된 파일이 없습니다.';
		}
		divFileupload.innerHTML = content;
	}
</script>
<script src="https://kit.fontawesome.com/59843f4445.js" crossorigin="anonymous"></script>
</head>
<body>
<h3>파일 업로드</h3>


	<form action="/file/fileuploadAction" 
			method="post" enctype="multipart/form-data" name ="fileuploadForm">
		bno : <input type ="text" name ="bno" value ="85" id="bno"> 			
		<br><br>
		<input type="file" name="files" multiple="multiple"> <br>
		<input type="file" name="files"> <br>
		<input type="file" name="files"> <br><br>
		<!--  파일만 등록하고자 할 때 -->
		<button type="submit">파일업로드</button>
		<!--  다른 페이지에 붙일 때 -->
		<button type="button" id="btnFileupload">Fetch파일업로드</button>

		<!--  rttr.addAttribute("message", message); -> ✔️  ${param.message} (쿼리스트링)-->
		<br><br>
		결과 : ${param.message}
		<br><br>
		<div id="divFileuploadRes"></div>
		
	</form>
	 
<h3> 파일 리스트 조회 </h3>
	 <button type="submit" id="btnList" onclick="getFileList()">파일조회</button>
	 <div id="divFileupload"></div>
	 <div id="divFiledelete"></div>
</body>
</html>