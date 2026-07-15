package bao.doan.productusecase.port.in;

import bao.doan.productusecase.model.ProductRequest;
import bao.doan.productusecase.model.ProductResponse;

public interface AddProductInputPort {

  ProductResponse addProduct(ProductRequest productRequest);
}
