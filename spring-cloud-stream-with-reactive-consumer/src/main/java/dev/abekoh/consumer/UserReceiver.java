package dev.abekoh.consumer;


import dev.abekoh.domain.models.User;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserReceiver {
	public Mono<Void> receiveUser(User user) {
		log.info("receive {}", user);
		return Mono.empty();
	}
}
