package top.wisely.learningspringmvc.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

@Slf4j
//@WebFilter({"/*"})
public class CustomFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("--CustomFilter/doFilter---");
        chain.doFilter(request, response);
    }
}
