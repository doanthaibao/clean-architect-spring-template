package bao.doan.productusecase;


import bao.doan.productdomain.Product;
import bao.doan.productdomain.ProductProvider;
import bao.doan.productusecase.exception.EntityAlreadyExistException;
import bao.doan.productusecase.exception.ErrorDetail;
import bao.doan.productusecase.model.ProductRequest;
import bao.doan.productusecase.model.ProductResponse;
import java.util.Objects;

import jakarta.inject.Named;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@Named
public class AddProductUseCase {

  private final ProductProvider productProvider;

  public ProductResponse addProduct(ProductRequest productRequest) {
    final var product = toProduct(productRequest);
    if (Objects.nonNull(productProvider.getProduct(product.getId()))) {
      throw new EntityAlreadyExistException(new ErrorDetail("id", "productId already exist."));
    }
    return toProductResponse(productProvider.addProduct(product));
  }

  private Product toProduct(ProductRequest productRequest) {
    return Product.builder()
        .id(productRequest.getId())
        .name(productRequest.getName())
        .build();
  }

  private ProductResponse toProductResponse(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .build();
  }

}
