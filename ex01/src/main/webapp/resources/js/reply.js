console.log('reply.js=========')

// get방식 요청
function fetchGet(url, callback){
	try{
		// url 요청
		fetch(url)
			// 요청결과 json문자열을 javascript 객체로 반환
			.then(response => response.json())
			// 콜백함수 실행
			.then(map => callback(map));			
	}catch(e){
		console.log('fetchGet',e);
	}
}

// post방식 요청
function fetchPost(url, obj, callback){
	try{
		// url 요청
		fetch(url
				, {
					method : 'post'
					, headers : {'Content-Type' : 'application/json'}
					, body : JSON.stringify(obj)
				})
			// 요청결과 json문자열을 javascript 객체로 반환
			.then(response => response.json())
			// 콜백함수 실행
			.then(map => callback(map));			
	}catch(e){
		console.log('fetchPost', e);
	}
	
}


// 댓글 조회및 출력
function getReplyList(){
	let bno = document.querySelector('#bno').value;
			// 수정★  여기서 가져오는 건 name = pageReply인 댓글 pageNo
	let page = document.querySelector('#pageReply').value;
	console.log('bno : ', bno);
	
	console.log('/reply/list/' + bno + '/' + page);
	console.log(`/reply/list/${bno}/${page}`);
	
	// url : 요청경로
	// callback : 응답결과를 받아 실행시킬 함수
	fetchGet(`/reply/list/${bno}/${page}`, replyView)
	
}

// 리스트 결과를 받아서 화면에 출력
function replyView(map){
	let list = map.list;
	let pageDto = map.pageDto;
	let criteria = map.criteria;
	console.log(list);
	console.log('pageDto=============', pageDto);
	console.log("criteria=====", criteria);
	
	// 리스트 사이즈를 확인하여 메세지 처리
	if(list.length == 0){
		replyDiv.innerHTML = '';
		replyDiv.innerHTML +=
				'<br>'
			+ '<div class="alert alert-primary" role="alert">'
			+' 댓글이 없습니다😥	'
			+'</div>';
	} else {
		
		
		let replyDivStr = 
			''
			+ '<table class="table text-break text-center"">                       '
			+ '  <thead>                                   '
			+ '    <tr>                                    '
			+ '      <th scope="col" class="col-2">#</th>                '
			+ '      <th scope="col" class="col-3">댓글</th>            '
			+ '      <th scope="col" class="col-3">작성자</th>             '
			+ '    </tr>                                   '
			+ '  </thead>                                  '
			+ '  <tbody>                                   ';
		
		// 리스트를 돌며 댓글목록을 생성
		list.forEach(reply => {
			replyDivStr +=  
			  '    <tr id="tr'+reply.rno+'" data-value="'+reply.reply+'">                                    '
			+ '      <th scope="row">' + reply.rno + '</th>                '
			+ '      <td class="text-start fst-italic">' + reply.reply 
			+ ' '
			+ ' 		<i class="fa-solid fa-delete-left" onclick="replyDelete('+ reply.rno +')"></i>'
			+ ' '
			+ '			<i class="fa-solid fa-pencil" onclick="replyUpdate('+ reply.rno +')"></i>'		
			+ '		 </td>                         '
			+ '      <td class = "fst-italic">' + reply.replyer
			+ '			<br>' + reply.replydate		
			+ '		 </td>                         '
			+ '    </tr>									';    	
		})
			                               
		replyDivStr += '  </tbody>                           '
						+ '</table>                          ';

		// 화면에 출력
		replyDiv.innerHTML = replyDivStr;
		
		// 페이지 블럭 생성 ( 페이징 )
		let pageBlock ='';
		
	 	pageBlock += // 이전 
		  '<nav aria-label="...">'
		 +  '<ul class="pagination justify-content-center">';
		 
		 // 이전버튼 (prev 블럭 보여주기)
		 if(pageDto.prev){
		 	 pageBlock += '' 
			    + '<li class="page-item"'
			    + 'onclick ="getPage('+(pageDto.startNo-1)+')">' 
		 		+ 	'<a class="page-link">Previous</a>' 
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
		 		+   '<a class="page-link" href="#">Next</a>'
		  		+ '</li>' ;
		 }
		 
		 pageBlock += 
		 			'</ul>'
				+'</nav>';
		
		replyDiv.innerHTML += pageBlock;
	}
}

	// 페이징 처리
	function getPage(page){
		// form에 있는 페이지번호 
		// 수정★
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













