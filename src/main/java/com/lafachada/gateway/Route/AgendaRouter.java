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
public class AgendaRouter {

    @Value("${services.agenda.url:http://localhost:8082}")
    private String agendaServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> buscarPorIdCliente() {
        return route("get_id_client")
                .GET("/api/v0/agenda/cliente/{id}", http())
                .before(uri(agendaServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerPublicacionAgendada() {
        return route("get_id_publication_meeting")
                .GET("/api/v0/agenda/cliente/{id}/publicaciones-pendientes", http())
                .before(uri(agendaServiceUrl))
                .build();
    }


    @Bean
    public RouterFunction<ServerResponse> buscarPorIdVendedor() {
        return route("get_id_seller")
                .GET("/api/v0/agenda/vendedor/{id}", http())
                .before(uri(agendaServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> deleteAgenda() {
        return route("delete_by_id")
                .DELETE("/api/v0/agenda/{id}", http())
                .before(uri(agendaServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> crearAgenda() {
        return route("create_agenda")
                .POST("/api/v0/agenda", http())
                .before(uri(agendaServiceUrl))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> cambiarEstado() {
        return route("cambiar_estado")
                .PUT("api/v0/agenda/{id}/estado", http())
                .before(uri(agendaServiceUrl)).build();
    }
}
