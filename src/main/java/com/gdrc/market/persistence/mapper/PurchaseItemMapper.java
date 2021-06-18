package com.gdrc.market.persistence.mapper;

import com.gdrc.market.domain.PurchaseItem;
import com.gdrc.market.persistence.entity.PurchasesProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {
    @Mappings({
        @Mapping(source = "id.productId", target = "productId"),
        @Mapping(source = "state", target = "active")
    })
    PurchaseItem toPurchaseItem(PurchasesProductEntity purchasesProduct);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "purchaseEntity", ignore = true),
            @Mapping(target = "productEntity", ignore = true),
            @Mapping(target = "id.purchaseId", ignore = true)
    })
    PurchasesProductEntity toPurchasesProductEntity(PurchaseItem purchaseItem);
}
