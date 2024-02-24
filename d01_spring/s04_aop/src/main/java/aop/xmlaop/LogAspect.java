package aop.xmlaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author zt
 * @date 2023/8/21
 **/
@Component
public class LogAspect {

    /**
     * 前置通知
     */
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("前置通知-->方法名称：" + methodName + "，参数：" + Arrays.toString(args));
    }

    /**
     * 后置通知
     */
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("后置通知-->方法名：" + methodName);
    }

    /**
     * 返回通知
     */
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回通知-->方法名称：" + methodName + "，返回结果：" + result);
    }

    /**
     * 异常通知
     */
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("异常通知-->方法名称：" + methodName + "，异常信息：" + ex);
    }

    /**
     * 环绕通知
     */
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argString = Arrays.toString(args);
        Object result = null;
        try {
            System.out.println("环绕通知-->目标方法之前执行");

            // 调用目标方法
            result = joinPoint.proceed();

            System.out.println("环绕通知-->目标方法返回值之后");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕通知-->目标方法出现异常执行");
        } finally {
            System.out.println("环绕通知-->目标方法执行完毕执行");
        }
        return result;
    }
}
