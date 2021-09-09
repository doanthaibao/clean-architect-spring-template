package bao.doan.productusecase;

import bao.doan.productdomain.Product;
import bao.doan.productusecase.exception.EntityAlreadyExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AddProductUseCaseTest {

  @Test
  public void addProductUseCase() {
    final var provider = new InMemoryProductProvider();
    final var addProductUseCase = new AddProductUseCase(provider);
    final String id = "123456";
    final var product = Product.builder().id(id).name("abc").build();
    Assertions.assertEquals("123456", addProductUseCase.addProduct(product).getId());
  }

  @Test
  public void addProductUseCaseWithExist (){
    final var provider = new InMemoryProductProvider();
    final var addProductUseCase = new AddProductUseCase(provider);
    final String id = "123456";
    final var product = Product.builder().id(id).name("abc").build();
    addProductUseCase.addProduct(product);
    assertThatExceptionOfType(EntityAlreadyExistException.class)
        .isThrownBy(() -> addProductUseCase.addProduct(product))
            .withMessage("'id' productId already exist.");
  }
}
