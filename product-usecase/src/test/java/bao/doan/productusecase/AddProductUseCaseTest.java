package bao.doan.productusecase;

import bao.doan.productusecase.exception.EntityAlreadyExistException;
import bao.doan.productusecase.model.ProductRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AddProductUseCaseTest {

  @Test
  public void addProductUseCase() {
    final var provider = new InMemoryProductProvider();
    final var addProductUseCase = new AddProductUseCase(provider);
    final String id = "123456";
    final var product = new ProductRequest(id, "abc");
    Assertions.assertEquals("123456", addProductUseCase.addProduct(product).id());
  }

  @Test
  public void addProductUseCaseWithExist (){
    final var provider = new InMemoryProductProvider();
    final var addProductUseCase = new AddProductUseCase(provider);
    final String id = "123456";
    final var product = new ProductRequest(id, "abc");
    addProductUseCase.addProduct(product);
    assertThatExceptionOfType(EntityAlreadyExistException.class)
        .isThrownBy(() -> addProductUseCase.addProduct(product))
            .withMessage("'id' productId already exist.");
  }
}
