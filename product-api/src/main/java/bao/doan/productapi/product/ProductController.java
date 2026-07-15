package bao.doan.productapi.product;

import static org.mapstruct.factory.Mappers.getMapper;

import bao.doan.productusecase.AddProductUseCase;
import bao.doan.productusecase.GetProductUseCase;
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

  private final GetProductUseCase getProductUseCase;
  private final AddProductUseCase addProductUseCase;

  @GetMapping("/v1/product/{id}")
  public ResponseEntity<ProductDto> getProduct(@PathVariable("id") String id) {
    final var result = getMapper(ProductDtoMapper.class).dtoFromResponse(
        getProductUseCase.getProduct(id));
    return ResponseEntity.ok().body(result);
  }

  @PostMapping("/v1/product")
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
    final var product = getMapper(ProductDtoMapper.class).requestFromDto(productDto);
    final var productDtoR = getMapper(ProductDtoMapper.class).dtoFromResponse(
        addProductUseCase.addProduct(product));
    return ResponseEntity.status(HttpStatus.CREATED).body(productDtoR);
  }
}
