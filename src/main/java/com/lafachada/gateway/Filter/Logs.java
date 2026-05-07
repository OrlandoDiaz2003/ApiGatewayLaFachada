package com.lafachada.gateway.Filter;

import java.io.IOException;
import jakarta.servlet.Filter;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Logs implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        long startTime = System.currentTimeMillis();
        log.info("==> Peticion Recibida: {} {}", req.getMethod(), req.getRequestURI());

        chain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;
        log.info("<== Respuesta Enviada: {} {} | Status: {} | Time: {}ms",
                req.getMethod(), req.getRequestURI(), res.getStatus(), duration);
    }


}
