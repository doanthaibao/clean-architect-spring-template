package bao.doan.productpersistence.mapper;

import bao.doan.productdomain.Product;
import bao.doan.productpersistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

    @Mapping(source = "id", target = "productId")
    ProductEntity fromBusinessToEntity(Product product);

    @Mapping(source = "productId", target = "id")
    Product fromEntityToBusiness(ProductEntity productEntity);
}
