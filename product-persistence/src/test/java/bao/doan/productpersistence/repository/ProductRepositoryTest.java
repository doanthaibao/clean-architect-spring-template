package bao.doan.productpersistence.repository;

import bao.doan.productpersistence.ProductPersistenceConfiguration;
import bao.doan.productpersistence.entity.ProductEntity;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProductPersistenceConfiguration.class})
@Transactional
public class ProductRepositoryTest {

  @Autowired
  private transient ProductRepository productRepository;

  @Test
  public void getProductEntity() {
    final var actualProductEntity = ProductEntity.builder().id("xxxxx").productId("TST")
        .name("Car product").build();
    productRepository.save(actualProductEntity);
    final var productEntity = productRepository.findProductByProductId(actualProductEntity.getProductId());
    Assertions.assertEquals(actualProductEntity.getProductId(), productEntity.getProductId());
    Assertions.assertEquals(actualProductEntity.getName(), productEntity.getName());
  }
}
