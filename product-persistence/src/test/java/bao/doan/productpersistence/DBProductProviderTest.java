package bao.doan.productpersistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.when;

import bao.doan.productdomain.Product;
import bao.doan.productpersistence.entity.ProductEntity;
import bao.doan.productpersistence.mapper.ProductMapper;
import bao.doan.productpersistence.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DBProductProviderTest {

  private static final String ID = "1234";
  @Mock
  private transient ProductRepository productRepository;

  private transient DBProductProvider dbProductProvider;

  @BeforeEach
  void setUp() {
    final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    dbProductProvider = new DBProductProvider(productRepository, productMapper);
  }

  @Test
  public void getProduct() {
    final var productEntity = ProductEntity.builder().id("1").productId(ID).name("test").build();
    when(productRepository.findProductByProductId(ArgumentMatchers.eq(ID))).thenReturn(
        productEntity);
    final var product = dbProductProvider.getProduct(ID);
    assertThat(productEntity.getProductId()).isEqualTo(product.getId());
  }

  @Test
  public void addProduct() {
    var product = new Product("1", "abc");
    assertThatNoException().isThrownBy(() -> dbProductProvider.addProduct(product));
  }

}
