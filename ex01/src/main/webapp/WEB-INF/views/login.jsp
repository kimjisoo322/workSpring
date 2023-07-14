<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
    <title>로그인</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
      html,
      body {
        height: 100%;
      }

      body {
        display: flex;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #dae8f0;
      }

      .form-signin {
        max-width: 330px;
        padding: 15px;
      }

      .form-signin .form-floating:focus-within {
        z-index: 2;
      }

	  .middle{
	    border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        margin-bottom: -1px;
        
	  }
      .start  {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
      }
	  
      .end  {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
      }

    </style>
 <!--  JS 댓글(스크립트)  -->
<script src="/resources/js/common.js"></script>   
<script type="text/javascript">

	window.addEventListener('load', function(){
		btnLogin.addEventListener('click', function(e){
			// 기본이벤트 재거
			e.preventDefault();
			
			// 파라메터 수집
			let obj={
					id : document.querySelector('#id').value
				  , pw : document.querySelector('#pw').value 
			}
			console.log(" ==== 로그인 obj ==== ",obj);
			
			// 요청
			fetchPost('/loginAction', obj, loginCheckCall)
		})
		
		 btnSigninView.addEventListener('click',function(){
          signupForm.style.display='none';
          signinForm.style.display='';
        })

        btnSignupView.addEventListener('click',function(){
          signupForm.style.display='';
          signinForm.style.display='none';
        })
        
        signUpId.addEventListener('blur', function(){
        	
        	// 입력 체크
        	if(!signUpId.value){
        		signUpMsg.innerHTML = "아이디를 입력해주세요";
        		return;
        	}
        	
        	
			let obj = {id : document.querySelector("#signUpId").value}     
			console.log(" ==== 아이디 중복체크 obj, ==== ID" , obj);
		
			// 요청 (idCheckCall 콜백함수)
			fetchPost('/idCheck', obj, (map)=>{
				
				if(map.result == 'success'){
					// 아이디중복체크성공
					idCheckRes.value='1';
					signUpName.focus();
				}else{
					 // 사용자가 체크 했을 경우는 1로, 체크 이후에는 0으로 바꿔줘야 다시 체크할 때 1 부여가 가능 
					idCheckRes.value='0';
					signUpId.focus();
					    // 입력필드 초기화 
					signUpId.value='';
				}
					signUpMsg.innerHTML = map.message;
					console.log( " ==== idCheckCall ==== ", map);
			});
		// 아이디 체크 -> 서버에 갔다와야 함!
		});
		
/** 회원가입 폼 관련======================  */
		signUpPw.addEventListener('blur', function(){
			//alert('blur');
			
			// 비밀번호 체크 -> 서버에 들리지 않아도 됨!
			if(!signUpPw.value){
				signUpMsg.innerHTML = "비밀번호를 입력해주세요";
				return;
			}
			if(!pwCheck.value){
				signUpMsg.innerHTML = "비밀번호 확인을 입력해주세요";
				return;
			}
			if(signUpPw.value == pwCheck.value){
				signUpMsg.innerHTML = '비밀번호 일치!';
				pwCheckRes.value =1;
				console.log("결과 : ",pwCheckRes);
			}else{
				signUpMsg.innerHTML = "비밀번호가 일치하지 않습니다";
				pwCheckRes.value =0;
				console.log("else 결과: ",pwCheckRes);
				signUpId.focus();
				pwCheck.value='';
				signUpPw.value='';
			}
		});
		
		btnSignup.addEventListener('click', function(e){
			// 기본이벤트초기화
			e.preventDefault();
			
			// 폼에 다 입력이 되었는지, pwCheckRes의 결과가 1인지 체크 
			let id = signUpId.value;
			let name = signUpName.value;
			let pw = signUpPw.value;
			
			if(!id){
				signUpMsg.innerHTML = "아이디를 입력해주세요";
				
			}
			if(!name){
				signUpMsg.innerHTML = "이름을 입력해주세요";
				
			}
			if(!pw){
				signUpMsg.innerHTML = "비밀번호를 입력해주세요";
			}
			
			 // 아이디 중복체크 확인
			if(idCheckRes.value != 1){
				signUpMsg.innerHTML = "아이디 중복체크를 해주세요!!";
				signUpId.focus();
				return;
			}
			 // 비밀번호 일치 확인 
			if(pwCheckRes.value != 1){
				signUpMsg.innerHTML = "비밀번호가 일치하는지 확인 해주세요!!";
				pwCheck.focus();
				return;
			} 
			
			let obj1 = {id : id, name : name, pw : pw}
	
			console.log(" ==== 회원가입 버튼 obj1, ====" , obj1);
			
			// 요청 (회원가입)
			fetchPost('/signUp', obj1, (map)=>{
				
				console.log('signUp의 obj1 ======', obj1);
				
				if(map.result == 'REST_SUCCESS'){
					alert(map.message);
					location.href = "/login?message="+map.message;
					
				}else{
					signUpMsg.innerHTML = map.message;
				}
					console.log( " ==== pwCheckCall ==== ", map);
			});
		})
});
	
	function loginCheckCall(map){
		// 로그인 성공 -> list 로 이동
		// 실패 -> 메세지 처리
		if(map.result == 'success'){
			location.href="/board/list_boot"
		} else {
			message.innerHTML = map.message;
		}
		console.log("======= loginCheckCall ===== ", map);
	}
	
	
