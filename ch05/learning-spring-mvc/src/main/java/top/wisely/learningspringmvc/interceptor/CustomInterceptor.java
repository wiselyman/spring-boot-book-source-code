package top.wisely.learningspringmvc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CustomInterceptor implements HandlerInterceptor {
    private final static String START_TIME = "startTime";
    private final static String PROCESS_TIME = "processTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(START_TIME, System.currentTimeMillis());
        log.info("preHandle处理中...");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute(START_TIME);
        long endTime = System.currentTimeMillis();
        request.removeAttribute(START_TIME);
        request.setAttribute(PROCESS_TIME, endTime - startTime);
        log.info("postHandle处理中...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("请求处理时间为：" + request.getAttribute(PROCESS_TIME) + "毫秒");
        request.removeAttribute(PROCESS_TIME);
    }
}
