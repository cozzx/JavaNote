package aop.annoaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author zt
 * @date 2023/8/21
 **/
// @Aspect 表示这个类是一个切面类
@Aspect
// @Component 注解保证这个切面类能够放入IOC容器
@Component
// 相同目标方法上同时存在多个切面时，切面的优先级，value 越小优先级越高
@Order(value = 1)
public class LogAspect {

    /**
     * 正常执行顺序：
     * 环绕通知-->目标方法之前执行
     * 前置通知-->方法名称：add，参数：[1, 2]
     * 方法内部 result = 3
     * 返回通知-->方法名称：add，返回结果：3
     * 后置通知-->方法名：add
     * 环绕通知-->目标方法返回值之后
     * 环绕通知-->目标方法执行完毕执行
     * 执行成功:3
     */

    /**
     * 异常执行顺序：
     * 环绕通知-->目标方法之前执行
     * 前置通知-->方法名称：div，参数：[1, 0]
     * 异常通知-->方法名称：div，异常信息：java.lang.ArithmeticException: / by zero
     * 后置通知-->方法名：div
     * 方法异常提示信息
     * 环绕通知-->目标方法出现异常执行
     * 环绕通知-->目标方法执行完毕执行
     */

    /**
     * 设置切入点和通知类型
     * 切入点表达式：execution(访问修饰符 增强方法返回类型 增强方法所在类全路径.方法名称(方法参数))
     * 通知类型：前置 @Before(value = "切入点表达式配置切入点")
     */
    @Before(value = "execution(public int aop.annoaop.CalculatorImpl.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("前置通知-->方法名称：" + methodName + "，参数：" + Arrays.toString(args));
    }

    /**
     * 重用切入点表达式
     */
    @Pointcut(value = "execution(public int aop.annoaop.CalculatorImpl.*(..))")
    public void pointCut() {
    }

    /**
     * 通知类型：后置 @After(value = "重用切入点表达式")
     */
    @After(value = "pointCut()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("后置通知-->方法名：" + methodName);
    }

    /**
     * 通知类型：返回 @AfterReturning()
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回通知-->方法名称：" + methodName + "，返回结果：" + result);
    }

    /**
     * 通知类型：异常 @AfterThrowing() 获取到目标方法异常信息
     */
    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("异常通知-->方法名称：" + methodName + "，异常信息：" + ex);
    }

    /**
     * 通知类型：环绕 @Around()
     */
    @Around(value = "pointCut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        // JoinPoint 对象
        // 1. 通过 JoinPoint 对象获取目标方法的签名对象
        Signature signature = joinPoint.getSignature();
        // 2. 通过方法的签名对象获取目标方法信息
        String methodName = signature.getName();
        System.out.println("methodName = " + methodName);
        int modifiers = signature.getModifiers();
        System.out.println("modifiers = " + modifiers);
        String declaringTypeName = signature.getDeclaringTypeName();
        System.out.println("declaringTypeName = " + declaringTypeName);
        // 3. 通过JoinPoint对象获取外界调用目标方法时的实参列表
        Object[] args = joinPoint.getArgs();
        String argString = Arrays.toString(args);
        System.out.println("argString = " + argString);

        Object result = null;
        try {
            System.out.println("环绕通知-->目标方法之前执行");

            // 调用目标方法
            result = joinPoint.proceed();
            System.out.println("result = " + result);

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
