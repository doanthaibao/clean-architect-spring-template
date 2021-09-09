package bao.doan.productusecase;

import bao.doan.productdomain.Product;
import bao.doan.productdomain.ProductProvider;
import java.util.HashMap;
import java.util.Map;

public class InMemoryProductProvider implements ProductProvider {

  private final transient Map<String, Product> provider = new HashMap<>();

  @Override
  public Product getProduct(final String id) {
    return provider.get(id);
  }

  @Override
  public Product addProduct(final Product product) {
    provider.put(product.getId(), product);
    return product;
  }
}
