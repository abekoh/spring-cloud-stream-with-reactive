package dev.abekoh.domain.models;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class User {

	final UUID userId;

	final String firstName;

	final String lastName;

	public User(
			String firstName,
			String lastName) {
		this.userId = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@JsonCreator(mode = Mode.PROPERTIES)
	public User(
			@JsonProperty("userId") String userId,
			@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName) {
		this.userId = UUID.fromString(userId);
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
