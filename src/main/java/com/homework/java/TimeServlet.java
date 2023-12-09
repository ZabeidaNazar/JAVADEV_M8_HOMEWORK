package com.homework.java;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/")
public class TimeServlet extends HttpServlet {
    private Page page;

    @Override
    public void init(ServletConfig config) {
        page = new Page(config.getServletContext().getRealPath("/WEB-INF/classes/pages/time.html"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");

        ZoneId zone = (ZoneId) req.getAttribute("zone");
        String time = ZonedDateTime.now(zone).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));

        resp.getWriter().write(page.getFile().replaceFirst("&\\{time}", time));
        resp.getWriter().close();
    }
}
