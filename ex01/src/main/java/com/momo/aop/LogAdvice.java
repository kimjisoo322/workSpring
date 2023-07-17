package com.momo.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.momo.service.logService;
import com.momo.vo.logVo;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
/**
 * 	AOP(Aspect-Oriented-Programming)
 *  : 관점지향프로그래밍
 *    핵심 비지니스 로직과 부가적인 기능을 분리해 개발하는 방법론 
 *     코드의 중복을 줄이고 유지보수성을 향상시킬 수 있음.
 * 
 *  부가적인 관심사 : 로깅, 보안, 트랜잭션관리 등 
 *   애플리케이션에서 공통적으로 처리해야 하는 기능
 * 
 * */
public class LogAdvice {
	
	// 언제 들어갈건지 정하기   클래스/메서드/매개변수이름*은 모든 것
	/**
	 *  pointCut : 언제 어디에 적용할 건지 기술
	 *  
	 *  Before :타켓 객체의 메서드가 실행되기 전에 호출되는 어드바이스, joinPoint를 통해 파라메터 정보 참조 가능
	 * */
	@Before("execution(* com.momo.service.Board*.*(..))")
	public void logBefore() {
		log.info("===================");
		
	}
	/**
	 * 	joinPoint : 타켓에 대한 정보와 상태를 담고 있는 객체로 매개변수로 받아서 사용!
	 * */
	@Before("execution(* com.momo.service.Reply*.*(..))")
	public void logBeforeParams(JoinPoint joinPoint) {
		log.info("================AOP=============");

		log.info("Param :" + Arrays.toString( joinPoint.getArgs()));
		log.info("Target :" + joinPoint.getTarget());
		log.info("Method :" + joinPoint.getSignature().getName());
	}
	/**
	 * 	Around : 타켓의 메소드가 호출되기 이전 시점과 이후 시점에 모두 처리해야 할 필요가 있는 부가 기능 정의 
	 * 			 주업무로직을 실행하기 위해 JoinPoint의 하위클래스인 ProceedingJoinPoint타입의 파라메터를 ★필수적★으로 선언! 
	 * */
	// 시간 구하기 

	/*
	 * @Around("execution(* com.momo.service.Board*.*(..))") public Object
	 * logTime(ProceedingJoinPoint pjp) { StopWatch stopWatch = new StopWatch();
	 * stopWatch.start();
	 * 
	 * Object res = ""; try { // 주 업무로직 실행 res = pjp.proceed(); } catch (Throwable
	 * e) { e.printStackTrace(); }
	 * 
	 * stopWatch.stop(); log.info("★ target : " + pjp.getTarget() + "." +
	 * pjp.getSignature().getName()); log.info("★ 수행시간 : " +
	 * stopWatch.getTotalTimeMillis() + "(ms)초"); log.info("==== 전체 ====" +
	 * pjp.getTarget() + "." + pjp.getSignature().getName()+ "★ 수행시간 : " +
	 * stopWatch.getTotalTimeMillis() + "(ms)초" ); return res; }
	 */
	
	@Autowired
	logService logservice;
 	
	/**
	 * 	AfterThrowing 
	 *  : 타겟 메서드 실행 중 예외가 발생한 뒤에 실행할 부가기능
	 *    오류가 발생내역을 테이블에 등록 
	 * */
	
	 @AfterThrowing(pointcut = "execution(* com.momo.service.*.*(..))", throwing= "exception")
	public void logException(JoinPoint joinPoint, Exception exception) {
		 log.info("=====@AfterThrowing" );
		// 예외가 발생 시 예외 내용을 테이블에 저장함
		try {
			logVo logvo = new logVo();
			logvo.setClassname( joinPoint.getTarget().getClass().getName());
			logvo.setMethodname(joinPoint.getSignature().getName());
			logvo.setParams(Arrays.toString(joinPoint.getArgs()));
			logvo.setErrmsg(exception.getMessage());
			
			logservice.logInsert(logvo);
			

			log.info(" === 로그 테이블에 저 장☆ === " );
			
		} catch (Exception e) {
			log.info("log테이블 저장 중 예외발생!!");
			log.info(e.getMessage());
		}
	}
}



