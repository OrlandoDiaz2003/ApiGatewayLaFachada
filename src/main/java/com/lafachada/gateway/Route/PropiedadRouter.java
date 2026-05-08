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
public class PropiedadRouter {


    @Value("${services.propiedad.url:http://localhost:8081}")
    private String propiedadServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> getPropiedadById() {
        return route("get_propiedad_by_id")
            .GET("/api/v0/propiedad/{id}", http())
            .before(uri(propiedadServiceUrl))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> deletePropiedad() {
        return route("delete_propiedad_by_id")
            .DELETE("/api/v0/propiedad/{id}", http())
            .before(uri(propiedadServiceUrl))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> buscarPropiedad() {
        return route("buscar_propiedad")
            .GET("/api/v0/propiedad", http())
            .before(uri(propiedadServiceUrl))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> crearPropiedad() {
        return route("crear_propiedad")
            .POST("/api/v0/propiedad", http())
            .before(uri(propiedadServiceUrl))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> modificarPropiedad() {
        return route("modificar_propiedad")
            .PATCH("/api/v0/propiedad/{id}", http())
            .before(uri(propiedadServiceUrl))
            .build();
    }
}
