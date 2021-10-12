package dev.abekoh.supplier;

import dev.abekoh.domain.models.User;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.publisher.Sinks.Many;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class UserHandler {

	private final Sinks.Many<User> sink;

	public UserHandler(Many<User> sink) {
		this.sink = sink;
	}

	/**
	 * ユーザー1件送信
	 *
	 * @param request ServerRequest
	 * @return ServerResponse
	 */
	public Mono<ServerResponse> send(ServerRequest request) {
		return request.bodyToMono(UserRequest.class)
				.map(UserRequest::toNewUser)
				.doOnNext(usr -> sink.emitNext(usr, EmitFailureHandler.FAIL_FAST))
				.flatMap(usr -> ServerResponse.ok().build());

	}
}
