package ru.gb.springdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AnnotationsHandler {

    @Pointcut("@within(ru.gb.springdemo.aspect.Timer)")
    public void classAnnotatedTimer() {}

    @Pointcut("@annotation(ru.gb.springdemo.aspect.Timer)")
    public void methodAnnotatedTimer() {}

    @Around("classAnnotatedTimer() || methodAnnotatedTimer()")
    public Object timerHandler(ProceedingJoinPoint joinPoint) {
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("exception: {}, {}", e.getClass(), e.getMessage());
        }
        long deltaTime = System.currentTimeMillis() - start;
        log.info("=====   Timer   =====");
        log.info("{} - {} #({}ms)", joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(), deltaTime);
        return result;
    }

    @Pointcut("@annotation(ru.gb.springdemo.aspect.RecoverException)")
    public void methodAnnotatedRecoverException() {}

    @Around("methodAnnotatedRecoverException()")
    public Object recoverExceptionHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        Class<? extends RuntimeException>[] exceptions = extractExceptions(joinPoint);
        String returnTypeName = ((MethodSignature)joinPoint.getSignature())
                .getReturnType().getSimpleName();
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            if (isExceptionContains(exceptions, e)) {
                throw e;
            } else {
                if (Character.isUpperCase(returnTypeName.charAt(0))) {
                    return null;
                }
                switch (returnTypeName) {
                    case "boolean": return false;
                    case "char": return ' ';
                    case "void": return null;
                    default: return 0;
                }
            }
        }
        return result;
    }

    private Class<? extends RuntimeException>[] extractExceptions(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        RecoverException annotation = methodSignature.getMethod()
                .getAnnotation(RecoverException.class);
        if (annotation != null) {
            return annotation.noRecoverFor();
        }
        return null;
    }

    private boolean isExceptionContains(Class<? extends RuntimeException>[] exceptions, Throwable e) {
        if (exceptions != null) {
            for (Class<? extends RuntimeException> exception : exceptions) {
                if (exception.equals(e.getClass())) return true;
                if (exception.isAssignableFrom(e.getClass())) return true;
            }
        }
        return false;
    }
}
