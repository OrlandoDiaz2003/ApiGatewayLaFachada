package com.lafachada.gateway.Route;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;


@Configuration
public class ResenaRouter {

    @Value("${services.resena.url:http://localhost:8086}")
    private String resenaUrl;

    @Bean
    public RouterFunction<ServerResponse> listaResena() {
        return route("get_all_resena")
            .GET("/api/v1/resenas/all", http())
            .before((uri(resenaUrl)))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> listaResenaPorUsuario() {
        return route("get_all_resena_by_userid")
            .GET("/api/v1/resenas/usuario/{id}", http())
            .before((uri(resenaUrl)))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerResenaPorId() {
        return route("get_by_id")
            .GET("/api/v1/resenas/{id}", http())
            .before((uri(resenaUrl)))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerResenaPorPublicacionId() {
        return route("get_by_publication_id")
            .GET("/api/v1/resenas/publicacionId/{id}", http())
            .before((uri(resenaUrl)))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> crearResena() {
        return route("create_resena_by_id")
            .POST("/api/v1/resenas/crear", http())
            .before((uri(resenaUrl)))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> deleteResenaById() {
        return route("delete_resena_by_id")
            .DELETE("/api/v1/resenas/{id}", http())
            .before((uri(resenaUrl)))
            .build();
    }
}
