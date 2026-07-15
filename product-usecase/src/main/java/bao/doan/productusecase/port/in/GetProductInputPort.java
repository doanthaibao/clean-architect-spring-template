package bao.doan.productusecase.port.in;

import bao.doan.productusecase.model.ProductResponse;

public interface GetProductInputPort {

  ProductResponse getProduct(String id);
}
