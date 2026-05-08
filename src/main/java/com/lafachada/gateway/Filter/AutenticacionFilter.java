package com.lafachada.gateway.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import com.lafachada.gateway.Service.JwtService;

@Component
public class AutenticacionFilter implements HandlerFilterFunction<ServerResponse, ServerResponse>{

    @Autowired
    private JwtService jwtService;

    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception {
        String authHeader = request.headers().firstHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);

        try {
            String username = jwtService.extractUsername(token);
            if (username != null && jwtService.isTokenValid(token, username)) {
                return next.handle(request);
            }
        } catch (Exception e){
        }
        return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();

    }
}
