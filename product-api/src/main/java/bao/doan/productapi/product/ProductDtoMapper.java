package bao.doan.productapi.product;

import bao.doan.productusecase.model.ProductRequest;
import bao.doan.productusecase.model.ProductResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ProductDtoMapper {

  ProductDto dtoFromResponse(ProductResponse productResponse);

  ProductRequest requestFromDto(ProductDto productDto);
}
