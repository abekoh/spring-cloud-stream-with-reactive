package dev.abekoh.supplier;

import dev.abekoh.domain.models.User;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Slf4j
@Component
public class UserHandler {

	private final Sinks.Many<User> sink;

	public UserHandler(Sinks.Many<User> sink) {
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
				.doOnNext(usr -> log.info("receive {}", usr))
				.map(UserRequest::toNewUser)
				.doOnNext(usr -> sink.emitNext(usr, EmitFailureHandler.FAIL_FAST))
				.doOnNext(usr -> log.info("send {}", usr))
				.flatMap(usr -> ServerResponse.ok().build());

	}
}
