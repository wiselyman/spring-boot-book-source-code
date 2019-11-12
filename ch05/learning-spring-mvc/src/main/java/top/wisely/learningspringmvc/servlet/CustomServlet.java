package top.wisely.learningspringmvc.servlet;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Getter
@Setter
//@WebServlet({"/web-servlet"})
public class CustomServlet extends HttpServlet {
    private String msg;

    public CustomServlet(String msg) {
        this.msg = msg;
    }


    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();
        writer.println("Welcome " + msg);
        writer.close();
        log.info("--CustomServlet/service---");
    }

}
