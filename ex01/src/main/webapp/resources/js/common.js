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
	
	// 로그인 
