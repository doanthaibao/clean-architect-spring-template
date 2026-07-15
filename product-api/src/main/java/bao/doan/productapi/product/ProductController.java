package bao.doan.productapi.product;

import bao.doan.productusecase.port.in.AddProductInputPort;
import bao.doan.productusecase.port.in.GetProductInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final GetProductInputPort getProductUseCase;
  private final AddProductInputPort addProductUseCase;
  private final ProductDtoMapper productDtoMapper;

  @GetMapping("/v1/product/{id}")
  public ResponseEntity<ProductDto> getProduct(@PathVariable String id) {
    final var result = productDtoMapper.dtoFromResponse(getProductUseCase.getProduct(id));
    return ResponseEntity.ok().body(result);
  }

  @PostMapping("/v1/product")
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
    final var product = productDtoMapper.requestFromDto(productDto);
    final var productDtoR = productDtoMapper.dtoFromResponse(
        addProductUseCase.addProduct(product));
    return ResponseEntity.status(HttpStatus.CREATED).body(productDtoR);
  }
}
