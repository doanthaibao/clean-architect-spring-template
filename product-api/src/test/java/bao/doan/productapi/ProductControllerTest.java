package bao.doan.productapi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import bao.doan.productapi.exception.CustomResponseEntityExceptionHandler;
import bao.doan.productapi.product.ProductController;
import bao.doan.productapi.product.ProductDto;
import bao.doan.productusecase.AddProductUseCase;
import bao.doan.productusecase.GetProductUseCase;
import bao.doan.productusecase.exception.EntityAlreadyExistException;
import bao.doan.productusecase.exception.EntityNotFoundException;
import bao.doan.productusecase.exception.ErrorDetail;
import bao.doan.productusecase.model.ProductRequest;
import bao.doan.productusecase.model.ProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootTest(classes = {ProductController.class, CustomResponseEntityExceptionHandler.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private GetProductUseCase getProductUseCase;

  @MockitoBean
  private AddProductUseCase addProductUseCase;

  private final ObjectMapper objectMapper = new ObjectMapper();

  private static final String ID = "12345";

  private ProductDto productDto;
  private ProductResponse productResponse;

  @BeforeEach
  void setup() {
    productDto = new ProductDto();
    productDto.setId(ID);
    productDto.setName("Plan");
    productResponse = ProductResponse.builder().name("Plan").id(ID).build();
  }

  @Test
  public void getResultFromGetProduct() throws Exception {
    given(getProductUseCase.getProduct(ID)).willReturn(productResponse);
    this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/product/{id}", ID))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void getNoResultWithId() throws Exception {
    doThrow(new EntityNotFoundException(new ErrorDetail("id", "not found")))
        .when(getProductUseCase).getProduct(anyString());
    this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/product/{id}", "33"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void addNewProduct() throws Exception {
    given(addProductUseCase.addProduct(any(ProductRequest.class))).willReturn(productResponse);
    this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productDto)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void returnExistExceptionWhenCreateProduct() throws Exception {
    doThrow(new EntityAlreadyExistException(new ErrorDetail("id", "already exists")))
        .when(addProductUseCase).addProduct(any(ProductRequest.class));
    this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productDto)))
        .andExpect(MockMvcResultMatchers.status().isConflict());
  }

}
