package com.gdrc.market.domain.repository;

import com.gdrc.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface IPurcaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);

}
