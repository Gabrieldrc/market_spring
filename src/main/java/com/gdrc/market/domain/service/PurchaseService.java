package com.gdrc.market.domain.service;

import com.gdrc.market.domain.Purchase;
import com.gdrc.market.domain.repository.IPurcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private IPurcaseRepository purcaseRepository;

    public List<Purchase> getAll() {
        return purcaseRepository.getAll();
    }
    public Optional<List<Purchase>> getByCustomer(String customerId) {
        return purcaseRepository.getByCustomer(customerId);
    }
    public Purchase save(Purchase purchase) {
        return purcaseRepository.save(purchase);
    }
}
