package dev.abekoh.consumer;

import reactor.blockhound.BlockHound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerApplication {
	public static void main(String[] args) {
		BlockHound.builder()
				// LogbackのみBlockingを許可
				.allowBlockingCallsInside(
						"ch.qos.logback.classic.Logger",
						"callAppenders"
				)
				.install();
		SpringApplication.run(ConsumerApplication.class, args);
	}
}
