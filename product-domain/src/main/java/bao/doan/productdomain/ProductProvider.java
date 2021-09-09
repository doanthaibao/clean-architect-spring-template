package bao.doan.productdomain;

public interface ProductProvider {

  Product getProduct(String id);

  Product addProduct(Product product);
}
