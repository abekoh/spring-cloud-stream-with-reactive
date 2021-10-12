package dev.abekoh.consumer;

import java.util.function.Consumer;

import dev.abekoh.domain.models.User;
import reactor.core.publisher.Flux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {
	@Bean("consumer")
	public Consumer<Flux<User>> consumer(UserReceiver receiver) {
		return stream -> stream
				.map(receiver::receiveUser)
				.subscribe();
	}
}
