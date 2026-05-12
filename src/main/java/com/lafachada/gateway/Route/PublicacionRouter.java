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
public class PublicacionRouter {

    @Value("${services.publicacion.url:http://localhost:8086}")
    private String publicacionUrl;

    @Bean
    public RouterFunction<ServerResponse> listarPublicaciones() {
        return route("get_all_publications")
            .GET("/api/v1/publicacion/all", http())
            .before((uri(publicacionUrl)))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerPublicacionPorId() {
        return route("get_publication_by_id")
            .GET("/api/v1/publicacion/{id}", http())
            .before((uri(publicacionUrl)))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> crearPublicacion() {
        return route("create_publication_by_id")
            .POST("/api/v1/publicacion/crear", http())
            .before((uri(publicacionUrl)))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> actualizarPublicacion() {
        return route("update_publication_by_id")
            .PATCH("/api/v1/publicacion/actualizar/{id}", http())
            .before((uri(publicacionUrl)))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> deletePublicationById() {
        return route("delete_publication_by_id")
            .DELETE("/api/v1/publicacion/eliminar/{id}", http())
            .before((uri(publicacionUrl)))
            .build();
    }
}
 