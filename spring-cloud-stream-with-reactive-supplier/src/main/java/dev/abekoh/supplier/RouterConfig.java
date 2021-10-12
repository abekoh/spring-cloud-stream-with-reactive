package dev.abekoh.supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {
	@Bean
	public RouterFunction<ServerResponse> routes(UserHandler userHandler) {
		return route(path("/api/v1/users").and(accept(MediaType.APPLICATION_JSON)), userHandler::send);
	}
}