</script>  
  </head>
  <body class="text-center">
    
<main class="form-signin w-100 m-auto">

<!--  로그인 폼 -->
  <form name='signinForm'>
  
    <img class="mb-4" src="/resources/css/books.png" alt="" width="72" height="57">
    <h1 class="h3 mb-3 fw-normal">WelCome✨</h1>
	
	<!--  오류 메세지 출력 -->
	<div id = "message">${param.message}</div>
    <div class="form-floating">
      <input  type="text" class="form-control start"   id="id" name ="id" required="required">
      <label for="id">ID</label>
    </div>
    <div class="form-floating">
      <input  type="password" class="form-control end" class ="end" id="pw" name = "pw" placeholder="Password" required="required">
      <label for="pw">Password</label>
    </div>

    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" value="remember-me"> 아이디 저장
      </label>
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit" id='btnLogin'>로그인</button>
    <p class="mt-5 mb-3 text-muted">&copy; Have A Nice Day</p>
  </form>
  
<!--   회원가입  폼 ============-->
  
  <form name='signupForm' style='display:none'>

    <img class="mb-4"  src="/resources/css/books.png" alt="" width="72" height="57">
    <h1 class="h3 mb-3 fw-normal">Sign Up✨</h1>

	<!--  오류 메세지 출력 -->
	<div id = "signUpMsg"></div>
	
    <div class="form-floating">
      <input  type="text" class="form-control start" id="signUpId" placeholder="ID">
      <label for="id">Id</label>
    </div>
       <div class="form-floating">
      <input type="text" class="form-control middle" id="signUpName" placeholder="Name">
      <label for="signUpName">Name</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control middle" id="signUpPw" placeholder="Password">
      <label for="pw">Password</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control end" id="pwCheck" placeholder="Password">
      <label for="pwCheck">PasswordCheck</label>
    </div>
    
    <button class="w-100 btn btn-lg btn-secondary" id='btnSignup' type="submit">회원가입</button>
    <p class="mt-5 mb-3 text-muted">&copy; Thank You For Coming</p>
    
      
  	<!--  아이디, 비밀번호의 value가 1일 경우 체크완료♡ -->
  	<input type="text" value="0" id="idCheckRes" >
  	<input type="text" value="0" id="pwCheckRes" >
  
  </form>
		
<!--  회원가입 또는 로그인 버튼 눌렀을 때 form을 번갈아 보여줌  -->
    <input  type ="button" id ="btnSignupView" value ="회원가입">
    <input  type ="button" id ="btnSigninView" value ="로그인">
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
 </body>
</html>
 