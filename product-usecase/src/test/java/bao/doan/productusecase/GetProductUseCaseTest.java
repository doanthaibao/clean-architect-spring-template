package bao.doan.productusecase;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bao.doan.productdomain.Product;
import bao.doan.productusecase.exception.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetProductUseCaseTest {

  @Test
  public void getProductUseCase() {
    var provider = new InMemoryProductProvider();
    var getProductUseCase = new GetProductUseCase(provider);
    var addProductUseCase = new AddProductUseCase(provider);
    var product = Product.builder().id("1").build();
    addProductUseCase.addProduct(product);
    Assertions.assertEquals("1", getProductUseCase.getProduct(product.getId()).getId());
  }

  @Test
  public void getNonExistProductUseCase() {
    var provider = new InMemoryProductProvider();
    var getProductUseCase = new GetProductUseCase(provider);
    assertThatExceptionOfType(EntityNotFoundException.class)
        .isThrownBy(() -> getProductUseCase.getProduct("1"))
        .withMessage("'id' product is not exist.");
  }
}
