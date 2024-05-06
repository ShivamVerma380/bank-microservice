package com.eazybank.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {
    
    public static final String CORRELATION_ID = "eazybank-correlation-id";

    public String getCorrelationId (HttpHeaders requestHeaders) {
        if (requestHeaders.get(CORRELATION_ID) != null) {
            return requestHeaders.get(CORRELATION_ID).stream().findFirst().get();
        } else {
            return null;
        }
    }

    public ServerWebExchange setRequestHeader (ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCorrelationId (ServerWebExchange serverWebExchange, String correlationId) {
        return this.setRequestHeader(serverWebExchange, CORRELATION_ID, correlationId);
    }
}
