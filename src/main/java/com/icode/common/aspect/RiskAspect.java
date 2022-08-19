package com.icode.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.icode.common.exception.BusinessException;
import com.icode.slotchain.ProcessorSlotChain;
import com.icode.slotchain.SlotChainProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * 注解环绕
 *
 * @Author: tiantianlikeU。
 * @Date: 2022/8/17 14:50
 */
@Aspect
@Component
public class RiskAspect {

    /**
     * 日志组件
     */
    private static final Logger logger = LoggerFactory.getLogger(RiskAspect.class);

    @Around("@within(com.icode.common.annos.Risk)")
    public Object riskEntry(ProceedingJoinPoint proceedingJoinPoint) {
        Object proceed = null;
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Class<?> clazz = proceedingJoinPoint.getTarget().getClass();
        Method method = methodSignature.getMethod();
        Object[] args = proceedingJoinPoint.getArgs();
        try {

            ProcessorSlotChain processorSlotChain = SlotChainProvider.newSlotChain();
            // 调用卡槽验证
            processorSlotChain.entry(args);
            // 调用实际接口
            proceed = proceedingJoinPoint.proceed(args);
        } catch (BusinessException be) {
            String message = be.getMessage();
            String errorCode = be.getErrCode();
            JSONObject resultJson = new JSONObject();
            resultJson.put("errorCode", errorCode);
            resultJson.put("message",message);
            return resultJson;

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        }
        return proceed;
    }


    @Around("@annotation(com.icode.common.annos.Risk)")
    public Object riskEntrys(ProceedingJoinPoint proceedingJoinPoint) {
        return riskEntry(proceedingJoinPoint);
    }
}
