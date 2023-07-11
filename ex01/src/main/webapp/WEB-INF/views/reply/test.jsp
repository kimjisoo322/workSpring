<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reply</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/59843f4445.js" crossorigin="anonymous"></script>
<script type="text/javascript">

		/** 데이터 가져오는 담당*/		
		// ★1. 서버에 댓글list 요청
		function getList(){
			let bno = document.querySelector('#bno').value;
			let page = document.querySelector('#pageNo').value;
			
		 fetchGet('/reply/list/' + bno + '/' + page, replyView);
/* 		// url 요청 결과를 받아옴 == getMapping 방식
		fetch('/reply/list/' + bno + '/' + page)
			// 받아온 결과 response를 json object 형식으로 변환 
		.then(response => response.json())
			// 반환된 결과 list을 이용해 callback 함수를실행 시켜 화면에 출력!  
		.then(map => replyView(map)); 
*/
		}
		
		// 페이징 ★
		function getPage(page){
			document.querySelector('#pageNo').value = page;
			getList();
		}
		
/** 리스트 출력 담당*/
	//★2. list를 화면에 출력
		function replyView(map){
	
		// list를 map으로부터 꺼내옴 !!!! 
		let list = map.list;
		let pageDto = map.pageDto;
		let criteria = map.criteria;
		
		// 콘솔창에 리스트 출력
		console.log(map);
		console.log(pageDto);
		console.log(criteria);
		
			// replyDiv에 답글 출력
		replyDiv.innerHTML = '';
		 
		list.forEach((reply, index) =>{
			console.log(" ========= replyDiv 출력 ========");
			replyDiv.innerHTML 	+=
				
			'<figure id="reply'+index+'" data-value="'+reply.reply+'">'
			+ 		'<blockquote class="blockquote">'
			+ 			'<p> '+reply.reply 
			+ ' '
			+ '<i class="fa-regular fa-pen-to-square" onclick="updateReply('+index+' , '+reply.rno+')"></i>'
			+ ' ' 
			+ '<i class="fa-regular fa-trash-can" onclick="deleteReply('+reply.rno+')"></i>'
			+'</p>'
			+ 		'</blockquote>'
			
			+ 		'<figcaption class="blockquote-footer">'
		    + 			'작성자 '+reply.replyer+' <cite title="Source Title">'+reply.replyDate+'</cite>'
			+ 		'</figcaption>'
			+ 	'</figure>';
		
/* 			replyDiv.innerHTML += '<br>' + reply.bno;
			replyDiv.innerHTML += '<br>' + reply.rno;
			replyDiv.innerHTML += '<br>' + reply.reply;
			replyDiv.innerHTML += '<br>' + reply.replyer;
			replyDiv.innerHTML += '<br>' + reply.replyDate;	 */
		});		

			// 페이지 블럭 생성
			let pageBlock ='';
			
		 	pageBlock += // 이전 
			  '<nav aria-label="...">'
			 +  '<ul class="pagination justify-content-center">';
			 
			 // 이전버튼 (prev 블럭 보여주기)
			 if(pageDto.prev){
			 	 pageBlock += '' 
				    + '<li class="page-item"'
				    + 'onclick ="getPage('+(pageDto.startNo-1)+')">' 
			 		+ 	'<a class="page-link">이전</a>' 
			 		+ '</li>' ; 
			 }
			 
			// i 가 시작번호 부터 1씩 증가해서 끝번호와 같아질때까지 반복
			 for(i=pageDto.startNo; i<=pageDto.endNo; i++){
					// pageNo하고 i가 같으면 active 처리 	
				 let activeStr = (criteria.pageNo == i)? 'active' : '';
					
				 pageBlock +=	
					'<li class="page-item '+activeStr+'"'
				 + 'onclick="getPage('+i+')">'
				 + '<a class="page-link" href="#">'+i+'</a></li>';
			}
			
			// 다음버튼 (next 블럭 보여주기)
			 if(pageDto.next){
				 pageBlock += ''
			 		+ '<li class="page-item" onclick ="getPage('+(pageDto.endNo+1)+')">'
			 		+   '<a class="page-link" href="#">다음</a>'
			  		+ '</li>' ;
			 }
			 
			 pageBlock += 
			 			'</ul>'
					+'</nav>';
			
			replyDiv.innerHTML += pageBlock;
	}       
		    
	/* 	<c:forEach begin = "${pageDto.startNo }" end ="${pageDto.endNo}" var ="i">
	    <li class="page-item"><a class="page-link ${i eq criteria.pageNo? 'active':'' }" onclick='go(${i})'>${i}</a></li>
	    </c:forEach> */
		    
		// 댓글 삭제 
		function deleteReply(rno){
			fetch('/reply/delete/'+ rno)
			.then(response => response.json())
			.then(map => replyRes(map))
		}
	
		 // 수정 화면 보여주기  ( index 요소 선택해서 innerHTML로 화면 요소 보여주기 ) 
		 // index = 화면의 몇번째 줄인지 , rno = 댓글 번호
		function updateReply(index, rno){
			 // 박스 선택 (수정)
		  	let edit = document.querySelector('#reply'+index);
			 
			 //  data-value="'+reply.reply+' (박스에 있는 댓글 값 가져오기)
			let replyTxt = edit.dataset.value;
			
			 // 박스 선택하면 새로운 수정 화면 출력 
		  	edit.innerHTML = '';
		  	edit.innerHTML += 
		  		
		  		'<div class="input-group mb-3">'
		    	+'<input type="text" id ="editReply'+rno+'" value="'+replyTxt+'" class="form-control" aria-describedby="button-addon2">'
		    	+'<button class="btn btn-outline-secondary" type="button" id="replyBtn2" onclick ="updateReplyAction('+rno+')">수정하기</button>'
		 		+'</div>';

		}	
		 // 수정하기 
		function updateReplyAction(rno){
			
			// 0. 파라메터 수집 (id의 value 값 가져오기)
			let reply = document.querySelector('#editReply'+rno).value;
			
			console.log("editReply" , reply);
			
			// 1. 전송할 데이터를 javascript 객체로 생성 
			let replyObj = {
					rno : rno,
					reply : reply
			};
			
			// 2. 내가 만든 객체를 json 문자열 타입으로 변환 
			let replyJson = JSON.stringify(replyObj);
			
			console.log("replyJson", replyJson);
			
			// 3. 서버에 요청 (url)
			fetch('/reply/update' , {method : 'post'
											, headers : {'Content-Type' : 'application/json'}
											, body : replyJson})
			// 4. 응답 처리 
			.then(response => response.json())
			.then(map => replyRes(map));
			 
		}

	// 버튼이 생성되고 난 후 이벤트 부여 = window.oncload 생성
	window.onload = function(){
		// 리스트 조회 및 출력 
		getList();
 //  --> 리스트 호출하고 맨 처음 불러져 올 때, page는 1페이지!!  
 
		replyBtn.addEventListener('click', function(){
			//alert("댓글 작성");
			
			// 0. 파라메터 수집 (id의 value 값 가져오기)
			let bno = document.querySelector('#bno').value;
			let reply = document.querySelector('#reply').value;
			let replyer = document.querySelector('#replyer').value;
			
			console.log("bno : " , bno);
			console.log("reply : " , reply);
			console.log("replyer : " , replyer);
			
			// 1. 전송할 데이터를 javascript 객체로 생성 
			//   이름 : 값 
			let replyObj = {
					bno : bno,
					reply : reply,
					replyer : replyer
			};

			
			// 서버에 요청 
			fetchPost('/reply/insert', replyObj, replyRes)
			
			
			// 2. 내가 만든 객체를 json 문자열 타입으로 변환 
			/* let replyJson = JSON.stringify(replyObj);
			
			// 3. 서버에 요청 (url)
			// post 방식의 insertReply url 넣기    ===> get 방식은 url만 작성/ post 방식은 어떤  데이터를 전송하려는지 담아주기 

			fetch('/reply/insert', {method : 'post'
									, headers : {'Content-Type' : 'application/json'}
									, body : replyJson})
			// 4. 응답 처리 
			.then(response => response.json())
			.then(map => replyRes(map));
 */			
		});
	}
	
	// 결과 (등록, 삭제 => 성공이면 getList() 호출!)
	function replyRes(map){
		if(map.result == 'success'){
			// 등록 성공
			getList();
		}else{
			// 등록 실패 			
			alert(map.message);
		}
	}
	
	// 자주 사용하는 fetch url 함수로 정의해놓기 
	function fetchGet(url, callback){
		console.log(url);
		console.log(callback);
		
		// url 요청
		try{
		fetch(url)
			// 요청결과 json 문자열을 javascript 객체로 변환 
		.then(response => response.json())
			// 콜백함수 호출 
		.then(map => callback(map));
		}catch(e){
			console.log("fathGet "+ e);
		}
	}
	
	function fetchPost(url, obj, callback){
		console.log(url);
		console.log(callback);
		
		// url 요청
		try{
		fetch(url, {
				method : 'post'
				, headers : {'Content-Type' : 'application/json'}
				, body : JSON.stringify(obj)
		})
			// 요청결과 json 문자열을 javascript 객체로 변환 
		.then(response => response.json())
			// 콜백함수 호출 
		.then(map => callback(map));
		}catch(e){
			console.log("fatchPost "+ e);
		}
	}
	
</script>
</head>
<body>
<h4> 댓글남기기📧</h4>
 <input type="text" name="bno" value="85" id ="bno" hidden>
<input type="text" name="page" value="1" id ="pageNo" hidden>

<div class="input-group mb-3">
  <input type="text" id= "replyer" class="form-control" placeholder="작성자" aria-label="Recipient's username" aria-describedby="basic-addon2">
  <input type="text" id= "reply" class="form-control" placeholder="댓글쓰기" aria-label="Recipient's username" aria-describedby="basic-addon2">
  <span class="input-group-text" id="replyBtn">댓글작성</span>
</div>

<div id = "replyDiv"></div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>



