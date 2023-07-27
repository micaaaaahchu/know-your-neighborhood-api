package com.lithan.kyn.model;

import com.lithan.kyn.entity.UserAccount;

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
public class UserDto {
    public UserDto() {
        // Empty constructor required by ModelMapper
    }
	private int userId;

	private String name;

	private String email;

	private String imageUrl;

	private String address;

	private String phoneNumber;

	private String provider;

	public UserDto(UserAccount user) {
		this.userId = user.getUserId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.imageUrl = user.getImageUrl();
		this.address = user.getAddress();
		this.phoneNumber = user.getPhoneNumber();
		this.provider = user.getProvider().toString();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	
}
