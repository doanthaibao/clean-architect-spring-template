package bao.doan.productpersistence.repository;

import bao.doan.productpersistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

  ProductEntity findProductByProductId(String productId);
}
