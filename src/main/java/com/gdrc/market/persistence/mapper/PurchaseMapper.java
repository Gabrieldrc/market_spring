package com.gdrc.market.persistence.mapper;

import com.gdrc.market.domain.Purchase;
import com.gdrc.market.domain.PurchaseItem;
import com.gdrc.market.persistence.entity.CustomerEntity;
import com.gdrc.market.persistence.entity.PurchaseEntity;
import com.gdrc.market.persistence.entity.PurchasesProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {
    @Mapping(source = "products", target = "items")
    Purchase toPurchase(PurchaseEntity purchase);
    List<Purchase> toPurchases(List<PurchaseEntity> purchases);

    @InheritInverseConfiguration
    @Mapping(target = "customerEntity", ignore = true)
    PurchaseEntity toPurchaseEntity(Purchase purchase);
}
