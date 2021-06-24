package com.gdrc.market.persistence;

import com.gdrc.market.domain.Purchase;
import com.gdrc.market.domain.repository.IPurcaseRepository;
import com.gdrc.market.persistence.crud.PurchaseCrudRepository;
import com.gdrc.market.persistence.entity.PurchaseEntity;
import com.gdrc.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PurchaseRepository implements IPurcaseRepository {
    @Autowired
    private PurchaseCrudRepository purchaseCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases(
                (List<PurchaseEntity>) purchaseCrudRepository.findAll()
        );
    }

    @Override
    public Optional<List<Purchase>> getByCustomer(String customerId) {
        return purchaseCrudRepository.findByCustomerId(customerId)
                .map(purchaseEntities -> mapper.toPurchases(purchaseEntities));
    }

    @Override
    public Purchase save(Purchase purchase) {
        PurchaseEntity purchaseEntity = mapper.toPurchaseEntity(purchase);
         /*
         Le indicamos a los productos de la compra, que pertenecen a
         dicha compra.
          */
        purchaseEntity.getProducts().forEach(products -> products.setPurchaseEntity(purchaseEntity));
        return mapper.toPurchase(purchaseCrudRepository.save(purchaseEntity));
    }
}
