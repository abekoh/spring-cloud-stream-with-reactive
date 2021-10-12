package dev.abekoh.supplier;

import reactor.blockhound.BlockHound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SupplierApplication {
	public static void main(String[] args) {
		BlockHound.builder()
				// LogbackのみBlockingを許可
				.allowBlockingCallsInside(
						"ch.qos.logback.classic.Logger",
						"callAppenders"
				)
				.install();
		SpringApplication.run(SupplierApplication.class, args);
	}
}
