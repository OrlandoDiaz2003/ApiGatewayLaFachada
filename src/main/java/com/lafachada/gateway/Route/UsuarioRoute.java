package com.lafachada.gateway.Route;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import com.lafachada.gateway.Filter.AutenticacionFilter;

@Configuration
public class UsuarioRoute {

    @Value("${services.usuario.url:http://localhost:8083}")
    private String usuarioServiceUrl;

    @Autowired
    private AutenticacionFilter autenticacionFilter;

    @Bean
    public RouterFunction<ServerResponse> login() {
        return route("login")
                .POST("/users/login", http())
                .before(uri(usuarioServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> register() {
        return route("register")
                .POST("/users/register", http())
                .before(uri(usuarioServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerUsuario() {
        return route("get_user_by_id")
                .GET("/users/{id}", http())
                .filter(autenticacionFilter::filter)
                .before(uri(usuarioServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> actualizarUsuario() {
        return route("update_user_by_id")
                .PUT("/users/{id}", http())
                .filter(autenticacionFilter::filter)
                .before(uri(usuarioServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> eliminarUsuario() {
        return route("delete_user_by_id")
                .DELETE("/users/{id}", http())
                .filter(autenticacionFilter::filter)
                .before(uri(usuarioServiceUrl))
                .build();
    }
}
