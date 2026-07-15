package bao.doan.app.configuration;

import bao.doan.productapi.product.ProductDtoMapper;
import bao.doan.productdomain.ProductProvider;
import bao.doan.productpersistence.DBProductProvider;
import bao.doan.productpersistence.mapper.ProductMapper;
import bao.doan.productpersistence.repository.ProductRepository;
import bao.doan.productusecase.AddProductUseCase;
import bao.doan.productusecase.GetProductUseCase;
import bao.doan.productusecase.port.in.AddProductInputPort;
import bao.doan.productusecase.port.in.GetProductInputPort;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CleanArchitectureConfiguration {

  @Bean
  ProductDtoMapper productDtoMapper() {
    return Mappers.getMapper(ProductDtoMapper.class);
  }

  @Bean
  ProductMapper productMapper() {
    return Mappers.getMapper(ProductMapper.class);
  }

  @Bean
  ProductProvider productProvider(ProductRepository productRepository, ProductMapper productMapper) {
    return new DBProductProvider(productRepository, productMapper);
  }

  @Bean
  GetProductInputPort getProductInputPort(ProductProvider productProvider) {
    return new GetProductUseCase(productProvider);
  }

  @Bean
  AddProductInputPort addProductInputPort(ProductProvider productProvider) {
    return new AddProductUseCase(productProvider);
  }
}
