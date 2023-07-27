package com.lithan.kyn.model;

import java.util.List;

import com.lithan.kyn.entity.Store;

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
public class UserFullData {

	public UserFullData(UserDto profile, Object stores, List<String> roles) {

	}

	private UserDto profile;

	private List<Store> stores;

	private List<String> roles;

	public UserDto getProfile() {
		return profile;
	}

	public void setProfile(UserDto profile) {
		this.profile = profile;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	
}
