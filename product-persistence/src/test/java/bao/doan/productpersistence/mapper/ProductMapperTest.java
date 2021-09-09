package bao.doan.productpersistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import bao.doan.productdomain.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class ProductMapperTest {

  @Test
  public void convertFromProductToEntity() {
    final var product = Product.builder().id("xxx").name("Car").build();
    final var productEntityMapping = Mappers.getMapper(ProductMapper.class).fromBusinessToEntity(product);
    final var actualProduct = Mappers.getMapper(ProductMapper.class).fromEntityToBusiness(productEntityMapping);
    assertThat(actualProduct).usingRecursiveComparison().isEqualTo(product);
  }
}
