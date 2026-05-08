package com.lafachada.gateway.Route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

import org.springframework.beans.factory.annotation.Value;


@Configuration
public class PagoRouter {

    @Value("${services.pago.url:http://localhost:8084}")
    private String pagoServiceUrl;


    @Bean
    public RouterFunction<ServerResponse> obtenerPagoPorId() {
        return route("get_payments_by_id")
            .GET("/payments/{id}", http())
            .before(uri(pagoServiceUrl))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> pagar() {
        return route("pay")
            .POST("/payments", http())
            .before(uri(pagoServiceUrl))
            .build();
    }
}
