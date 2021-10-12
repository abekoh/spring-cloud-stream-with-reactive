package dev.abekoh.supplier;


import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.abekoh.domain.models.User;
import lombok.ToString;

@ToString
public class UserRequest {

	final String firstName;

	final String lastName;

	@JsonCreator(mode = Mode.PROPERTIES)
	public UserRequest(
			@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName) {
		Objects.requireNonNull(firstName, "fistName is required");
		Objects.requireNonNull(lastName, "lastName is required");
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * 新規ユーザーに変換
	 * @return User (with userId)
	 */
	public User toNewUser() {
		return new User(firstName, lastName);
	}
}
