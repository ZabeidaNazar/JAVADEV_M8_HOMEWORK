package com.homework.java;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.ZoneId;

@WebFilter(value = "/")
public class TimezoneValidateFilter extends HttpFilter {
    private Page page;

    @Override
    public void init(FilterConfig config) {
        page = new Page(config.getServletContext().getRealPath("/WEB-INF/classes/pages/time.html"));
    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String timezone = req.getParameter("timezone");

        if (timezone == null) {
            writeError(resp);
            return;
        }

        try {
            req.setAttribute("zone", ZoneId.of(timezone.toUpperCase()));
        } catch (Exception ignored) {
            writeError(resp);
            return;
        }

        chain.doFilter(req, resp);
    }

    private void writeError(HttpServletResponse resp) throws IOException {
        resp.setStatus(400);
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(page.getFile().replaceFirst("&\\{time}", "Invalid timezone"));
        resp.getWriter().close();
    }
}
