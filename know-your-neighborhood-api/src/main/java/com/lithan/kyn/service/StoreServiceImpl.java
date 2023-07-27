package com.lithan.kyn.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lithan.kyn.entity.Store;
import com.lithan.kyn.entity.UserAccount;
import com.lithan.kyn.model.StoreDto;
import com.lithan.kyn.repository.StoreRepository;
import com.lithan.kyn.repository.UserAccountRepository;

@Service
public class StoreServiceImpl implements StoreService {

  @Autowired
  private StoreRepository storeRepo;

  @Autowired
  private UserAccountRepository userRepo;

  @Override
  public List<Store> listStore() {
    return storeRepo.findAll();
  }

  @Override
  public Store getStoreById(int storeId) {
    return storeRepo.findById(storeId)
        .orElseThrow(() -> new NoSuchElementException("Store not found"));
  }

  @Override
  public StoreDto addStore(StoreDto storeDto) {
    Optional<UserAccount> optionalUser = userRepo.findByEmail(storeDto.getUser().getEmail());

    if (optionalUser.isPresent()) {
      UserAccount user = optionalUser.get();
      Store store = new Store(storeDto, user);
      storeRepo.save(store);
      StoreDto DTO = new StoreDto(store);
      return DTO;
    } else {
      throw new NoSuchElementException("User not found");
    }
  }


  @Override
  public Store editStore(StoreDto storeDto) {
    Store store = storeRepo.findById(storeDto.getStoreId())
        .orElseThrow(() -> new NoSuchElementException("Store not found"));

    store.setStoreName(storeDto.getStoreName());
    store.setCountry(storeDto.getCountry());
    store.setCity(storeDto.getCity());
    store.setStoreEmail(storeDto.getStoreEmail());
    store.setPhoneNumber(storeDto.getPhoneNumber());
    store.setImageUrl(storeDto.getImageUrl());
    store.setDescription(storeDto.getDescription());

    return storeRepo.save(store);
  }

  @Override
  public List<Store> searchStore(String keyword) {
    return storeRepo.searchStore(keyword);
  }

}
