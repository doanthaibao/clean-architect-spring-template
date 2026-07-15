package bao.doan.productusecase;

import bao.doan.productdomain.Product;
import bao.doan.productdomain.ProductProvider;
import bao.doan.productusecase.exception.EntityNotFoundException;
import bao.doan.productusecase.exception.ErrorDetail;
import bao.doan.productusecase.model.ProductResponse;
import java.util.Optional;

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
public class GetProductUseCase {

  private final ProductProvider productProvider;

  public ProductResponse getProduct(String id) {
    var product = productProvider.getProduct(id);
    return toProductResponse(Optional.ofNullable(product).orElseThrow(
        () -> new EntityNotFoundException(new ErrorDetail("id", "product is not exist."))));
  }

  private ProductResponse toProductResponse(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .build();
  }
}
