package bao.doan.productapi.product;

import bao.doan.productdomain.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductDtoMapper {

  ProductDto dtoFromBusiness(Product product);

  Product businessFromDto(ProductDto productDto);
}
