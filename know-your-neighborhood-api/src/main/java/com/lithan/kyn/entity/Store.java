package com.lithan.kyn.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lithan.kyn.model.StoreDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_store")
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private int storeId;

	@Column(nullable = false)
	private String storeName;

	@Column(nullable = false)
	private String country;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String storeEmail;

	@Column(nullable = false)
	private String phoneNumber;

	private String description;

	@Lob
	private String imageUrl;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserAccount user;

	public Store(StoreDto storeDto, UserAccount user) {
		this.storeName = storeDto.getStoreName();
		this.country = storeDto.getCountry();
		this.city = storeDto.getCity();
		this.storeEmail = storeDto.getStoreEmail();
		this.phoneNumber = storeDto.getPhoneNumber();
		this.description = storeDto.getDescription();
		this.imageUrl = storeDto.getImageUrl();
		this.user = user;
	}

	public int getStoreId() {
		return storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getStoreEmail() {
		return storeEmail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStoreEmail(String storeEmail) {
		this.storeEmail = storeEmail;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Store () {
		
	}

}
