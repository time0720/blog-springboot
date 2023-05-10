package com.time.blog.aop;

import com.time.blog.domain.entity.Log;
import com.time.blog.domain.entity.LogModel;
import com.time.blog.mapper.LogMapper;
import com.time.blog.utils.IpUtils;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mjw
 * @date 2023/5/10
 */
@Aspect
@Component
public class OperationLogAspect {

    @Resource
    private LogMapper logMapper;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 设置操作日志的切入点，在注释的位置切入代码
     */
    @Pointcut("@annotation(com.time.blog.aop.OperationLogAnnotation)")
    public void operLogPointcut() {
    }

    @AfterReturning(value = "operLogPointcut()", returning = "result")
    public void saveOperLog(JoinPoint joinpoint, Object result) throws Throwable {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes 中获取 HttpServletRequest 的信息
        assert requestAttributes != null;
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        try {
            //将返回值转换成map集合
            Map<String, Object> map = objectToMap(result);
            Log log = new Log();
            LogModel logModel = new LogModel();
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinpoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OperationLogAnnotation annotation = method.getAnnotation(OperationLogAnnotation.class);
            if (annotation != null) {
                log.setModelCode(annotation.operModelCode());
                log.setType(annotation.operType());
                log.setDescription(annotation.operDesc());
            }
            //设置id
            log.setId(System.currentTimeMillis());
            // 操作时间
            log.setOperationTime(Timestamp.valueOf(sdf.format(new Date())));
            // 操作用户
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            Browser browser = userAgent.getBrowser();
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            Version browserVersion = userAgent.getBrowserVersion();
            log.setUserCode(browser.getName() + operatingSystem.getName() + browserVersion.getVersion());
            // 操作IP
            log.setIp(IpUtils.getIP(request));
            // 返回值信息
            log.setResult((String) map.get("message"));
            // 保存日志
            logMapper.saveLog(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }
}
