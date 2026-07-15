package bao.doan.productusecase;


import bao.doan.productdomain.Product;
import bao.doan.productdomain.ProductProvider;
import bao.doan.productusecase.exception.EntityAlreadyExistException;
import bao.doan.productusecase.exception.ErrorDetail;
import bao.doan.productusecase.model.ProductRequest;
import bao.doan.productusecase.model.ProductResponse;
import bao.doan.productusecase.port.in.AddProductInputPort;
import java.util.Objects;

public class AddProductUseCase implements AddProductInputPort {

  private final ProductProvider productProvider;

  public AddProductUseCase(ProductProvider productProvider) {
    this.productProvider = productProvider;
  }

  @Override
  public ProductResponse addProduct(ProductRequest productRequest) {
    final var product = toProduct(productRequest);
    if (Objects.nonNull(productProvider.getProduct(product.getId()))) {
      throw new EntityAlreadyExistException(new ErrorDetail("id", "productId already exist."));
    }
    return toProductResponse(productProvider.addProduct(product));
  }

  private Product toProduct(ProductRequest productRequest) {
    return Product.builder()
        .id(productRequest.id())
        .name(productRequest.name())
        .build();
  }

  private ProductResponse toProductResponse(Product product) {
    return new ProductResponse(product.getId(), product.getName());
  }

}
