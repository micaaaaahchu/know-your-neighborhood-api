package com.lithan.kyn.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditProfileDto {
	@NotBlank
	private int userId;

	@NotBlank
	private String name;

	@NotBlank
	private String address;

	@NotBlank
	private String phoneNumber;

	public int getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
}
