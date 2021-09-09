package bao.doan.productusecase;


import bao.doan.productdomain.Product;
import bao.doan.productdomain.ProductProvider;
import bao.doan.productusecase.exception.EntityAlreadyExistException;
import bao.doan.productusecase.exception.ErrorDetail;
import java.util.Objects;
import javax.inject.Named;
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

  public Product addProduct(Product product) {
    if (Objects.nonNull(productProvider.getProduct(product.getId()))) {
      throw new EntityAlreadyExistException(new ErrorDetail("id", "productId already exist."));
    }
    return productProvider.addProduct(product);
  }

}
