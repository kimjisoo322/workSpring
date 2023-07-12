//  [ 자주 사용하는 fetch url 함수로 정의해놓기 ]
console.log("reply.js 연결 완★")
 		// get 방식 요청
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
		// post 방식 요청
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

	// 댓글 목록 함수 
	function getReplyList(){
		let bno =document.querySelector('#bno').value;
		let page = document.querySelector('#pageNo').value;
		console.log("bno : " , bno);
		console.log("page : ", page);
		
		console.log('/reply/list/' + bno + '/' + page);
		console.log(`/reply/list/${bno}/${page}`);
		 
		/*		     url : 요청 경로 
		 *  	callback : 응답 결과를 실행시킬 함수 
		 * */
				// /reply/list/{bno}/{page}
		fetchGet(`/reply/list/${bno}/${page}`, replyView);
	}	
	// list를  화면에 출력하는 함수 
	function replyView(map){
		let list = map.list;
		let pageDto = map.pageDto;
		let criteria = map.criteria;
		
		console.log("list : " , list);
		console.log("pageDto: " , pageDto);
		
		if(list.length == 0){
			replyDiv.innerHTML = '';
			replyDiv.innerHTML +=
				'<br>'
			+ '<div class="alert alert-primary" role="alert">'
			+' 댓글이 없습니다😥	'
			+'</div>';
		}else{
			let replyDivStr =''
	
			+ '<table class="table text-break text-center">'
			+' <thead>' 
			+ ' <tr>' 
			+ '   <th scope="col" class= "col-2">#</th>'
			+ '     <th scope="col" class= "col-3">댓글</th>'
			+ ' 	<th scope="col" class= "col-3">작성자</th>'
			+ ' </tr>'
			+ '</thead>'
			+ '<tbody>';
			
			list.forEach(reply => {
				replyDivStr +=
					' <tr id="tr'+reply.rno+'" data-value= "'+reply.reply+'">'
				+  ' <th scope="row">'+reply.rno+'</th>' 
				+  ' 	<td class = "text-start fst-italic">'+reply.reply
				+ ' '
				+ 		'<i class="fa-solid fa-delete-left" onclick="replyDelete('+reply.rno+')"></i>'
				+ ' '
				+ 		'<i class="fa-solid fa-pencil" onclick="replyUpdate('+reply.rno+')"></i></td>'   
				+   	'<td class = "fst-italic">'+reply.replyer+' <br>' +reply.replyDate+' </td>'  
				+   '</tr>';
			
			});
			
			replyDivStr +=
				' </tbody>'
				+ '</table>';
			
			replyDiv.innerHTML  = replyDivStr;
			
		
			// 페이지블럭 생성 
			let pageBlock =                                       
			
			`<nav aria-label="Page navigation example">`
			 +	`<ul class="pagination justify-content-center">`;
	
			
			if(pageDto.prev){
				pageBlock += ''
					+ 	`<li class="page-item" onclick = "getPage(${pageDto.startNo-1})">`
					+ 		`<a class="page-link">Previous</a>`
					+ 	`</li>`;
			}
			for(i= pageDto.startNo; i<= pageDto.endNo; i++){
				pageBlock += 
						` <li class="page-item" onclick ="getPage(${i})">`
					+		`<a class="page-link" href="#">${i}</a>`
					+	`</li>`;
			}
			
			if(pageDto.next){
				 pageBlock +=
					 `<li class="page-item" onclick="getPage(${pageDto.endNo+1})">`
				+ 		`<a class="page-link" href="#">Next</a>`
				+	`</li>`
				+  `</ul>`
				+`</nav>`;
				replyDiv.innerHTML  += pageBlock;
			}
			
			
		}
		
	
	}
	
	// 페이징처리 
	function getPage(page){
		document.querySelector('#pageReply').value = page;
		getReplyList();
	}
	
		// 답글 등록 버튼 
	function replyWrite(){
		/**
		 * url (요청경로) : /reply/insert
		 * obj : JSON 형식으로 전달할 데이터 
		 * callback : 콜백함수 (응답결과를 받아서 처리할 함수)
		 * */
		
		// 답글 작성 시 필요한 데이터 수집 - bno, reply, replyer 
		let bno = document.querySelector('#bno').value;
		let reply = document.querySelector('#reply').value;
		let replyer = document.querySelector('#replyer').value;
		
		// 전달할 객체를 obj로 생성해놓고 JSON문자열로 파싱!
		let obj = {bno : bno, reply : reply, replyer : replyer};
		
		console.log(obj);
		
		fetchPost('/reply/insert', obj, replyRes)
	}
	
		// 답글 삭제 함수 
	function replyDelete(rno){
		console.log("삭제) rno : " , rno);

		fetchGet('/reply/delete/'+ rno, replyRes);
	}
	
	 	// 답글 수정 화면 보여주기 
	function replyUpdate(rno){
		let edit = document.querySelector('#tr'+rno);
		let replyTxt = edit.dataset.value;
		
		edit.innerHTML = ''; 
		edit.innerHTML += 
			'<td colspan ="3">'
			
	  		+'<div class="input-group ">'
	  		+' <span class="input-group-text ">수정📌</span>'
			 +' <input type="text" class="form-control bg-secondary  bg-opacity-50 text-white" id ="editReply'+rno+'" value="'+replyTxt+'">'
			 +' <input type="button" id="replyEdit" onclick ="updateReplyAction('+rno+')" class="input-group-text" value="수정하기">'
		     +'</div>'
	 		+'</td>';
	}
	
		// 답글 수정하기 
	function updateReplyAction(rno){
		let reply = document.querySelector('#editReply' + rno).value;
		let obj = {rno : rno, reply : reply};
		

		fetchPost('/reply/update', obj, replyRes)
	}
	
	
	// 결과 (등록, 삭제 => 성공이면 getList() 호출!)
	function replyRes(map){
		console.log(map);
		// 성공 : 리스트 조회 및 출력  
		// 실패 : 메세지 출력 
		if(map.result == 'success'){
			// 등록 성공
			getReplyList();
		}else{
			// 등록 실패 			
			alert(map.message);
		}
	}
	