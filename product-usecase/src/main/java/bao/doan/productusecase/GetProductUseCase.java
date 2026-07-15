package bao.doan.productusecase;

import bao.doan.productdomain.Product;
import bao.doan.productdomain.ProductProvider;
import bao.doan.productusecase.exception.EntityNotFoundException;
import bao.doan.productusecase.exception.ErrorDetail;
import bao.doan.productusecase.model.ProductResponse;
import bao.doan.productusecase.port.in.GetProductInputPort;
import java.util.Optional;

public class GetProductUseCase implements GetProductInputPort {

  private final ProductProvider productProvider;

  public GetProductUseCase(ProductProvider productProvider) {
    this.productProvider = productProvider;
  }

  @Override
  public ProductResponse getProduct(String id) {
    var product = productProvider.getProduct(id);
    return toProductResponse(Optional.ofNullable(product).orElseThrow(
        () -> new EntityNotFoundException(new ErrorDetail("id", "product is not exist."))));
  }

  private ProductResponse toProductResponse(Product product) {
    return new ProductResponse(product.getId(), product.getName());
  }
}
