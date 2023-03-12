package bao.doan.productpersistence;

import bao.doan.productdomain.Product;
import bao.doan.productdomain.ProductProvider;
import bao.doan.productpersistence.mapper.ProductMapper;
import bao.doan.productpersistence.repository.ProductRepository;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

@Getter
@AllArgsConstructor
@Named
public class DBProductProvider implements ProductProvider {

  private final ProductRepository productRepository;

  @Override
  public Product getProduct(String id) {
    return Mappers.getMapper(ProductMapper.class)
        .fromEntityToBusiness(productRepository.findProductByProductId(id));
  }

  @Override
  public Product addProduct(Product product) {
    productRepository.save(Mappers.getMapper(ProductMapper.class).fromBusinessToEntity(product));
    return product;
  }
}
