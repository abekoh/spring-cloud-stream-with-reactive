package dev.abekoh.supplier;

import java.util.function.Supplier;

import dev.abekoh.domain.models.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupplierConfig {
	@Bean
	public Sinks.Many<User> sink() {
		return Sinks.many().unicast().onBackpressureBuffer();
	}

	@Bean("producer")
	public Supplier<Flux<User>> producer(Sinks.Many<User> sink) {
		return sink::asFlux;
	}
}
