package xyz.mynt.singson.productms.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.mynt.singson.productms.service.ProductLogService;

@Aspect
@Component
public class ProductLogAdvice {
	
	@Autowired
	ProductLogService productLogService;
	
	@Pointcut(value = "execution (* xyz.mynt.singson.productms.controller.*.*(..) )")
	public void productControllerPointCut() {
		//point cut for product controller
	}
	
	@Around("productControllerPointCut()")
	public Object productLogger(ProceedingJoinPoint pjp) throws Throwable {
		
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().getSimpleName();
		
		//SENDING LOG - Class and method called + arguments sent
		productLogService.sendLog("From class: " +className);
		productLogService.sendLog("Invoked method: " +methodName);
		productLogService.sendLog("Argumenst passed: " +mapper.writeValueAsString(pjp.getArgs()));
		
		Object obj = pjp.proceed();
		
		//SENDING LOG - whole response entity
		productLogService.sendLog("Resulting Reponse: " +mapper.writeValueAsString(obj));

		return obj;
	}

}
