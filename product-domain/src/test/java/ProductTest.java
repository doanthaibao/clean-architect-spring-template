import bao.doan.productdomain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

  @Test
  public void createProduct() {
    final var  product = Product.builder().id("1").name("TTD").build();
    Assertions.assertEquals("1", product.getId());
    Assertions.assertEquals("TTD", product.getName());
  }
}
