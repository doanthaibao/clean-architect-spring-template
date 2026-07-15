package bao.doan.productpersistence;

import bao.doan.productdomain.Product;
import bao.doan.productdomain.ProductProvider;
import bao.doan.productpersistence.mapper.ProductMapper;
import bao.doan.productpersistence.repository.ProductRepository;

public class DBProductProvider implements ProductProvider {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public DBProductProvider(ProductRepository productRepository, ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @Override
  public Product getProduct(String id) {
    return productMapper.fromEntityToBusiness(productRepository.findProductByProductId(id));
  }

  @Override
  public Product addProduct(Product product) {
    productRepository.save(productMapper.fromBusinessToEntity(product));
    return product;
  }
}
